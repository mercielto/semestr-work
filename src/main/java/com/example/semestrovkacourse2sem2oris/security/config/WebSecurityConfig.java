package com.example.semestrovkacourse2sem2oris.security.config;

import com.example.semestrovkacourse2sem2oris.model.Role;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry.requestMatchers(
                            "/authentication/**", "/error", "/main/**", "/main", "/post/**", "/chapter/**"
                    ).permitAll();

                    authorizationManagerRequestMatcherRegistry.requestMatchers(
                            "/styles/*", "/scripts/*", "/images/*", "/uploads/**"
                    ).permitAll();

                    authorizationManagerRequestMatcherRegistry.requestMatchers(
                            "/admin/**", "/swagger-ui/index.html"
                    ).hasAuthority(Role.ADMIN.name());

                    authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                })

                .formLogin(
                        httpSecurityFormLoginConfigurer -> {
                            httpSecurityFormLoginConfigurer.loginPage("/authentication/sign-in");
                            httpSecurityFormLoginConfigurer.usernameParameter("login");
                            httpSecurityFormLoginConfigurer.passwordParameter("password");
                            httpSecurityFormLoginConfigurer.defaultSuccessUrl("/main");
                            httpSecurityFormLoginConfigurer.failureUrl("/error");
                        }
                )

                .logout(httpSecurityLogoutConfigurer -> {
                    httpSecurityLogoutConfigurer.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));
                    httpSecurityLogoutConfigurer.invalidateHttpSession(true);
                    httpSecurityLogoutConfigurer.logoutSuccessUrl("/main");
                    httpSecurityLogoutConfigurer.deleteCookies("JSESSIONID");
                })

                .rememberMe(httpSecurityRememberMeConfigurer ->
                        httpSecurityRememberMeConfigurer.rememberMeParameter("rememberMe")
                                .tokenRepository(persistentTokenRepository()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
