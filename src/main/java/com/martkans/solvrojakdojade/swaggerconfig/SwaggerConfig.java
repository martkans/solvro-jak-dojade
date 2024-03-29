package com.martkans.solvrojakdojade.swaggerconfig;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .pathMapping("/")
                .apiInfo(metaData());
    }


    private ApiInfo metaData() {
        Contact contact = new Contact("Martin Kansik", "https://github.com/martkans", "martkans@outlook.com");

        return new ApiInfo(
                "JaKDojade in Solvro City",
                "Solvro recruitment task",
                "1.0.0",
                "Terms of service URL",
                contact,
                "Apache License Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html",
                new ArrayList<>()
        );
    }
}
