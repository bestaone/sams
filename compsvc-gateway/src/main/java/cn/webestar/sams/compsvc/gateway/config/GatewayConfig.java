package cn.webestar.sams.compsvc.gateway.config;

import cn.webestar.sams.compsvc.gateway.filter.GlobalAuthFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class GatewayConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    @Order(-1)
    public GlobalFilter globalAuthFilter() {
        return new GlobalAuthFilter(redisTemplate, objectMapper);
    }

}
