package com.asule.research.controller;

import com.asule.research.common.BaseResponse;
import com.asule.research.dto.User;
import com.asule.research.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class TestController {

    @GetMapping("/test")
    public String index() {
        return "test";
    }
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/user/login")
    public BaseResponse login(@RequestBody User user) {
        return sysUserService.login(user);
    }
}
