package com.lllfff.enity;

import lombok.Data;

@Data
public class Account {
    private Integer id;
    private String username;
    private  String password;
    private  String perms;
    private  String role;
}
