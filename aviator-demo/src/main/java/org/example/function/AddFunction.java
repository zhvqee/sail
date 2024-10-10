package org.example.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

public class AddFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        Object obj1 = arg1.getValue(env);
        Object obj2 = arg2.getValue(env);
        if (obj1 instanceof String || obj2 instanceof String) {
            return FunctionUtils.wrapReturn(obj1.toString() + obj2.toString());
        }
        return null;
    }

    @Override
    public String getName() {
        return "add";
    }
}
