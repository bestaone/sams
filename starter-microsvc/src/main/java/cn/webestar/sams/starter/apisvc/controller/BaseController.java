package cn.webestar.sams.starter.apisvc.controller;

import cn.webestar.sams.basic.beans.api.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.webestar.sams.basic.beans.domain.CommonDO;
import cn.webestar.sams.basic.common.Assert;
import cn.webestar.sams.basic.common.CommonException;
import cn.webestar.sams.basic.common.R;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseController<T extends CommonDO> {

    /**
     *  获取service
     */
    public abstract IService<T> getService();

    public R<T> _create(CreateBody body) {
        T o = body.toDomain();
        getService().save(o);
        return R.success(o);
    }

    public R<Boolean> _delete(Long id) {
        Assert.notNull(id, 30001, "id不能为空");
        Boolean f = getService().removeById(id);
        return R.success(f);
    }

    public R<T> _update(UpdateBody body) {
        T o = body.toDomain();
        getService().updateById(o);
        return R.success(o);
    }

    public R<T> _findById(Long id) {
        Assert.notNull(id, 30001, "id不能为空");
        T o = getService().getById(id);
        return R.success(o);
    }

    public R<Map<Long, T>> _findByIds(Set<Long> ids) {
        Assert.notNull(ids, 30001, "ids不能为空");
        Assert.isTrue(ids.size()>0, 30002, "ids不能为空");
        List<T> list = getService().listByIds(ids);
        Map<Long, T> map = new HashMap(10);
        list.forEach((item) -> map.put(item.getId(), item));
        return R.success(map);
    }

    public R<IPage<T>> _page(PageBody body) {
        Assert.notNull(body.getPageNum(), 30001, "pageNum不能为空");
        Assert.notNull(body.getPageSize(), 30002, "pageSize不能为空");
        Assert.isTrue(body.getPageSize()<=200, 30003, "pageSize长度不能超过200");
        IPage<T> page = getService().page(body.getPage(), body.toQueryWapper());
        return R.success(page);
    }

    public R<T> _findOne(SimpleBody body) {
        T o = null;
        try {
            o = getService().getOne(body.toQueryWapper());
        } catch (MyBatisSystemException e) {
            if (e.getCause() instanceof TooManyResultsException) {
                throw new CommonException(31001, "查询结果不唯一");
            } else {
                e.printStackTrace();
            }
        }
        // 解决无法转换null返回的问题
        return R.success(o);
    }

    public R<List<T>> _limitList(@RequestBody LimitBody body) {
        Assert.notNull(body.getOffset(), 30001, "offset不能为空");
        Assert.notNull(body.getLimit(), 30002, "limit不能为空");
        List<T> list = getService().list(body.toQueryWapper());
        return R.success(list);
    }

}
