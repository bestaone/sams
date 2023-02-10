package cn.webestar.sams.basic.beans.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public abstract class LimitBody extends Body{

    private Integer offset = 0;
    private Integer limit = 500;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * 转换成查询参数
     * @param <T> 查询参数格式对象
     * @return 查询参数
     */
    public <T> QueryWrapper<T> toQueryWapper() {
        QueryWrapper<T> queryWrapper = toParamsQueryWapper();
        queryWrapper.last("limit " + getOffset() + "," + getLimit());
        return queryWrapper;
    }

    public abstract <T> QueryWrapper<T> toParamsQueryWapper();

}
