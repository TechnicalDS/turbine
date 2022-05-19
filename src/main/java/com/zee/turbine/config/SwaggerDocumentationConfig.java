package com.zee.turbine.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Saba Imteyaz
 * @Date 15/03/22
 */

@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Turbine(Business layer)- OpenAPI 3.0")
                        .description("API documentation for Turbine (Payment aggregator Service) of zee5")
                        .termsOfService("")
                        .version("0.0.1")
                        .license(new License()
                                .name("")
                                .url("http://unlicense.org"))
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .email("saba.imteyaz@zee.com")));
    }

}
