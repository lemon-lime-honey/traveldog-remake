package com.llh.traveldog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@EnableWebMvc
@OpenAPIDefinition(info = @Info(title = "TravelDog: Remake", description = "TravelDog: Remake"))
public class SwaggerConfiguration implements WebMvcConfigurer {
}
