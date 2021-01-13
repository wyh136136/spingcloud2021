package com.wyh.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wyh.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "get_global_Handle")
public class OrderHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;
    @GetMapping("customer/hystrix/ok/{id}")
    public String getPaymentInfo_OK(@PathVariable("id") Integer id){
        String paymentInfo_ok = this.paymentHystrixService.getPaymentInfo_OK(id);
        log.info("****"+paymentInfo_ok);
        return paymentInfo_ok;
    }
    //=========服务降级
//    @HystrixCommand(fallbackMethod = "getPaymentInfo_Handle",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
//    })
    @HystrixCommand
    @GetMapping("customer/hystrix/timeout/{id}")
    public String getPaymentInfo_timeout(@PathVariable("id") Integer id){
        int i=10/0;
        String paymentInfo_timeout = this.paymentHystrixService.getPaymentInfo_timeout(id);
        log.info("****"+paymentInfo_timeout);
        return paymentInfo_timeout;
    }

    public String getPaymentInfo_Handle(@PathVariable("id") Integer id){
        return "我是消费者，对方8001系统繁忙请稍后再试或者检查自己是否出错，o(╥﹏╥)o";
    }

    //全局fallback
    public String get_global_Handle(){
        return "全局异常消息，请稍后再试、、、o(╥﹏╥)o";
    }




}
