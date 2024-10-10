package org.example.function;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.lexer.token.OperatorType;

import java.util.HashMap;
import java.util.Map;

public class AddFunctionTest {
    public static void main(String[] args) {
        AviatorEvaluator.addFunction(new AddFunction());
        AviatorEvaluator.getInstance().aliasOperator(OperatorType.AND,"and");
        AviatorEvaluator.getInstance().aliasOperator(OperatorType.OR,"or");

        Expression expression = AviatorEvaluator.compile("add(\"hello:\",result);");

        Map<String, Object> env = new HashMap<>();
        env.put("result", "world !!!");
        Object execute = expression.execute(env);
        System.out.println(execute);

        Object execute1 = AviatorEvaluator.execute("if(1 ==1 and 2==2) {return true;}");
        System.out.println(execute1);
    }
}
