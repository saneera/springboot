package com.springboot.rest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@SpringBootApplication	
public class AppMain extends SpringBootServletInitializer {

	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppMain.class);
    }

	public static void main(String[] args) throws Exception {
        SpringApplication.run(AppMain.class, args);
    }
	
	@Bean
	public InternalResourceViewResolver setupViewResolver()  {
	        InternalResourceViewResolver resolver =  new InternalResourceViewResolver();
	        resolver.setPrefix ("/WEB-INF/jsp/");
	        resolver.setSuffix (".jsp");
	        resolver.setViewClass (JstlView.class);
	        return resolver;
	    }
}
