package com.orig.design.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 * 和JDK动态代理不同
 * 是基于继承实现
 */
public class RealInterceptor implements MethodInterceptor{
    private Enhancer enhancer = new Enhancer();

    /**
     * 具体执行的回调方法
     * @param o
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Long currentTime = System.currentTimeMillis();
        System.out.println("begin to execute cglib proxy to add user");
        System.out.println(String.format("method: %s", method.getName()));
        Object result = methodProxy.invokeSuper(o, args);//注意，这里是invokeSuper
        System.out.println(String.format("execute cglib proxy end, cost: %s ms", System.currentTimeMillis()-currentTime));
        return result;
    }

    /**
     * 设置代理的父类
     * 设置回调方法
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T newProxyInstance(Class<T> tClass){
        enhancer.setSuperclass(tClass);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    public Enhancer getEnhancer(){
        return enhancer;
    }
}
