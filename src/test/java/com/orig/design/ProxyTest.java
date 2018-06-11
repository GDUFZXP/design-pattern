package com.orig.design;

import com.orig.design.proxy.*;
import com.orig.design.util.ProxyGeneratorUtils;
import org.junit.Test;

/**
 * 代理模式测试类
 */
public class ProxyTest {
    /**
     * 测试静态代理
     */
    @Test
    public void testStaticProxy(){
        UserService userService = new UserServiceImpl();
        StaticProxy staticProxy = new StaticProxy(userService);
        staticProxy.addUser("xupeng.zhang");
    }

    /**
     * 测试JDK动态代理
     */
    @Test
    public void testJDKDynamicProxy(){
        UserService userService = new UserServiceImpl();
        RealInvocationHandler realInvocationHandler = new RealInvocationHandler(userService);
        UserService userProxy = realInvocationHandler.getProxy();
        userProxy.addUser("xupeng.zhang");
    }

    @Test
    public void testGetJDKProxyClassInfo(){
        UserService userService = new UserServiceImpl();
        RealInvocationHandler realInvocationHandler = new RealInvocationHandler(userService);
        UserService userProxy = realInvocationHandler.getProxy();
        System.out.println(userProxy.getClass().getName());
        System.out.println(userProxy.getClass().getSuperclass());
        System.out.println(userProxy.getClass().getInterfaces()[0].getName());
        ProxyGeneratorUtils.saveJDKProxyClass("D:/JdkProxy.class", userProxy.getClass());
    }

    @Test
    public void testCglibProxy(){
        RealInterceptor realInterceptor = new RealInterceptor();
        UserRoleService userRoleProxy = realInterceptor.newProxyInstance(UserRoleService.class);
        userRoleProxy.addUserRole("xupeng.zhang","admin");
    }

    /**
     * 获取cglib代理类信息
     */
    @Test
    public void testGetCglibProxyClassInfo() throws Exception {
        RealInterceptor realInterceptor = new RealInterceptor();
        UserRoleService userRoleProxy = realInterceptor.newProxyInstance(UserRoleService.class);
        System.out.println(userRoleProxy.getClass().getName());
        System.out.println(userRoleProxy.getClass().getSuperclass());
        System.out.println(userRoleProxy.getClass().getInterfaces()[0].getName());
        ProxyGeneratorUtils.saveCglibProxyClass("D:/CglibProxy.class",realInterceptor.getEnhancer());
    }
}
