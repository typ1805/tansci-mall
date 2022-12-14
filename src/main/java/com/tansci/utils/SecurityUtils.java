package com.tansci.utils;

import com.tansci.domain.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @ClassName： SecurityUtils.java
 * @ClassPath： com.tansci.utils.SecurityUtils.java
 * @Description： 用户登录信息
 * @Author： tanyp
 * @Date： 2022/03/29 16:14
 **/
@Data
public class SecurityUtils implements UserDetails {

    private String id;

    private String username;

    private String nickname;

    private String password;

    private Integer type;

    private String roleId;

    private String shopId;

    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUtils() {
    }

    public SecurityUtils(SysUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.type = user.getType();
        this.roleId = user.getRoleId();
        this.shopId = user.getShopId();
        this.authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 账号是否未过期，默认是false
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 账号是否未锁定，默认是false
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 账号凭证是否未过期，默认是false
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
