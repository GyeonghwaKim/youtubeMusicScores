package com.example.youtubeSheet.preSecurity;

import com.example.youtubeSheet.entity.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


//@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession httpSession=request.getSession(false);

        if(httpSession!=null&& httpSession.getAttribute("role")!=null) return true;

        else throw new UnauthorizedException("인증되지 않은 사용자입니다");
    }
}
