package com.kevin.pharmacyapi.pharmacyapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Farmacia")
                        .description("API para gestión de inventario y movimientos de stock en farmacia")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Kevin")
                                .url("https://github.com/Kevin2407/Pharmacy_back")
                                .email("tu@email.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}