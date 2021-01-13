package com.wyh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient//注册进eureka
public class GatewayMain9527 {
    public static void main(String[] args) {
            SpringApplication.run(GatewayMain9527.class,args);
     }
}
