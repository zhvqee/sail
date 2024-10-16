package org.example;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.io.IOException;

import static org.example.BaseTest.readText;

public class TestCompile {
    public static void main(String[] args) throws IOException {
        String script = readText("compile.av");
        Expression expression = AviatorEvaluator.compile(script);
        System.out.println(expression);
    }
}
