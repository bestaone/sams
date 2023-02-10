package cn.webestar.sams.apisvc.auth.controller;

import cn.webestar.sams.apisvc.auth.api.dto.AccountLoginDTO;
import cn.webestar.sams.apisvc.auth.api.dto.SmsLoginDTO;
import cn.webestar.sams.basic.common.Assert;
import cn.webestar.sams.basic.common.Constant;
import cn.webestar.sams.basic.common.R;
import cn.webestar.sams.basic.common.api.vo.LoginVO;
import cn.webestar.sams.basic.common.session.Authentication;
import cn.webestar.sams.basic.common.session.SessionContext;
import cn.webestar.sams.microsvc.user.api.UserApi;
import cn.webestar.sams.microsvc.user.domain.User;
import cn.webestar.sams.microsvc.user.dto.user.UserSimpleBody;
import cn.webestar.sams.starter.apisvc.session.SessionContextHolder;
import cn.webestar.sams.starter.cache.CacheUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Slf4j
@Tag(name = "用户认证", description = "用户认证")
@RestController
@RequestMapping("/api/open/auth")
public class AuthController {

    /**
     * 登录时存储短信验证码到缓存中的key
     */
    private final static String SMS_CODE_CACHE_KEY = "sms:code";

    private final static Integer expireIn = Constant.AUTH.ACCESS_TOKEN_EXPIRE;

    private final static Integer SMS_TIMEOUT = 180;

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserApi userApi;

    private final Random random = new Random();

    @PostMapping("/login/account")
    public R<LoginVO> accountLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountLoginDTO body){

        UserSimpleBody param = new UserSimpleBody();
        param.setUsername(body.getUsername());
        R<User> resp = userApi.findOne(param);
        User user = resp.getData();

        String password = body.getPassword();
        Boolean f = passwordEncoder.matches(password, user.getPassword());
        Assert.isTrue(f, 30001, "用户名密码错误");

        Authentication auth = new Authentication();
        auth.setUserId(user.getId());
        auth.setUsername(user.getUsername());
        auth.setPhoneNum(user.getPhoneNum());
        auth.setAvatarUrl(user.getAvatarUrl());

        SessionContext context = SessionContextHolder.setAuthentication(auth);

        LoginVO vo = new LoginVO();
        vo.setAccessToken(context.getAccessToken());
        vo.setRefreshToken(context.getRefreshToken());
        vo.setExpireIn(expireIn);

        return R.success(vo);
    }

    @PostMapping("/login/sms")
    public R<LoginVO> smsLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody SmsLoginDTO body){

        String phoneNum = body.getPhoneNum();
        Integer smsCode = body.getSmsCode();

        // 查找用户
        UserSimpleBody param = new UserSimpleBody();
        param.setPhoneNum(phoneNum);
        R<User> resp = userApi.findOne(param);
        User user = resp.getData();

        // 验证短信验证码
        Integer smsCodeCache = (Integer) cacheUtil.get(SMS_CODE_CACHE_KEY + ":" + phoneNum);
        Assert.isTrue(smsCode.equals(smsCodeCache), 30001, "验证码错误");

        Authentication auth = new Authentication();
        auth.setUserId(user.getId());
        auth.setUsername(user.getUsername());
        auth.setPhoneNum(user.getPhoneNum());
        auth.setAvatarUrl(user.getAvatarUrl());
        SessionContext context = SessionContextHolder.setAuthentication(auth);

        LoginVO vo = new LoginVO();
        vo.setAccessToken(context.getAccessToken());
        vo.setRefreshToken(context.getRefreshToken());
        vo.setExpireIn(expireIn);

        return R.success(vo);
    }

    @PostMapping("/send/sms")
    public R sendSms(@RequestParam("phoneNum") String phoneNum){
        Integer smsCode = random.nextInt(899999)+ 100000;
        cacheUtil.set(SMS_CODE_CACHE_KEY + ":" + phoneNum, smsCode, SMS_TIMEOUT);
        log.debug("生成短信验证码：{}, 有效期:{}妙", smsCode, SMS_TIMEOUT);
        return R.success(smsCode);
    }

}
