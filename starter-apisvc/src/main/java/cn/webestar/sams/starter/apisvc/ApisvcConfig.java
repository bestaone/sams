package cn.webestar.sams.starter.apisvc;

import cn.webestar.sams.starter.apisvc.interceptor.FeignRequestInterceptor;
import cn.webestar.sams.starter.apisvc.session.SessionContextHolder;
import cn.webestar.sams.starter.apisvc.session.SessionFilter;
import feign.RequestInterceptor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class ApisvcConfig {


    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init(){
        SessionContextHolder.setRedisTemplate(redisTemplate);
    }

    /**
     * header 传递
     */
    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return new FeignRequestInterceptor();
    }

    @Bean
    public FilterRegistrationBean companyUrlFilterRegister() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SessionFilter(redisTemplate));
        registration.addUrlPatterns("/*");
        registration.setName("authFilter");
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }

}
