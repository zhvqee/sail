package org.example;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class User extends SObject {

    public String userName;

    public User(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                '}';
    }

    @Override
    public String say(String hello) {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        //父类或接口方法
        MethodHandle say = null;
        try {
            say = lookup.findSpecial(SObject.class, "say", MethodType.methodType(String.class, String.class), User.class);
            return (String) say.bindTo(this).invokeExact("hello word");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }

    public String mixi() {
        return "mixi";
    }

    public static String run() {
        System.out.println("run abc");
        return "run";
    }


}
