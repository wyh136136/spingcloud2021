package com.wyh.springcloud.controller;

import com.wyh.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("payment/hystrix/ok/{id}")
    public String getPaymentInfo_OK(@PathVariable("id") Integer id){
        String paymentInfo_ok = this.paymentHystrixService.getPaymentInfo_OK(id);
        log.info("****"+paymentInfo_ok);
        return paymentInfo_ok;
    }
    @GetMapping("payment/hystrix/timeout/{id}")
    public String getPaymentInfo_timeout(@PathVariable("id") Integer id){
        String paymentInfo_timeout = this.paymentHystrixService.getPaymentInfo_Timeout(id);
        log.info("****"+paymentInfo_timeout);
        return paymentInfo_timeout;
    }

    //====服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        return this.paymentHystrixService.paymentCircuitBreaker(id);
    }
}
