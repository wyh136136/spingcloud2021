package com.wyh.springcloud.controller;


import com.wyh.springcloud.entities.CommonResult;
import com.wyh.springcloud.entities.Payment;
import com.wyh.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("consumer/payment/")
public class OrderController {
    //private static final String PAYMENT_URL="http://localhost:8001";
    private static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE/";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancer loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    //=================ForObject=============================
    @GetMapping("create")
    public CommonResult<Payment> create(Payment payment){
      return  restTemplate.postForObject(PAYMENT_URL+"payment/create",payment, CommonResult.class);
    }
    @GetMapping("getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long  id){
        return  restTemplate.getForObject(PAYMENT_URL+"payment/getById/"+id,CommonResult.class);
    }
    //=================ForEntity=============================
    @GetMapping("create2")
    public CommonResult<Payment> create2(Payment payment){
        ResponseEntity<CommonResult> commonResultResponseEntity = restTemplate.postForEntity(PAYMENT_URL + "payment/create", payment, CommonResult.class);
        return  commonResultResponseEntity.getBody();
    }
    @GetMapping("getPayment2/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long  id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "payment/getById/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else{
            return new CommonResult<>(444,"查询失败");
        }
    }

    @GetMapping("getLB")
    public String  getLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances==null || instances.size()<=0){
            return null;
        }
        ServiceInstance instance = this.loadBalancer.instance(instances);
        URI uri = instance.getUri();
        System.out.println("=========="+uri);
        return  restTemplate.getForObject(uri+"getServerPort",String.class);
    }
}
