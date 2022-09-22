package com.jalasoft.bootcamp.setting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SettingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettingApplication.class, args);
	}

}
