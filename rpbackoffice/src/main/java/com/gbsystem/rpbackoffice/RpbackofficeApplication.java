package com.gbsystem.rpbackoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RpbackofficeApplication extends SpringBootServletInitializer {
	
	@Override  
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)   
	{
		return application.sources(RpbackofficeApplication.class);  
	}  
	public static void main(String[] args) {
		SpringApplication.run(RpbackofficeApplication.class, args);
	}
	
	
}
