package cn.webestar.sams.compsvc.gateway.filter;

import cn.webestar.sams.basic.common.Constant;
import cn.webestar.sams.basic.common.R;
import cn.webestar.sams.starter.utils.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class AuthCheckGateWayFilter implements GatewayFilter, Ordered {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        if (Constant.AUTH.IGNORE_METHOD.equalsIgnoreCase(request.getMethod().toString())) {
            return chain.filter(exchange);
        }

        final String url = request.getPath().pathWithinApplication().value();
        final String method = exchange.getRequest().getMethod().name();
        log.info("请求METHOD:{},URL:{}", method, url);
        if (isIgnoreUrl(url)) {
            return chain.filter(exchange);
        }

        try {

            String authHeader = request.getHeaders().getFirst(Constant.AUTH.TOKEN_HEADER);
            if (authHeader == null) {
                return unauthorizedResponse(exchange, response, R.fail(40003, "miss token"));
            }

            authHeader = URLDecoder.decode(authHeader, "UTF-8");
            if (!authHeader.startsWith(Constant.AUTH.TOKEN_PREFIX)) {
                return unauthorizedResponse(exchange, response, R.fail(40003, "miss bearer"));
            }

            final String token = authHeader.substring(Constant.AUTH.TOKEN_PREFIX.length());
            final Claims claims = JwtTokenUtil.getClaims(token);
            if (claims == null) {
                return unauthorizedResponse(exchange, response, R.fail(40003, "token error"));
            }

            String userId = claims.getSubject();
            if (userId == null) {
                return unauthorizedResponse(exchange, response, R.fail(40003, "invalid token"));
            }

            Date expiredTime = claims.getExpiration();
            if (expiredTime == null || (System.currentTimeMillis() > expiredTime.getTime())) {
                return unauthorizedResponse(exchange, response, R.fail(40003, "token expire"));
            }

            String tokenKey = String.format(Constant.AUTH.ACCESS_TOKEN_CACHE_KEY, userId, token);
            Object context = redisTemplate.opsForValue().get(tokenKey);
            if (context == null) {
                return unauthorizedResponse(exchange, response, R.fail(40003, "invalid token"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return unauthorizedResponse(exchange, response, R.fail(40003, "valid fail"));
        }

        return chain.filter(exchange);
    }

    /**
     * 将 JWT 鉴权失败的消息响应给客户端
     */
    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, ServerHttpResponse response, R r) {
        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String json = null;
        try {
            json = objectMapper.writeValueAsString(r);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        DataBuffer dataBuffer = response.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(dataBuffer));
    }

    /**
     * 检测url是否是忽略登录验证的地址
     */
    public boolean isIgnoreUrl(String uri) {
        for (String includeUrl : Constant.AUTH.IGNORE_URLS) {
            if (antPathMatcher.match(includeUrl, uri)) {
                return true;
            }
        }
        return false;
    }

    //   值越小，优先级越高
    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

}