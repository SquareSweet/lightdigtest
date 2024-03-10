package me.sqsw.lightdigtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LightdigtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightdigtestApplication.class, args);
	}

}
