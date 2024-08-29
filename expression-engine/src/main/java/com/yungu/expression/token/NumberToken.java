package com.yungu.expression.token;

import com.yungu.expression.token.enums.TokenType;

import java.util.Map;

public class NumberToken extends Token<Number> {

    private Number number;

    public NumberToken(Number number, String value) {
        super(value);
        this.number = number;
    }

    @Override
    public TokenType getType() {
        return TokenType.Number;
    }

    @Override
    public Number getValue(Map<String, Object> env) {
        return number;
    }
}
