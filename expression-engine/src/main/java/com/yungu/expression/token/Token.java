package com.yungu.expression.token;

import com.yungu.expression.token.enums.TokenType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * token 把输入的文本 转化为一个个相应的token
 * 这一步就是进行词法分析
 * 本表达式引擎只token 包括如下
 *
 * @param <T>
 */
public abstract class Token<T> implements Serializable {

    private Map<String, Object> metaMap = new HashMap<>();

    protected final String lexeme;

    public Token(String lexeme) {
        this.lexeme = lexeme;
    }

    public void addMeta(String name, Object value) {
        metaMap.put(name, value);
    }

    public Object getMeta(String name) {
        return metaMap.get(name);
    }

    public abstract T getValue(Map<String, Object> env);

    public abstract TokenType getType();
}
