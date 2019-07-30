package com.sanju.is;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class IsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsApplication.class, args);
	}

}
