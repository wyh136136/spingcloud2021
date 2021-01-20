package com.wyh.springcloud.controller;

import com.wyh.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {
    @Autowired
    private IMessageProvider iMessageProvider;
    @GetMapping("/sendMessage")
    public  String send(){
      return  this.iMessageProvider.send();
    }
}
