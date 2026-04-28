package com.asule.research.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
public class LoginUser implements UserDetails {

    private SysUser sysUser;

    /**
     * 通过系统用户对象构造登录用户。
     *
     * @param sysUser 系统用户信息
     */
    public LoginUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    /**
     * 返回当前用户拥有的权限集合。
     *
     * @return 权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 返回用于认证的密码。
     *
     * @return 用户密码
     */
    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    /**
     * 返回用于认证的用户名。
     *
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    /**
     * 账号是否未过期。
     *
     * @return true 表示未过期，false 表示已过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * 账号是否未被锁定。
     *
     * @return true 表示未锁定，false 表示已锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 凭证（通常是密码）是否未过期。
     *
     * @return true 表示凭证未过期，false 表示凭证已过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 当前账号是否可用。
     *
     * @return true 表示可用，false 表示不可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 获取底层系统用户对象。
     *
     * @return 系统用户信息
     */
    public SysUser getSysUser() {
        return sysUser;
    }
}
