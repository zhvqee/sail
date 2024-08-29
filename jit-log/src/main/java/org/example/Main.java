package org.example;

public class Main {

    public static int add(int a, int b) {
        return a + b;
    }

    //-XX:+PrintCompilation

    //-XX:+PrintCodeCache
    //-XX:InitialCodeCacheSize
    //-XX:ReservedCodeCacheSize
    //-XX:CodeCacheExpansionSize
    public static void main(String[] args) throws InterruptedException {
        //method1();
        System.out.println(System.currentTimeMillis());

        for (int i = 0; i < 50000; i++) {
            MyInterface myInterface;
            if (i < 45000) {
                // The first 45.000 executions will enter here
                myInterface = new MyInterfaceImpl();
            } else {
                myInterface = new MyInterfaceLoggerImpl();
            }
            myInterface.addARandomNumber(50);
        }
    }

    private static void method1() throws InterruptedException {
        for (int i = 1; i < 100000; i++) {
            int add = add(i, i * 2);
           // System.out.println(add);
        }
        Thread.sleep(10000);
    }



    interface MyInterface {
        void addARandomNumber(double value);
    }


    static class MyInterfaceImpl implements MyInterface {
        @Override
        public void addARandomNumber(double value) {
            double random = Math.random();
            double finalResult = random + value;
        }
    }


    static class MyInterfaceLoggerImpl implements MyInterface {
        @Override
        public void addARandomNumber(double value) {
            double v = Math.random() + value;
           // System.out.println("The value is: " + v);
        }
    }


}