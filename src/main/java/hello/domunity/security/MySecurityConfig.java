package hello.domunity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@EnableWebSecurity//시큐리티 필터가 적용
public class MySecurityConfig {

    //로그인 실패 핸들러 의존성 주입
    @Autowired
    private AuthenticationFailureHandler customFailureHandler;

    @Autowired
    private PrincipalService principalService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

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

        return http.build();
    }
}
