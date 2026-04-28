package com.asule.research.service.impl;

import com.asule.research.dao.SysUserDao;
import com.asule.research.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2026-04-28 20:18:45
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

}
