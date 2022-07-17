package com.tansci.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName： SecurityUtils.java
 * @ClassPath： com.kuiper.tansci.utils.SecurityUtils.java
 * @Description： 用户登录信息
 * @Author： tanyp
 * @Date： 2022/03/29 16:14
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUserUtils {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static SecurityUtils getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (SecurityUtils) authentication.getDetails();
    }

    public static void logout() {
        SecurityContextHolder.clearContext();
    }

}
