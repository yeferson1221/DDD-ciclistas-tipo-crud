package org.sofka.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CyclingApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/cycling");
		SpringApplication.run(CyclingApplication.class, args);
	}

}
