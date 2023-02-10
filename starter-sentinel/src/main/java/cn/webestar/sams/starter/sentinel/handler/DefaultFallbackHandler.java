package cn.webestar.sams.starter.sentinel.handler;

import cn.webestar.sams.basic.common.R;

public class DefaultFallbackHandler {

    public static R<String> fun(Throwable e){
        return R.fail("异常，接口被降级熔断，执行了DefaultFallback.fun");
    }

}
