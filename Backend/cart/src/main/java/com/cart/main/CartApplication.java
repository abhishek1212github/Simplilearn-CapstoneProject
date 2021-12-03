package com.cart.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.cart")
@EnableJpaRepositories(basePackages = "com.cart.dao")
@ComponentScan(basePackages = {"com.cart.controller","com.cart.service"})
@EntityScan(basePackages = "com.cart.bean")
public class CartApplication {

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	public static void main(String[] args) {

		SpringApplication.run(CartApplication.class, args);
	}

}
