package com.yungu.refined;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.yungu.nonamed.SupperClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CreateMethod {

    public static Class<?> generate(Class<?> supperClass) {
        DynamicType.Unloaded<?> type = new ByteBuddy()
                .subclass(supperClass)
                .method(ElementMatchers.named("hello"))
                .intercept(FixedValue.value("hello world!!!"))
                .make();
        return type.load(Thread.currentThread().getContextClassLoader()).getLoaded();
    }

    public static Class<?> generate2(Class<?> supperClass) {
        DynamicType.Unloaded<?> type = new ByteBuddy()
                .subclass(supperClass)
                .defineMethod("hello2", String.class, Modifier.PUBLIC)
                .intercept(FixedValue.value("new hello2"))
                .make();
        return type.load(Thread.currentThread().getContextClassLoader()).getLoaded();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> generate = generate(SupperClass.class);
        SupperClass instance = (SupperClass) generate.newInstance();
        String hello = instance.hello();
        System.out.println(hello);

        Class<?> generate2 = generate2(SupperClass.class);
        Object o = generate2.newInstance();
        Method hello2 = generate2.getDeclaredMethod("hello2", null);
        Object invoke = hello2.invoke(o, null);
        System.out.println(invoke);
    }
}
