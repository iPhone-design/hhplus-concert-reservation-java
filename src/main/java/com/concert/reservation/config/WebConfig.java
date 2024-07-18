package com.concert.reservation.config;

import com.concert.reservation.presentation.interceptor.LoggerInterceptor;
import com.concert.reservation.presentation.interceptor.TokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoggerInterceptor loggerInterceptor;
    private final TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/concert/**")
                .addPathPatterns("/payment/**")
                .excludePathPatterns("/token/**")        // 토큰 발급, 조회 제외
                .excludePathPatterns("/customer/**");    // 잔액 충전, 조회 제외
    }
}