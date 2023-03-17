package cn.webestar.sams.apisvc.mall.controller;

import cn.webestar.sams.basic.common.R;
import cn.webestar.sams.microsvc.org.api.CorpApi;
import cn.webestar.sams.microsvc.org.dto.corp.CorpCreateBody;
import cn.webestar.sams.microsvc.user.api.UserApi;
import cn.webestar.sams.microsvc.user.dto.user.UserCreateBody;
import cn.webestar.sams.starter.sentinel.handler.DefaultBlockHandler;
import cn.webestar.sams.starter.sentinel.handler.DefaultFallbackHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/basic")
public class ApiController {

    @Autowired
    private UserApi userApi;

    @Autowired
    private CorpApi corpApi;

    @GetMapping("/testLoadBalanc")
    public R<String> testLoadBalanc(HttpServletRequest requet) throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        Integer port = requet.getServerPort();
        log.info("testLoadBalanc！");
        return R.success(ip + ":" + port);
    }

    @SentinelResource(
            value = "testSentinel",
            blockHandler = "fun",
            // blockHandler 只处理sentinel流控降级规则抛出的BlockException。
            blockHandlerClass = DefaultBlockHandler.class,
            fallback = "fun",
            // fallback 默认处理所有的异常，其中包括BlockEcxeption、1/0
            fallbackClass = DefaultFallbackHandler.class
    )
    @GetMapping("/testSentinel")
    public R<String> testSentinel() {
        log.info("testSentinel！");
        return R.success();
    }

    @GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-demo-tx", rollbackFor = Exception.class)
    @GetMapping("/testSeata")
    public R<String> testSeata() throws Exception {

        UserCreateBody b = new UserCreateBody();
        b.setAvatarUrl("xx");
        b.setUsername(System.currentTimeMillis()+"");
        b.setPhoneNum("1212");
        b.setPassword("ssw");
        b.setStatus(1);
        b.setRegtime(LocalDateTime.now());
        b.setCreaterId(1L);
        b.setUpdaterId(1L);
        R r1 = userApi.create(b);

        CorpCreateBody b1 = new CorpCreateBody();
        b1.setName("corp");
        b1.setAddress("eee");
        b1.setStatus(1);
        b1.setCreaterId(1L);
        b1.setUpdaterId(1L);
        R r2 = corpApi.create(b1);

        log.info("testSeata！");

        throw new Exception("发生异常，数据已回滚，请核查数据库表");
    }

}
