package org.example.seq;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.BaseTest.readText;

public class SeqTest {


    @Data
    public static class User {
        private int age;
        private String name;
    }

    public static void main(String[] args) throws IOException {
        //into ,map, filter ,reduce function
        String script = readText("seq.av");
        Expression expression = AviatorEvaluator.compile(script);
        Map<String, Object> env = new HashMap<>();

        List<User> ls = new ArrayList<>();
        User user = new User();
        user.setAge(40);
        user.setName("da ming");
        ls.add(user);

        User user2 = new User();
        user2.setAge(10);
        user2.setName("xiao ming");
        ls.add(user2);

        env.put("inputResult", ls);
        Object execute = expression.execute(env);
        System.out.println(execute);
    }
}
