package com.ezen.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class JpaApplication {
	
	@Primary
	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
		
	}

	@Bean
    ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


}
