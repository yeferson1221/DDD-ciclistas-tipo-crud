package org.sofka.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectTdfApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/projectTDF");
		SpringApplication.run(ProjectTdfApplication.class, args);
	}

}
