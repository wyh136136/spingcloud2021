package com.wyh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(CustomerOrder80.class,args);
    }
}
