package com.diego.todoneo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TodoneoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoneoApplication.class, args);
	}

}
