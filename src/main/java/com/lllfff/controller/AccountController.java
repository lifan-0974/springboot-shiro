package com.lllfff.controller;

import com.lllfff.enity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {
    //页面跳转
    @GetMapping("{url}")
    public  String redirect(@PathVariable("url") String url){
        return  url;
    }
    //登录
    @PostMapping("login")
    public String login(String username , String password , Model model){
        //将登录的值放入token
        Subject subject= SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            //登录成功
            subject.login(token);
            //获取登录的值传入session
            Account account=(Account) subject.getPrincipal();
            subject.getSession().setAttribute("account",account);
            return "index";
        }catch (UnknownAccountException e){
            //用户不存在
            e.printStackTrace();
            model.addAttribute("msg","用户不存在!");
            return "login";
        }catch (IncorrectCredentialsException e){
            //密码错误
            e.printStackTrace();
            model.addAttribute("msg","密码错误!");
            return "login";
        }
    }
    //权限不足界面
    @GetMapping("unauth")
    @ResponseBody
    public String unauth(){
     return "未授权 无法访问！";
    }
    //退出登录
    @GetMapping("loginout")
    public String loginout(){
        Subject subject= SecurityUtils.getSubject();
        subject.logout();
        return "login";

    }
}
