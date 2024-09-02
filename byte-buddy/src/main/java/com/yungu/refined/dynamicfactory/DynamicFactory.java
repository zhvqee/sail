package com.yungu.refined.dynamicfactory;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;

import java.io.FileOutputStream;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.not;

public class DynamicFactory {
    public static class UserType {
        public String doSomething() {
            return null;
        }
    }

    public static interface Interceptor {
        String doSomethingElse();
    }

    public static interface InterceptionAccessor {
        Interceptor getInterceptor();

        void setInterceptor(Interceptor interceptor);
    }

    public static interface InstanceCreator {
        Object makeInstance();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {


        //创建一个类：extends UserType implements InterceptionAccessor
        DynamicType.Unloaded<UserType> userTypeUnloaded = new ByteBuddy()
                .subclass(UserType.class)
                .name("UserTypeDynamic")
                .method(not(isDeclaredBy(Object.class)))
                //委派给interceptor 字段类的某个实例方法
                .intercept(MethodDelegation.toField("interceptor"))
                .defineField("interceptor", Interceptor.class, Visibility.PRIVATE)
                .implement(InterceptionAccessor.class).intercept(FieldAccessor.ofBeanProperty())
                .make();
        byte[] bytes = userTypeUnloaded.getBytes();
        printClass(bytes, "aa");

        Class<? extends UserType> dynamicUserType = userTypeUnloaded.load(DynamicFactory.class.getClassLoader())
                .getLoaded();

        System.out.println(dynamicUserType.getClassLoader());


     //   Thread.currentThread().setContextClassLoader(dynamicUserType.getClassLoader());


        DynamicType.Unloaded<InstanceCreator> make = new ByteBuddy()
                .subclass(InstanceCreator.class)
                .method(not(isDeclaredBy(Object.class)))
                .intercept(MethodDelegation.toConstructor(dynamicUserType))
                .make();
        byte[] bytes1 = make.getBytes();
        printClass(bytes1, "bb");
        InstanceCreator factory = make.load(dynamicUserType.getClassLoader()) //dynamicUserType.getClassLoader()
                .getLoaded().newInstance();
        System.out.println(factory.getClass().getClassLoader());


        printClass(bytes, "aa");

        class HelloWorldInterceptor implements Interceptor {
            @Override
            public String doSomethingElse() {
                return "Hello World!";
            }
        }

        UserType userType = (UserType) factory.makeInstance();
        ((InterceptionAccessor) userType).setInterceptor(new HelloWorldInterceptor());
        String doSomething = userType.doSomething();
        System.out.println(doSomething);
    }

    private static void printClass(byte[] bytes, String className) throws IOException {
        FileOutputStream stream = new FileOutputStream("/Users/user/Dowloads/workspace/sail/byte-buddy/target/" + className + ".class");
        stream.write(bytes);
        stream.flush();
        stream.close();
    }
}
