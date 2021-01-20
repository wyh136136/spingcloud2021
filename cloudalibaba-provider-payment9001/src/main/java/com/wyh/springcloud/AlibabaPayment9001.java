package com.wyh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //注册服务
public class AlibabaPayment9001 {
    public static void main(String[] args) {
            SpringApplication.run(AlibabaPayment9001.class,args);
     }
}
