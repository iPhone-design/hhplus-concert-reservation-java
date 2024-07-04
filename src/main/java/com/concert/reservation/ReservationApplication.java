package com.concert.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class ReservationApplication {

	public static void main(String[] args) {
		System.out.println("=========================================");
		System.out.println(UUID.randomUUID());
		System.out.println("=========================================");
		SpringApplication.run(ReservationApplication.class, args);
	}

}
