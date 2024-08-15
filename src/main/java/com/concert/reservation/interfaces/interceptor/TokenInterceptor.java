package com.concert.reservation.interfaces.interceptor;

import com.concert.reservation.application.token.TokenFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    private final TokenFacade tokenFacade;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        if (url.contains("/concert") || url.contains("/payment")) {
            // 토큰 유효성 체크
            tokenFacade.checkActiveStatusFromRedis(request.getHeader("Authorization"));
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}