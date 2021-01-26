package com.jk.polsource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EntityScan("model")
public class PolsourceApplication {

		public static void main(String[] args) {
		SpringApplication.run(PolsourceApplication.class, args);

	}

}
