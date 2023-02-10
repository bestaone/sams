package cn.webestar.sams.basic.beans.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public abstract class SimpleBody extends Body {

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
