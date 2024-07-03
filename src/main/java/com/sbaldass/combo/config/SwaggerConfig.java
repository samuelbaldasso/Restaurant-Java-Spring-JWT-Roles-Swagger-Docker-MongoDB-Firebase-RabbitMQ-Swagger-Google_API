package com.sbaldass.combo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Autowired
    private Environment environment;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(environment.getProperty("open-api.dev-url"));
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("samuelbaldasso93@gmail.com");
        contact.setName("Samuel Baldasso");
        contact.setUrl("https://github.com/samuelbaldasso");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Combo")
                .version("1.0")
                .contact(contact)
                .description("Este projeto é um backend para o sistema mobile Combo.")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
