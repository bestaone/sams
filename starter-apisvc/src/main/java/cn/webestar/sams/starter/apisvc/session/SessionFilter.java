package cn.webestar.sams.starter.apisvc.session;

import cn.webestar.sams.basic.common.Constant;
import cn.webestar.sams.basic.common.session.SessionContext;
import cn.webestar.sams.starter.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SessionFilter implements Filter {

    private RedisTemplate redisTemplate;

    public SessionFilter(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (Constant.AUTH.IGNORE_METHOD.equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        SessionContext context = getSessionContext(request);
        if(context!=null){
            SessionContextHolder.setContext(context);
        }

        chain.doFilter(request, response);
    }

    private SessionContext getSessionContext(HttpServletRequest request) {

        String authHeader = request.getHeader(Constant.AUTH.TOKEN_HEADER);
        if(authHeader==null){
            return null;
        }

        try {
            authHeader = URLDecoder.decode(authHeader, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.debug("toke 编码失败", e);
            return null;
        }
        if(!authHeader.startsWith(Constant.AUTH.TOKEN_PREFIX)){
            return null;
        }

        final String accessToken = authHeader.substring(Constant.AUTH.TOKEN_PREFIX.length());
        final Claims claims = JwtTokenUtil.getClaims(accessToken);
        if(claims==null){
            return null;
        }

        String userId = claims.getSubject();
        if(userId==null){
            return null;
        }

        String accessTokenKey = String.format(Constant.AUTH.ACCESS_TOKEN_CACHE_KEY, userId, accessToken);
        SessionContext context = (SessionContext) redisTemplate.opsForValue().get(accessTokenKey);

        if(context==null){
            return null;
        }

        // accessToken 延期
        redisTemplate.expire(accessTokenKey, Constant.AUTH.ACCESS_TOKEN_EXPIRE, TimeUnit.SECONDS);

        // refreshToken 延期
        String refreshTokenKey = String.format(Constant.AUTH.REFRESH_TOKEN_CACHE_KEY, userId, context.getRefreshToken());
        redisTemplate.expire(refreshTokenKey, Constant.AUTH.REFRESH_TOKEN_EXPIRE, TimeUnit.SECONDS);

        return context;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
