package com.yungu.refined;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.matcher.ElementMatchers;

public class RuntimeTypeMethod {

    public static class HelloService {

        public String hello(int arg) {
            return "int :" + arg;
        }

        public String hello(String arg) {
            return "String:" + arg;
        }
    }

    public static class Intercept {

        @RuntimeType
        public static Object intercept(@RuntimeType Object arg) {
            System.out.println("Invoked method with: " + arg);
            if (arg instanceof Integer) {
                return arg + "";
            }
            return arg;
        }
    }

    public static Class<?> generate(Class<?> supperClass) {
        DynamicType.Unloaded<?> type = new ByteBuddy()
                .subclass(supperClass)
                .method(ElementMatchers.named("hello"))
                .intercept(MethodDelegation.to(Intercept.class))
                .make();
        return type.load(Thread.currentThread().getContextClassLoader()).getLoaded();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Class<?> generate = generate(HelloService.class);
        HelloService newInstance = (HelloService) generate.newInstance();
        newInstance.hello(1);
        newInstance.hello("a");
    }
}
