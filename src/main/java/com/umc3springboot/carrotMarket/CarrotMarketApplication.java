package com.umc3springboot.carrotMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CarrotMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrotMarketApplication.class, args);
	}

}
