package org.example;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        //拿到构造器句柄
        MethodHandle constructor = lookup.findConstructor(User.class, MethodType.methodType(void.class, String.class));
        User user = (User) constructor.invokeExact("userName");
        System.out.println(user);
        String helloWord = user.say("hello word");
        System.out.println(helloWord);

        MethodHandle say = lookup.findVirtual(User.class, "say", MethodType.methodType(String.class, String.class));
        String helloWord2 = (String) say.bindTo(user).invokeExact("hello word2");
        System.out.println(helloWord2);

        MethodHandle run = lookup.findStatic(User.class, "run", MethodType.methodType(String.class));
        System.out.println(run.invoke());


        MethodHandle mixi = lookup.findVirtual(User.class, "mixi", MethodType.methodType(String.class));
        Object invoke = mixi.bindTo(user).invoke();
        System.out.println(invoke);

    }
}
