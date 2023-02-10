package cn.webestar.sams.starter.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class SequenceConfig {

    @Autowired(required = false)
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    public IdGenerator idGenerator() {
        return new IncrIdGenerator(redisTemplate);
    }

}
