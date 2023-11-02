package com.posmosalimos.geulgwi.global.config.web;

import com.posmosalimos.geulgwi.global.interceptor.AdminAuthorizationInterceptor;
import com.posmosalimos.geulgwi.global.interceptor.AuthenticationInterceptor;
import com.posmosalimos.geulgwi.global.resolver.memberinfo.UserInfoArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    // accessToken 검증
    private final AuthenticationInterceptor authenticationInterceptor;
    // UserInfo 조회
    private final UserInfoArgumentResolver userInfoArgumentResolver;
    // Role ADMIN 체크하는 인터셉터
    private final AdminAuthorizationInterceptor adminAuthorizationInterceptor;



    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("**", "http://localhost:3000", "http://44.218.158.106:8000")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.OPTIONS.name())
                .allowedHeaders("*")
                .maxAge(3600); //Access-Control-Max-Age: 3600 으로 설정
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor) //accessToken 검증 우선
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/join",
                        "/user/login",
                        "/user/validate/**",
                        "/tag/list/**",
                        "/file",
                        "/oauth/kakao/callback",
                        "/email/**"
                );

        registry.addInterceptor(adminAuthorizationInterceptor)
                .order(2)
                .addPathPatterns(
                        "/user/admin/**",
                        "tag/admin/list",
                        "/challenge/admin/**"
                );

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

        resolvers.add(userInfoArgumentResolver); //유저 정보 탐색 리졸버 등록
    }
}
