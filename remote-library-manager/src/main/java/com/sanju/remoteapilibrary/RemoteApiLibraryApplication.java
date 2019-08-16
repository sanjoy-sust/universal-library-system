package com.sanju.remoteapilibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RemoteApiLibraryApplication {
	public static void main(String[] args) {
		SpringApplication.run(RemoteApiLibraryApplication.class, args);
	}
}
