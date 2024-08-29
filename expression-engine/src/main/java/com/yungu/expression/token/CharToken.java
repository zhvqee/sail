package com.yungu.expression.token;

import com.yungu.expression.token.enums.TokenType;

import java.util.Map;

public class CharToken extends Token<Character> {
    private char ch;

    public CharToken(char value) {
        super(value + "");
        ch = value;
    }

    @Override
    public TokenType getType() {
        return TokenType.String;
    }

    @Override
    public Character getValue(Map<String, Object> env) {
        return ch;
    }
}
