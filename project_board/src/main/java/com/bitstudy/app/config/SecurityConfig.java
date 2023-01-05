package com.bitstudy.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/** spring security 를 설치하고 실행하면 무조건 로그인 화면으로 넘어감.
 *  "DefaultLoginPageGeneratingFilter" 파일 맨 위에 /login 이라고 써있음.
 *  아직 로그인 기능이 없으니까 요청 url 대로 화면이 나타나게 할 거임.
 *
 * */
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*  HttpSecurity : 세부적인 보안 기능을 설정할 수 있는 api를 제공.
        *   - URL 접근 권한 설정
        *   - 커스텀 로그인 페이지 지원
        *   - 인증 후 성공 / 실패 핸들링
        *   - 사용자 로그인 / 로그아웃
        *   - CSRF(Cross Site Request Forgery) 공격으로부터 보호
        * */


        /** HttpSecurity 의 authorizeHttpRequests 에서 모든 요청 인증 허용(permitAll) */
        return http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())

                /** 로그인 페이지 만들기*/
                .formLogin()

                /**빌드하기*/
                .and().build();

        /*  formLogin() 뒤에 사용할 수 있는 메서드
            .loginPage("경로") : 커스텀 로그인 페이지
            .defaultSuccesUrl("경로") : 로그인 성공 후 이동할 페이지
            .failureUrl("경로") : 로그인 실패 후 이동할 페이지
            /usernameParameter("유저네임") : 아이디 파라미터명
        *
        * */
    }
}
