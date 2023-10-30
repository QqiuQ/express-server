package com.bobby.securityjwt.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: SwaggerConfig
 * @author: Bobby
 * @date: 10/30/2023
 **/
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customApi() {
        Contact bobby = new Contact()
                .email("vividbobby@163.com")
                .name("Bobby");

        Info info = new Info()
                .contact(bobby)
                .description("项目Api接口");

        return new OpenAPI().info(info).openapi("3.0.1");
    }
}
