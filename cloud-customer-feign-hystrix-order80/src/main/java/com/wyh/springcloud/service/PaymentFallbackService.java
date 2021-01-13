package com.wyh.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements  PaymentHystrixService{
    @Override
    public String getPaymentInfo_OK(Integer id) {
        return "Fallback---getPaymentInfo_OK----o(╥﹏╥)o";
    }

    @Override
    public String getPaymentInfo_timeout(Integer id) {
        return "Fallback---getPaymentInfo_timeout----o(╥﹏╥)o";
    }
}
