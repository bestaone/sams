package cn.webestar.sams.apisvc.oms.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "API", description = "API")
@RestController
public class ApiController {

    @Value("${my.test}")
    private String test = "ok";

    @GetMapping("/api/one")
    public String getApi1(HttpServletRequest request){
        return test;
    }

}
