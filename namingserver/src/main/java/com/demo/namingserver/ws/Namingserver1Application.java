package com.demo.namingserver.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Namingserver1Application {

	public static void main(String[] args) {
		SpringApplication.run(Namingserver1Application.class, args);
	}

}
