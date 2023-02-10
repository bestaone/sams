package cn.webestar.sams.basic.beans.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public abstract class PageBody extends Body{

    private Integer pageNum = 1;
    private Integer pageSize = 50;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Page getPage() {
        return new Page(pageNum, pageSize, true);
    }

    /**
     * 转换成查询参数
     * @param <T> 查询参数格式对象
     * @return 查询参数
     */
    public <T> QueryWrapper<T> toQueryWapper() {
        return toParamsQueryWapper();
    }

    public abstract <T> QueryWrapper<T> toParamsQueryWapper();

}
