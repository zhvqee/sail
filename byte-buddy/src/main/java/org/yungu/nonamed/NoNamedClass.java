package org.yungu.nonamed;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

public class NoNamedClass {


    public static Class<?> generate(Class<?> supperClass) {
        DynamicType.Unloaded<?> type = new ByteBuddy()
                .subclass(supperClass)
                .make();
        return type.load(Thread.currentThread().getContextClassLoader()).getLoaded();
    }

    public static Class<?> generate2(Class<?> supperClass) {
        return new ByteBuddy()
                .with(new NamingStrategy.AbstractBase() {
                    @Override
                    protected String name(TypeDescription superClass) {
                        return "test.abc." + superClass.getSimpleName();
                    }
                }).subclass(supperClass).make().load(Thread.currentThread().getContextClassLoader()).getLoaded();
    }

    public static Class<?> generate3(Class<?> supperClass) {
        return new ByteBuddy()
                .with(new NamingStrategy.SuffixingRandom("DynamicClass")).subclass(supperClass).make().load(Thread.currentThread().getContextClassLoader()).getLoaded();
    }


    public static void main(String[] args) {
        Class<?> generate = generate(Object.class);
        //net.bytebuddy.renamed.java.lang.Object$ByteBuddy$mcvWIeHn
        System.out.println(generate.getName());

        Class<?> generate2 = generate(SupperClass.class);
        //org.yungu.nonamed.SupperClass$ByteBuddy$73BGD41e
        System.out.println(generate2.getName());

        Class<?> generate3 = generate2(SupperClass.class);
        //test.abc.org.SupperClass
        System.out.println(generate3.getName());

        Class<?> generate4 = generate3(SupperClass.class);
        //test.abc.org.SupperClass
        System.out.println(generate4.getName());


    }
}
