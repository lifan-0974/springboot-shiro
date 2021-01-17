package com.lllfff.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lllfff.enity.Account;
import com.lllfff.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//下方有test测试方法
public class AccountService implements com.lllfff.service.AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public Account findbyname(String username) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("username",username);
        return accountMapper.selectOne(wrapper);
    }
}
