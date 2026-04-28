package com.asule.research.dao;

import com.asule.research.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SysUserDao extends BaseMapper<SysUser> {

}

