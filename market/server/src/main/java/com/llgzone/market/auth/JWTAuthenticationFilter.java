package com.llgzone.market.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getParameter("token");
        if (token == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            JWTAuthenticationToken JWToken = new JWTAuthenticationToken(token);
            // 鉴定权限，如果鉴定失败，AuthenticationManager会抛出异常被我们捕获
            Authentication authResult = getAuthenticationManager().authenticate(JWToken);
            // 将鉴定成功后的Authentication写入SecurityContextHolder中供后序使用
            SecurityContextHolder.getContext().setAuthentication(authResult);
        } catch (AuthenticationException failed) {
            SecurityContextHolder.clearContext();
            // 返回鉴权失败
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, failed.getMessage());
            return;
        }
        chain.doFilter(request, response);
    }
}