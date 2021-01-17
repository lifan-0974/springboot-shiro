package com.lllfff.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lllfff.enity.Account;
import org.springframework.stereotype.Repository;

@Repository
//mybitsplus提供的方法类  下方有test测试方法
public interface AccountMapper extends BaseMapper<Account> {

}
