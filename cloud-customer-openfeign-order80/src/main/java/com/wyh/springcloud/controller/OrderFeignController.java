package com.wyh.springcloud.controller;

import com.wyh.springcloud.entities.CommonResult;
import com.wyh.springcloud.entities.Payment;
import com.wyh.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;
    @GetMapping("customer/feign/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getById(id);
    }
    @GetMapping("customer/payment/timeout")
    public String timeout() {
        return this.paymentFeignService.timeout();
    }

}
