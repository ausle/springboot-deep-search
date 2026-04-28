package com.asule.research.service.impl;

import com.asule.research.dao.SysUserDao;
import com.asule.research.entity.LoginUser;
import com.asule.research.entity.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        SysUser sysUser = sysUserDao.selectOne(queryWrapper);
        if (Objects.isNull(sysUser)){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new LoginUser(sysUser);
    }
}
