package com.wyh.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentHystrixService {
    public String getPaymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   getPaymentInfo_OK,  id"+id+"O(∩_∩)O哈哈~";
    }
    @HystrixCommand(fallbackMethod = "getPaymentInfo_Handler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String getPaymentInfo_Timeout(Integer id){
        int timenumber=8;
//        //线程休眠三秒
//        try {
//            TimeUnit.MILLISECONDS.sleep(3000);
//        }catch (InterruptedException i){
//            i.printStackTrace();
//        }
        int i=10/0;
        return "线程池："+Thread.currentThread().getName()+"   getPaymentInfo_Timeout,  id"+id+"  耗时"+timenumber+"秒钟";
    }
    public String getPaymentInfo_Handler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"系统繁忙或运行异常，请稍后再试、、"+"o(╥﹏╥)o";
    }


    //===  服务熔断  ===
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled", value="true"),  // 是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),  //请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000"), // 时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="60"),  // 失败率达到多少后跳闸
            //整体意思：10秒内 10次请求，有6次失败，就跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        //模拟发生异常
        if(id < 0){
            throw new RuntimeException("*****id，不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数，请稍后再试....";
    }
}
