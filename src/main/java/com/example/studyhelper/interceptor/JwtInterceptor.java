package com.example.studyhelper.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.studyhelper.common.BusinessException;
import com.example.studyhelper.util.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new BusinessException(401, "未登录，请先登录");
        }

        String token = header.substring(7);
        if (!jwtUtil.validateToken(token)) {
            throw new BusinessException(401, "Token无效或已过期");
        }

        Claims claims = jwtUtil.parseToken(token);
        Object userIdObj = claims.get("userId");
        Long userId = userIdObj instanceof Number ? ((Number) userIdObj).longValue() : null;
        request.setAttribute("userId", userId);
        request.setAttribute("username", claims.getSubject());
        request.setAttribute("role", claims.get("role"));
        return true;
    }
}
