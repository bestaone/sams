package cn.webestar.sams.apisvc.auth.controller;

import cn.webestar.sams.basic.common.R;
import cn.webestar.sams.basic.common.session.Authentication;
import cn.webestar.sams.microsvc.user.api.UserApi;
import cn.webestar.sams.microsvc.user.domain.User;
import cn.webestar.sams.starter.apisvc.session.SessionContextHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class ApiController {

    @Autowired
    private UserApi userApi;

    @Operation(summary = "获取当前登录用户", security = { @SecurityRequirement(name = "Authorization") })
    @PostMapping("/currentUser")
    public R<Map<String, Object>> currentUser(){

        Authentication auth = SessionContextHolder.getContext().getAuth();
        R<User> r = userApi.findById(auth.getUserId());
        User user = r.getData();

        Map<String, Object> map = new HashMap();
        map.put("name", user.getUsername());
        map.put("avatar", user.getAvatarUrl());

        return R.success(map);
    }

    @PostMapping("/logout")
    public R logout(){
        SessionContextHolder.logout();
        return R.success();
    }

}
