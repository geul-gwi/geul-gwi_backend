package com.posmosalimos.geulgwi.global.config;

import com.posmosalimos.geulgwi.global.error.FeingClientExceptionErrorDecoder;
import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableFeignClients(basePackages = "com.posmosalimos.geulgwi") // todo 패키지명 수정
@Import(FeignClientsConfiguration.class)
@RequiredArgsConstructor
public class FeignConfiguration {


    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
        // NONE, BASIC, HEADERS
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeingClientExceptionErrorDecoder();
    }

    @Bean
    public Retryer retryer(){
        // period : 실행 주기
        // maxAttempts : 최대 재시도 횟수
        return new Retryer.Default(1000, 2000, 3);
    }

}
