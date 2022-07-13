package com.tansci.security;

import com.tansci.common.Enums;
import com.tansci.exception.BusinessException;
import com.tansci.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

/**
 * @ClassName： JwtAuthenticationTokenFilter.java
 * @ClassPath： com.tansci.security.JwtAuthenticationTokenFilter.java
 * @Description： JWT登录授权过滤器
 * @Author： tanyp
 * @Date： 2022/03/29 16:14
 **/
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        if (Objects.nonNull(tokenHeader) && tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
            if (JwtTokenUtils.isExpiration(token)) {
                throw new BusinessException(Enums.AUTH_NO_TOKEN.getValue());
            } else {
                String username = JwtTokenUtils.getUsername(token);
                if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(JwtTokenUtils.getUserRole(token))));
                    authentication.setDetails(userDetailsService.loadUserByUsername(username));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }

}