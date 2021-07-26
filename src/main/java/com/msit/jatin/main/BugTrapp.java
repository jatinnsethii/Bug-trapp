
package com.msit.jatin.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BugTrapp{

	public static void main(String[] args) {
		SpringApplication.run(BugTrapp.class, args);
	}

}