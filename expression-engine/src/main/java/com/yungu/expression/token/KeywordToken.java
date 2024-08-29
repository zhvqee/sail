package com.yungu.expression.token;

import com.yungu.expression.token.enums.TokenType;

import java.util.HashMap;
import java.util.Map;

public class KeywordToken extends Token<KeywordToken> {


    public static KeywordToken IF = new KeywordToken("if");
    public static KeywordToken FOR = new KeywordToken("for");
    public static KeywordToken RETURN = new KeywordToken("return");

    private static Map<String, KeywordToken> leximeMap = new HashMap<>();

    static {
        leximeMap.put("if", IF);
        leximeMap.put("for", FOR);
        leximeMap.put("return", RETURN);
    }

    public static boolean canConvert(StringToken stringToken) {
        return leximeMap.containsKey(stringToken.getValue(null));
    }

    public KeywordToken(String value) {
        super(value);

    }

    @Override
    public KeywordToken getValue(Map<String, Object> env) {
        return leximeMap.get(lexeme);
    }

    @Override
    public TokenType getType() {
        return TokenType.Keyword;
    }
}
