package com.example.comumnity.global.config;


import com.example.comumnity.domain.user.domain.Role;
import com.example.comumnity.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*"); // 허용할 URL
        configuration.addAllowedHeader("Bearer"); // 허용할 Header
        configuration.addAllowedMethod("*"); // 허용할 http 메서드
        configuration.setAllowCredentials(true); // 클라이언트에서 쿠키받기 true
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable()
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 안함 -> jwt 토큰을 사용하기 때문. -> 세션을 사용하지 않기 때문에 Authentication / Authorization 문제가 생김
                .authorizeRequests()
                .antMatchers("api/v1/**").permitAll()
//                .antMatchers("/swagger-ui.html/**").permitAll()
//                .antMatchers("/api/v1/user/signup/**").permitAll()
//                .antMatchers("/api/v1/user/login/**").permitAll()
//                .antMatchers(("/greeting")).hasRole(Role.USER.name())
//                .antMatchers("/api/v1/user/info/**").hasRole(Role.USER.name())
//                .antMatchers("/greeting").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
//                .and()

        ;

    }

}