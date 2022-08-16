package com.contacts.ContactsRestApp;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableJpaRepositories(basePackages = { "com.contacts.ContactsRestApp.Repository" })
@SpringBootApplication
//Swagger için annotation
@EnableSwagger2
public class ContactsRestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactsRestAppApplication.class, args);
	}
	
	
	//Swagger icin com.contacts.ContactsRestApp servislerini aktarma
	 @Bean
	 public Docket swaggerApi() {
	  return new Docket(DocumentationType.SWAGGER_2).select()
	    .apis(RequestHandlerSelectors.basePackage("com.contacts.ContactsRestApp")).build();
	 }
	 //React gelistirme yaparken CORS hatasý aldým. Bu nedenle gelistirilen kod blogu
	 @Bean
		public WebMvcConfigurer configure() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/*").allowedOrigins("http://localhost:8080");
				}
				
			};
		}
}

