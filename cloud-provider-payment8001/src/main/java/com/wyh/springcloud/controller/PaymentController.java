package com.wyh.springcloud.controller;

import com.wyh.springcloud.dao.PaymentDao;
import com.wyh.springcloud.entities.CommonResult;
import com.wyh.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentDao paymentDao;
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private DiscoveryClient discoveryClient;
    @PostMapping(value="payment/create")
    public CommonResult<Payment> create(@RequestBody  Payment obj){
        int result = this.paymentDao.insert(obj);
        log.info("插入结果"+result);
        if(result>0){
            return new CommonResult(200,"插入成功serverport:"+serverPort,result);
        }else{
            return new CommonResult(444,"插入失败");
        }
    }
    @GetMapping(value="payment/getById/{id}")
    public CommonResult getById(@PathVariable("id") Long  id){
        Payment payment = this.paymentDao.selectByPrimaryKey(id);
        log.info("查询结果"+payment);
        if(payment!=null){
            return new CommonResult(200,"查询成功:serverport:"+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录");
        }
    }
    @GetMapping(value="discovery")
    public Object discovery(){
        List<String> services = this.discoveryClient.getServices();
        for (String service : services) {
            log.info("****************",service);
        }
        List<ServiceInstance> instances = this.discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("==========",instance.getHost()+"\\"+instance.getUri()+"\\"+instance.getPort());
        }
        return this.discoveryClient;
    }

    @GetMapping(value="getServerPort")
    public String  getServerPort(){

        return this.serverPort;
    }
    @GetMapping("payment/timeout")
    public String timeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException i){
            i.printStackTrace();
        }
        return this.serverPort;

    }

}
