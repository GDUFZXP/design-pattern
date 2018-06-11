package com.orig.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 实际执行处理类
 */
public class RealInvocationHandler implements InvocationHandler{
    private Object target;

    public RealInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 最终代理对象会将请求转发到invoke方法执行
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Long executeTime = System.currentTimeMillis();
        System.out.println("begin to execute method in dynamic proxy...");
        Object result = method.invoke(target, args);
        System.out.println(String.format("execute method by dynamic proxy end, cost %s ms", System.currentTimeMillis() - executeTime));
        return result;
    }

    /**
     * 基于反射实现动态代理对象生成
     * @param <T>
     * @return
     */
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), this);
    }
}
