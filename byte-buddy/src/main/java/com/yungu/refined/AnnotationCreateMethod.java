package com.yungu.refined;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.*;
import net.bytebuddy.matcher.ElementMatchers;
import org.yungu.nonamed.SupperClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class AnnotationCreateMethod {

    public static class Intercept {

        //ths: 即被创建出来的对象
        public static String intercept(@Super SupperClass supperClass, @SuperCall Callable<String> caller, @Origin Method method, @This Object ths, @Argument(0) String arg) {
            System.out.println("intercept before");
            System.out.println(ths);
            System.out.println(method);


            System.out.println("supperClass:" + supperClass);
            System.out.println(supperClass.hello());
            try {
                String call = caller.call();
                System.out.println("origin-invoke:" + call);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("hello world:" + arg);
            System.out.println("intercept after");
            return arg;
        }
    }

    ;

    public static Class<?> generate(Class<?> supperClass) {
        DynamicType.Unloaded<?> type = new ByteBuddy()
                .subclass(supperClass)
                .method(ElementMatchers.named("hello").and(ElementMatchers.takesArguments(String.class)))
                .intercept(MethodDelegation.to(Intercept.class))
                .make();
        return type.load(Thread.currentThread().getContextClassLoader()).getLoaded();
    }


    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> generate = generate(SupperClass.class);
        SupperClass instance = (SupperClass) generate.newInstance();
        String hello = instance.hello("first");
        System.out.println(hello);
        System.out.println(instance);


    }
}
