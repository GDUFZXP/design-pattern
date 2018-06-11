package com.orig.design.proxy;

/**
 * 静态代理类
 */
public class StaticProxy implements UserService{
    private UserService userService;

    public StaticProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addUser(String user) {
        //统计耗时
        Long executeTime = System.currentTimeMillis();
        System.out.println("begin to execute static proxy to add user......");
        userService.addUser(user);
        System.out.println(String.format("execute static proxy to add user end. cost %s ms", System.currentTimeMillis() - executeTime));
    }
}
