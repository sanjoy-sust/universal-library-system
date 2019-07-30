package com.sanju.bm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BmApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmApplication.class, args);
	}

}
