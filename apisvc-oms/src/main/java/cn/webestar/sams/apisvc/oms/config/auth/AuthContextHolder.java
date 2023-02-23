package cn.webestar.sams.apisvc.oms.config.auth;

import cn.webestar.sams.apisvc.oms.common.OmsConstant;
import cn.webestar.sams.basic.common.session.Authentication;
import cn.webestar.sams.basic.common.session.SessionContext;
import cn.webestar.sams.starter.utils.JwtTokenUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AuthContextHolder {

    private static RedisTemplate redisTemplate;

    private static final ThreadLocal<SessionContext> contexts = new ThreadLocal<>();

    public static void setRedisTemplate(RedisTemplate redisTemplate) {
        AuthContextHolder.redisTemplate = redisTemplate;
    }

    public static SessionContext getContext() {
        return contexts.get();
    }

    public static void setContext(SessionContext context) {
        contexts.set(context);
        refresh();
    }

    public static void refresh() {
        SessionContext context = contexts.get();
        String refreshTokenKey = String.format(OmsConstant.AUTH.REFRESH_TOKEN_CACHE_KEY, context.getAuth().getUserId(), context.getRefreshToken());
        redisTemplate.opsForValue().set(refreshTokenKey, context, OmsConstant.AUTH.REFRESH_TOKEN_EXPIRE, TimeUnit.SECONDS);
    }

    public static SessionContext setAuthentication(Authentication authentication){

        String userId = authentication.getUserId().toString();
        String accessToken = JwtTokenUtil.generateToken(userId);
        String refreshToken = UUID.randomUUID().toString().replace("-", "");

        SessionContext context = new SessionContext();
        context.setAccessToken(accessToken);
        context.setRefreshToken(refreshToken);
        context.setAuth(authentication);
        context.setExpire(LocalDateTime.now().plusSeconds(JwtTokenUtil.EXPIRE));

        AuthContextHolder.setContext(context);

        redisTemplate.opsForValue().set(String.format(OmsConstant.AUTH.ACCESS_TOKEN_CACHE_KEY, userId, accessToken), context, OmsConstant.AUTH.ACCESS_TOKEN_EXPIRE, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(String.format(OmsConstant.AUTH.REFRESH_TOKEN_CACHE_KEY, userId, refreshToken), accessToken, OmsConstant.AUTH.REFRESH_TOKEN_EXPIRE, TimeUnit.SECONDS);

        return context;
    }

    public static void logout(){
        SessionContext context = AuthContextHolder.getContext();
        Long userId = context.getAuth().getUserId();
        String accessToken = context.getAccessToken();
        String refreshToken = context.getRefreshToken();
        redisTemplate.expire(String.format(OmsConstant.AUTH.ACCESS_TOKEN_CACHE_KEY, userId, accessToken), 0, TimeUnit.SECONDS);
        redisTemplate.expire(String.format(OmsConstant.AUTH.REFRESH_TOKEN_CACHE_KEY, userId, refreshToken), 0, TimeUnit.SECONDS);
    }

    public static void invalid(Long userId) {

    }

}