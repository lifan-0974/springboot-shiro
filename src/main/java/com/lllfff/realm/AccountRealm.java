package com.lllfff.realm;

import com.lllfff.enity.Account;
import com.lllfff.service.impl.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class AccountRealm extends AuthorizingRealm {
    @Autowired
    private AccountService accountService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取到登录信息
        Subject subject= SecurityUtils.getSubject();
        Account account=(Account) subject.getPrincipal();
        //设置角色信息
        Set<String> roles=new HashSet<>();
        roles.add(account.getRole());
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);
        //设置权限信息
       info.addStringPermission(account.getPerms());
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取token中的登录信息
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        //根据service Byname方法
        Account account=accountService.findbyname(token.getUsername());
        if (account!=null){
            //登录名正确 进行密码判断
            return  new SimpleAuthenticationInfo(account,account.getPassword(),getName());
            //密码错误登录失败报错IncorrectCredentialsException
        }
        //登录名不正确报错UnknownAccountException
        return null;
    }
}
