package cn.webestar.sams.apisvc.oms.config.auth;

import cn.webestar.sams.apisvc.oms.common.OmsConstant;
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
public class AuthFilter implements Filter {

    private RedisTemplate redisTemplate;

    public AuthFilter(RedisTemplate redisTemplate){
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

        if (OmsConstant.AUTH.IGNORE_METHOD.equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        SessionContext context = getSessionContext(request);
        if(context!=null){
            AuthContextHolder.setContext(context);
        }

        chain.doFilter(request, response);
    }

    private SessionContext getSessionContext(HttpServletRequest request) {

        String authHeader = request.getHeader(OmsConstant.AUTH.TOKEN_HEADER);
        if(authHeader==null){
            return null;
        }

        try {
            authHeader = URLDecoder.decode(authHeader, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.debug("toke 编码失败", e);
            return null;
        }
        if(!authHeader.startsWith(OmsConstant.AUTH.TOKEN_PREFIX)){
            return null;
        }

        final String accessToken = authHeader.substring(OmsConstant.AUTH.TOKEN_PREFIX.length());
        final Claims claims = JwtTokenUtil.getClaims(accessToken);
        if(claims==null){
            return null;
        }

        String userId = claims.getSubject();
        if(userId==null){
            return null;
        }

        String accessTokenKey = String.format(OmsConstant.AUTH.ACCESS_TOKEN_CACHE_KEY, userId, accessToken);
        SessionContext context = (SessionContext) redisTemplate.opsForValue().get(accessTokenKey);

        if(context==null){
            return null;
        }

        // accessToken 延期
        redisTemplate.expire(accessTokenKey, OmsConstant.AUTH.ACCESS_TOKEN_EXPIRE, TimeUnit.SECONDS);

        // refreshToken 延期
        String refreshTokenKey = String.format(OmsConstant.AUTH.REFRESH_TOKEN_CACHE_KEY, userId, context.getRefreshToken());
        redisTemplate.expire(refreshTokenKey, OmsConstant.AUTH.REFRESH_TOKEN_EXPIRE, TimeUnit.SECONDS);

        return context;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
