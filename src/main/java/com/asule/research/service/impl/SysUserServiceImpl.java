package com.asule.research.service.impl;

import com.asule.research.common.BaseResponse;
import com.asule.research.common.ResultUtils;
import com.asule.research.dto.User;
import com.asule.research.entity.LoginUser;
import com.asule.research.service.SysUserService;
import com.asule.research.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public BaseResponse login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        // 使用AuthenticationManager对用户提交的用户名、密码(凭证)进行认证
        try {
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            if (Objects.isNull(authenticate)){
                throw new RuntimeException("用户名或密码错误");
            }
            Object principal = authenticate.getPrincipal();
            LoginUser loginUser = (LoginUser) principal;

            // 生成JWT TOKEN
            String token = jwtTokenUtil.generateToken(loginUser.getSysUser().getId(), loginUser.getUsername());

            long userId = jwtTokenUtil.getUserId(token);
            String username = jwtTokenUtil.getUsername(token);
            System.out.println("userId: " + userId);
            System.out.println("username: " + username);

            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            return ResultUtils.success(map);
        }catch (Exception e){
            return ResultUtils.error(500, e.getMessage());
        }


    }
}
