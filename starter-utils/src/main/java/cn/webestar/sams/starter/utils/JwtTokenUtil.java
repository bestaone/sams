package cn.webestar.sams.starter.utils;

import cn.webestar.sams.basic.common.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","type": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 */
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_SUB = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String SECRET = "jwtsecret";
    /**
     * 3小时
     */
    public static final Integer EXPIRE = Constant.AUTH.ACCESS_TOKEN_EXPIRE;

    /**
     * 根据负责生成JWT的token
     */
    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    public static Claims getClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}",token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRE * 1000);
    }

    /**
     * 从token中获取登录用户名
     */
    public static String getSubjectFromToken(String token) {
        String subuject;
        try {
            Claims claims = getClaims(token);
            subuject =  claims.getSubject();
        } catch (Exception e) {
            subuject = null;
        }
        return subuject;
    }

    /**
     * 验证token是否还有效
     * @param token       客户端传入的token
     * @param subject 从数据库中查询出来的用户信息
     */
    public static boolean validateToken(String token, String subject) {
        String tokenSubject = getSubjectFromToken(token);
        return tokenSubject.equals(subject) && !tokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    private static boolean tokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private static Date getExpiredDateFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     */
    public static String generateToken(Serializable sub) {
        Map<String, Object> claims = new HashMap(2);
        claims.put(CLAIM_KEY_SUB, sub);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以被刷新
     */
    public static boolean canRefresh(String token) {
        return !tokenExpired(token);
    }

    /**
     * 刷新token
     */
    public static String refreshToken(String token) {
        Claims claims = getClaims(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

}