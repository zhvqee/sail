package com.yungu.expression.token;

import com.yungu.expression.token.enums.TokenType;

import java.util.Map;

public class StringToken extends Token<String> {

    public StringToken(String value) {
        super(value);
    }

    @Override
    public TokenType getType() {
        return TokenType.String;
    }

    @Override
    public String getValue(Map<String, Object> env) {
        return lexeme;
    }
}
