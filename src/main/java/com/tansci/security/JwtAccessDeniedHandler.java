package com.tansci.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName： JwtAccessDeniedHandler.java
 * @ClassPath： com.tansci.security.JwtAccessDeniedHandler.java
 * @Description： 没有访问权限
 * @Author： tanyp
 * @Date： 2022/03/29 16:14
 **/
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(WrapMapper.wrap(Wrapper.AUTHORIZATION_CODE, Wrapper.AUTHORIZATION_MESSAGE, null)));
    }

}
