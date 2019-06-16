/*
 * Project: my-app-simple
 * 
 * File Created at 2013年12月11日 上午10:01:07
 * 
 * Copyright 2012 seaway.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Seaway Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with seaway.com.
 */
package org.liu.reflect;

import org.liu.jdk18.Person;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * reflection
 * <br>----------------------------------------------------变更记录--------------------------------------------------
 * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
 * <br> 0     | 2013年12月11日 上午10:01:07  	|  刘章盛     | 创建  
 */
public class TestReflection {

    /** 
     * reflection
     * @param args
     * <br>----------------------------------------------------变更记录--------------------------------------------------
     * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
     * <br> 0     | 2013年12月11日 上午10:01:07  	|  刘章盛     | 创建  
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
//        getClassName();
//        one();
//        two();
//        three();
//        four();
//        five();
//        six();
        seven();
        System.out.println("----------------------");
        eight();
        System.out.println("=======================================================");
        nine();
        System.out.println("=======================================================");
        ten();
        System.out.println("=======================================================");
        eleven();
    }
    
    public static void getClassName(){
        /***通过一个对象获得完整的包名和类名***/
        Person p = new Person();
        System.out.println("1--" + p.getClass().getName());
    }
    
    public static void one(){
        /***实例化Class类对象***/
        Class<?> demo1 = null;
        Class<?> demo2 = null;
        Class<?> demo3 = null;
        try {
            demo1 = Class.forName("com.liu.app.entity.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        demo2 = new Person().getClass();
        demo3 = Person.class;
        
        System.out.println("2--" + demo1.getName());
        System.out.println("2--" + demo2.getName());
        System.out.println("2--" + demo3.getName());
        
    }
    
    public static void two(){
        /***通过Class实例化其他类的对象***/
        Class<?> demo = null;
        Person per = null;
        try {
            demo = Class.forName("com.liu.app.entity.Person");
            per = (Person) demo.newInstance();
            per.setFirstName("a");
            per.setGender("man");
            System.out.println("3--" + per);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    public static void three(){
        /***通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象）***/
        Class<?> demo = null;
        try {
            demo = Class.forName("com.liu.app.entity.Person");
            Constructor<?>[] cons = demo.getConstructors();
            for (int i = 0; i < cons.length; i++) {
                System.out.println("4--" + cons[i]);
                Class<?>[] params = cons[i].getParameterTypes();
                Person p = null;
                if(params.length == 0){
                    p = (Person) cons[i].newInstance();
                    System.out.println("4--" + p);
                }else {
                    p = (Person) cons[i].newInstance("kobe","male");
                    System.out.println("4--" + p);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    public static void four(){
        /***返回一个类实现的接口***/
        Class<?> demo = null;
        try {
            demo = Class.forName("com.liu.app.entity.Person");
            Class<?>[] intes = demo.getInterfaces();
            for (Class<?> clazz : intes) {
                System.out.println("5--" + clazz.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void five(){
        /***取得其他类中的父类***/
        try {
            Class<?> demo = Class.forName("com.liu.app.entity.Person");
            Class<?> superClass = demo.getSuperclass();
            System.out.println("6--" + superClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void six(){
        /***获取构造函数的修饰符***/
        try {
            Class<?> demo = Class.forName("com.liu.app.entity.Person");
            Constructor<?>[] superClass = demo.getConstructors();
            for (Constructor<?> constructor : superClass) {
                System.out.println("7--1" + constructor.getName());
                int modifyers = constructor.getModifiers();
                //该行打印构造函数的修饰符
                System.out.println("7--2" + Modifier.toString(modifyers));
                Class<?>[] p = constructor.getParameterTypes();
                System.out.println("7--3参数类型{");
                for (int j=0;j<p.length;j++) {
                    System.out.print("7--" + p[j].getName());
                    if(j<p.length-1){
                        System.out.println(",");
                    }
                }
                System.out.println("}");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void seven(){
        /***获得一个类的全部方法***/
        try {
            Class<?> demo = Class.forName("com.liu.app.entity.Person");
            Method[] methods = demo.getMethods();
            for (int i = 0; i < methods.length; i++) {
                Class<?> returnType = methods[i].getReturnType();//方法的返回类型
                Class<?>[] params = methods[i].getParameterTypes();//方法的参数类型集合
                int temp = methods[i].getModifiers();//方法的修饰符
                System.out.print(Modifier.toString(temp) + " ");
                System.out.print(returnType.getName() + " ");
                System.out.print(methods[i].getName() + " ");
                System.out.print("(");
                for (int j = 0; j < params.length; j++) {
                    System.out.print(params[j].getName() + " arg" + j);
                    if(j < params.length - 1){
                        System.out.print(",");
                    }
                }
                Class<?>[] excep = methods[i].getExceptionTypes();
                if(excep.length > 0){
                    System.out.print(") throws ");
                    for (int j = 0; j < excep.length; j++) {
                        System.out.println(excep[j].getName()+" ");
                        if(j < excep.length - 1){
                            System.out.println(",");
                        }
                    }
                }else{
                    System.out.print(")");
                }
                System.out.println();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void eight(){
        /***取得类的全部属性***/
        Class<?> demo = null;
        try {
            demo = Class.forName("com.liu.app.entity.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===============本类属性========================");
        // 取得本类的全部属性
        Field[] field = demo.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // 权限修饰符
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[i].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + field[i].getName() + ";");
        }
        System.out.println("===============实现的接口或者父类的属性========================");
        // 取得实现的接口或者父类的属性
        Field[] filed1 = demo.getFields();
        for (int j = 0; j < filed1.length; j++) {
            // 权限修饰符
            int mo = filed1[j].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = filed1[j].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + filed1[j].getName() + ";");
        }
    }
    
    public static void nine(){
        /***通过反射调用其他类中的方法***/
        Class<?> demo = null;
        try {
            demo = Class.forName("com.liu.app.entity.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            //调用Person类中的say方法
            Method method=demo.getMethod("say");
            method.invoke(demo.newInstance());
            //调用Person的say(String content)方法
            method=demo.getMethod("say", String.class);
            method.invoke(demo.newInstance(),"I am JustingLiu");
             
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void ten(){
        /***调用其他类的set和get方法***/
        try {
            Class<?> demo = Class.forName("com.liu.app.entity.Person");
            Object obj = demo.newInstance();
            setter(obj,"Name","jesslie",String.class);
            getter(obj,"Name");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    private static void getter(Object obj,String attr){
        try {
            Method method = obj.getClass().getMethod("get" + attr);
            System.out.println(method.invoke(obj));
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    private static void setter(Object obj,String attr,Object value,Class<?> type){
        Method method;
        try {
            method = obj.getClass().getMethod("set" + attr,type);
            method.invoke(obj,value);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    public static void eleven() throws Exception{
        /***通过反射操作属性***/
        Class<?> demo = null;
        Object obj = null;
 
        demo = Class.forName("com.liu.app.entity.Person");
        obj = demo.newInstance();
 
        Field field = demo.getDeclaredField("name");
        field.setAccessible(true);
        field.set(obj, "wonderson");
        System.out.println(field.get(obj));
    }
    
    public static void twelve(){
        /***通过反射取得并修改数组的信息***/
        int[] temp = {1,2,3,4,5};
        Class<?> com = temp.getClass().getComponentType();
        System.out.println("数组类型:" + com.getName());
        System.out.println("数组长度:" + Array.getLength(temp));
        System.out.println("数组的第一个元素:" + Array.get(temp, 0));
        Array.set(temp, 0, 10);
        System.out.println("修改后数组的第一个元素:" + Array.getByte(temp, 0));
    }
    
    public static void thirteen(){
        int[] temp={1,2,3,4,5,6,7,8,9};
        int[] newTemp=(int[])arrayInc(temp,15);
        print(newTemp);
        System.out.println("=====================");
        String[] atr={"a","b","c"};
        String[] str1=(String[])arrayInc(atr,8);
        print(str1);
    }
    /**
     * 修改数组大小
     */
    public static Object arrayInc(Object obj,int len){
        Class<?> arr=obj.getClass().getComponentType();
        Object newArr=Array.newInstance(arr, len);
        int co=Array.getLength(obj);
        System.arraycopy(obj, 0, newArr, 0, co);
        return newArr;
    }
    /**
     * 打印
     */
    public static void print(Object obj){
        Class<?> c=obj.getClass();
        if(!c.isArray()){
            return;
        }
        System.out.println("数组长度为： "+Array.getLength(obj));
        for (int i = 0; i < Array.getLength(obj); i++) {
            System.out.print(Array.get(obj, i)+" ");
        }
    }

}
