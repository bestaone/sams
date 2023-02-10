package cn.webestar.sams.starter.sentinel.handler;

import cn.webestar.sams.basic.common.R;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class DefaultBlockHandler {

    public static R<String> fun(BlockException e){
        return R.fail("异常，接口被降级熔断，执行了DefaultBlockHandler.fun");
    }

}
