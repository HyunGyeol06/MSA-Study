package com.msastudy.money;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final String API_NAME = "MSA_Study";

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title(API_NAME);
        return new OpenAPI()
                .info(info);
    }
}
