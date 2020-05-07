package org.liu.proxy.cglibdynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyCglibProxy implements MethodInterceptor{
    
    private Object target;
    
    public Object getInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setInterfaces(null);
        // 回调代理方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("do something before invoke target");
        //下面两种调用方式都是正确的
        Object result = proxy.invokeSuper(obj, args);
//        Object result = method.invoke(target, args);
        System.out.println("do something after invoke target");
        return result;
    }

}
