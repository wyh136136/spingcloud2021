package com.wyh.springcloud.service;

import com.wyh.springcloud.entities.CommonResult;
import com.wyh.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value="CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping(value="payment/getById/{id}")
    public CommonResult getById(@PathVariable("id") Long  id);
    @GetMapping("payment/timeout")
    public String timeout();
}
