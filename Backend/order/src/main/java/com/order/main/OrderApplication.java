package com.order.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com")
@EnableJpaRepositories(basePackages = "com.order.dao")
@EntityScan(basePackages = "com.order.bean")
@ComponentScan(basePackages = {"com.order.service","com.order.controller"})
public class OrderApplication {

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	public static void main(String[] args) {
		
		SpringApplication.run(OrderApplication.class, args);
	}

}
