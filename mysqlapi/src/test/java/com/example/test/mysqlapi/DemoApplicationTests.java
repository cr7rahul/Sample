package com.example.test.mysqlapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.annotation.Rollback;


@SpringBootApplication
@EnableJpaAuditing
@Rollback(value=false)
public class DemoApplicationTests {

	public static void main(String[] args){
		SpringApplication.run(DemoApplication.class, args);
	}

}
