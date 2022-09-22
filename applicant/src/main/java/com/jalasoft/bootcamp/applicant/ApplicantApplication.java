package com.jalasoft.bootcamp.applicant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableCaching
public class ApplicantApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ApplicantApplication.class, args);
    }
}
