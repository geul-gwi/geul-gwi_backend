package com.posmosalimos.geulgwi.global.resolver.memberinfo;

import com.posmosalimos.geulgwi.entity.Role;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenManager tokenManager;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // return값이 true이면, resolveArgument가 실행됨
        boolean hasMemberInfoAnnotation = parameter.hasParameterAnnotation(UserInfo.class);
        boolean hasMemberInfoDto = UserInfoDto.class.isAssignableFrom(parameter.getParameterType());
        return hasMemberInfoAnnotation && hasMemberInfoDto;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.split(" ")[1];

        Claims tokenClaims = tokenManager.getTokenClaims(token);
        Long id = Long.valueOf((Integer) tokenClaims.get("id"));
        String role = (String) tokenClaims.get("role");

        return UserInfoDto.builder()
                .id(id)
                .role(Role.from(role))
                .build();
    }
}
