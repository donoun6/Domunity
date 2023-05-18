package hello.domunity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@EnableWebSecurity//시큐리티 필터가 적용
public class MySecurityConfig  {

    @Autowired
//    /* 로그인 실패 핸들러 의존성 주입 */
    private AuthenticationFailureHandler customFailureHandler;

    @Autowired
    private PrincipalService principalService;

    @Bean
    public BCryptPasswordEncoder encoderPWD() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalService).passwordEncoder(encoderPWD());
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .ignoringAntMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/css/**", "/image/**", "/js/**","/","/domunity", "/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/signin")
                .usernameParameter("memberId")
                .passwordParameter("memberPw")
                .loginProcessingUrl("/auth/signin")
                .failureHandler(customFailureHandler)
                .defaultSuccessUrl("/domunity")
                .permitAll()
                .and()
                .logout();
//                .invalidateHttpSession(true).deleteCookies("JESSIONID");

        return http.build();
    }
}




//        http
//                .csrf().disable() //scrf토큰 비활성화
//                .authorizeRequests()
//                .mvcMatchers("/", "/css/**", "/image/**", "/js/**")
//                .permitAll();
//        http
//                .authorizeRequests(authorize ->
//                        authorize
//                                .antMatchers("/","/domunity","/member/signup").permitAll() //권한이 필요하지 않는 주소패턴들
//                                //위에서 설정한 주소 이외에 나머지 주소들은 인증이 필요 == 로그인페이지로(권한 획득)
//                                .anyRequest().authenticated()
//                );
//
//        http.formLogin(login ->
//                login
//                        .loginPage("/member/signin")
//                        .usernameParameter("memberId")
//                        .passwordParameter("memberPw")
//                        .loginProcessingUrl("/member/signin")
//                        .failureHandler(customFailureHandler)
//        );