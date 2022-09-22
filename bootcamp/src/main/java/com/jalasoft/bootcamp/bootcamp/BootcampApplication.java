package com.jalasoft.bootcamp.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class BootcampApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BootcampApplication.class, args);
    }
}
