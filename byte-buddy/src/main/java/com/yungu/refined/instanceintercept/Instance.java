package com.yungu.refined.instanceintercept;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.FileOutputStream;
import java.io.IOException;

public class Instance {
    public static class UserType {
        public String doSomething() {
            return null;
        }
    }

    public static class Intercept {
        public String intercept() {
            System.out.println("intercept entry:" + this.toString());
            return "invoke intercept";
        }
    }


    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {


        Intercept intercept = new Intercept();
        System.out.println(intercept.toString());
        //创建一个类：extends UserType implements InterceptionAccessor
        DynamicType.Unloaded<UserType> userTypeUnloaded = new ByteBuddy()
                .subclass(UserType.class)
                .name("UserTypeDynamic")
                .method(ElementMatchers.<MethodDescription>named("doSomething"))
                //委派给interceptor 字段类的某个实例方法
                .intercept(MethodDelegation.to(intercept))
                .make();
        byte[] bytes = userTypeUnloaded.getBytes();
        printClass(bytes, "aacc");
        Class<? extends UserType> loaded = userTypeUnloaded.load(Instance.class.getClassLoader()).getLoaded();
        UserType userType = loaded.newInstance();
        userType.doSomething();
    }

    private static void printClass(byte[] bytes, String className) throws IOException {
        FileOutputStream stream = new FileOutputStream("/Users/user/Dowloads/workspace/sail/byte-buddy/target/" + className + ".class");
        stream.write(bytes);
        stream.flush();
        stream.close();
    }
}
