package com.bitstudy.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/*
* @Configuration : 설정파일 만들기 위한 에너테이션 or Bean으로 등록하기 위한 에너테이션
* */
@Configuration

/*
* @EnableJpaAuditing : jpa에서 자동으로 세팅하게 해줄때 사용하는 에너테이션
* */
@EnableJpaAuditing
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("bitstudy");
        //TODO: 나중에 스프링 시큐리티로 인증기능 붙일때 수정필요
    }
}
