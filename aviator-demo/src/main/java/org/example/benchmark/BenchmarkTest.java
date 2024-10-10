package org.example.benchmark;

import com.googlecode.aviator.AviatorEvaluator;
import org.example.BaseTest;

import java.io.IOException;

public class BenchmarkTest extends BaseTest {
    public static void main(String[] args) throws IOException {
        //into ,map, filter ,reduce function
        Object execute = AviatorEvaluator.getInstance().compileScript("benchmark.av").execute();
        System.out.println(execute);
    }
}
