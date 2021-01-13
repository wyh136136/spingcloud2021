package com.wyh.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="CLOUD-PROVIDER-HYSTRIX-SERVICE",fallback = PaymentFallbackService.class)
@Component
public interface PaymentHystrixService {
    @GetMapping("payment/hystrix/ok/{id}")
    public String getPaymentInfo_OK(@PathVariable("id") Integer id);
    @GetMapping("payment/hystrix/timeout/{id}")
    public String getPaymentInfo_timeout(@PathVariable("id") Integer id);
}
