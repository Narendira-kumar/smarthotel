package com.demo.hm.ws;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	 Contact contact = new Contact(
	            "Smart Hotel ",
	            " ", 
	            ""
	    );
	    
		ApiInfo apiInfo = new ApiInfo(
				"Restful Web Service endpoints",
				" ", 
				"1.0",
				"Terms of service", 
				contact, 
				"License of API",
				"API License URL", 
				Collections.emptyList());

		@Bean
		public Docket apiDocket() {

			Docket docket = new Docket(DocumentationType.SWAGGER_2)
					.protocols(new HashSet<>(Arrays.asList("HTTP","HTTPs")))
					.apiInfo(apiInfo)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.demo.hm.ws")).paths(PathSelectors.any())
					.build();

			return docket;

		}
}
