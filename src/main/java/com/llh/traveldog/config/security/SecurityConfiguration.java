package com.llh.traveldog.config.security;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URLS = {
        "/sign-api/login",
        "/sign-api/sign-up",
        "/sign-api/exception",
        "/v3/api-docs/**",
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/webjars/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, JwtTokenPrivider jwtTokenPrivider) throws Exception {
        httpSecurity.csrf((csrf) -> csrf.disable()).cors(withDefaults())
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.anyRequest().permitAll());

        //     httpSecurity.httpBasic(HttpBasicConfigurer::disable).csrf(CsrfConfigurer::disable);
        // httpSecurity.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // httpSecurity.authorizeHttpRequests(authorize -> authorize
        //     .requestMatchers(WHITE_LIST_URLS).permitAll()
        //     .requestMatchers(HttpMethod.GET, "/place/**").permitAll()
        //     .requestMatchers(HttpMethod.POST, "/place").hasRole("ADMIN")
        //     .requestMatchers(HttpMethod.PUT, "/place/**").hasRole("ADMIN")
        //     .requestMatchers(HttpMethod.DELETE, "/place/**").hasRole("ADMIN")
        //     .requestMatchers(HttpMethod.GET, "/review/**").permitAll()
        //     .anyRequest().hasAnyRole("ADMIN", "USER"));
        // httpSecurity.exceptionHandling(authenticationManager -> authenticationManager
        //     .accessDeniedHandler(new CustomAccessDeniedHandler())
        //     .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
        // httpSecurity.addFilterBefore(new JwtAuthenticationFilter(jwtTokenPrivider), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
