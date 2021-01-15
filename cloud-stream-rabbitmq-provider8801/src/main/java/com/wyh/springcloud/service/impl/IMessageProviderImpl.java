package com.wyh.springcloud.service.impl;

import cn.hutool.core.lang.UUID;
import com.wyh.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class)  //建立消息通道
public class IMessageProviderImpl implements IMessageProvider {
    @Autowired
    private MessageChannel output;
    @Override
    public String send() {
        String s = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(s).build());
        System.out.println("ssssss====="+s);
        return "";
    }
}
