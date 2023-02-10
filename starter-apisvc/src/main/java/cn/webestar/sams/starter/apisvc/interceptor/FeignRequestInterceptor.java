package cn.webestar.sams.starter.apisvc.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

public class FeignRequestInterceptor implements RequestInterceptor {

    /**
     * 将上游请求的header全部传递到下游接口
     */
    @Override
    public void apply(RequestTemplate template) {
        Map<String, Collection<String>> headers = getHeaders();
        // feign源码问题，带"content-length" 会报错
        headers.remove("content-length");
        template.headers(headers);
    }

    /**
     * 获取 request 中的所有的 header 值
     */
    private Map<String, Collection<String>> getHeaders() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Collection<String>> map = new LinkedHashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, Arrays.asList(value));
        }
        return map;
    }
}