package com.wyh.springcloud.dao;

import com.wyh.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {
    int deleteByPrimaryKey(Long id);

    int insert(Payment record);


    Payment selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Payment record);
}