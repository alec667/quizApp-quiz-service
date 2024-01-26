package com.example.quizservice;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class QuizServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizServiceApplication.class, args);
    }

    @Bean
    public OpenAPI quizServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("quiz service API")
                        .description("quiz service for quizApp application")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDocs Wiki Documentation")
                        .url("https://springdoc.org/"));
    }

}
