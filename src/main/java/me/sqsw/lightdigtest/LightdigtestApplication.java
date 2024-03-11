package me.sqsw.lightdigtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCaching
@EnableFeignClients(basePackages = {"me.sqsw.lightdigtest.client"})
public class LightdigtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightdigtestApplication.class, args);
	}

}
