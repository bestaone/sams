package cn.webestar.sams.compsvc.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthCheckGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Autowired
    private AuthCheckGateWayFilter authCheckGateWayFilter;

    @Override
    public GatewayFilter apply(Object config) {
        return authCheckGateWayFilter;
    }

}

