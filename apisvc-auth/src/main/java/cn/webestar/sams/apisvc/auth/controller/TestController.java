package cn.webestar.sams.apisvc.auth.controller;

import cn.webestar.sams.basic.common.R;
import cn.webestar.sams.microsvc.user.api.UserApi;
import cn.webestar.sams.microsvc.user.domain.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试", description = "测试")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private UserApi userApi;

    @Value("${my.test}")
    private String test = "ok";

    @GetMapping("/one")
    public R<User> getApi(){
        return userApi.findById(1L);
    }

}
