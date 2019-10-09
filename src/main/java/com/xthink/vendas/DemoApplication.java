package com.xthink.vendas;

import com.xthink.domain.Salesman;
import com.xthink.repositories.SalesmanRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// TODO - Application do not automatic recognized Beans 
@SpringBootApplication(scanBasePackages = {"com.xthink.repositories", 
"com.xthink.configuration", "com.xthink.vendas"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(SalesmanRepository repository) {
		return args -> {
			repository.save(new Salesman("Joao", "Ricardo"));
		};
	}
}
