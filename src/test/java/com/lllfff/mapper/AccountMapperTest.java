package com.lllfff.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AccountMapperTest {
    @Autowired
  private  AccountMapper mapper;
    @Test
    void  test(){
        mapper.selectList(null).forEach(System.out::println);
    }

}