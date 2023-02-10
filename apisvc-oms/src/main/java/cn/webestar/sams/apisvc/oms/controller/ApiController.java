package cn.webestar.sams.apisvc.oms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Value("${my.test}")
    private String test = "ok";

    @GetMapping("/api/one")
    public String getApi1(){
        return test;
    }

}
