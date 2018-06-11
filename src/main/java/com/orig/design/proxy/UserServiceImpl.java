package com.orig.design.proxy;

/**
 * 实际业务逻辑
 */
public class UserServiceImpl implements UserService{
    @Override
    public void addUser(String user) {
        System.out.println(String.format("add user:%s success", user));
    }
}
