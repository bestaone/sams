package cn.webestar.sams.starter.route;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@LoadBalancerClients(defaultConfiguration = {LoadbalanceConfig.class})
public class RouteConfig {

}
