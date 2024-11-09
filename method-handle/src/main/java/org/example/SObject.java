package org.example;

public class SObject implements Behavior {
    @Override
    public String say(String name) {
        return "parent " + name;
    }
}
