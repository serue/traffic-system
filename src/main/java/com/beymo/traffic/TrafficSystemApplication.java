package com.beymo.traffic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TrafficSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficSystemApplication.class, args);
	}

}
