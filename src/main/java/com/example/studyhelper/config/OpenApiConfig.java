package com.example.studyhelper.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String AUTH_SCHEME = "Bearer";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("StudyHelper 自习助手 API")
                        .description("自习助手后端接口文档 - 基于 Spring Boot 3 + MyBatis-Plus + JWT")
                        .version("1.0.0")
                        .contact(new Contact().name("StudyHelper").email("study@example.com")))
                .addSecurityItem(new SecurityRequirement().addList(AUTH_SCHEME))
                .schemaRequirement(AUTH_SCHEME, new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization"));
    }
}
