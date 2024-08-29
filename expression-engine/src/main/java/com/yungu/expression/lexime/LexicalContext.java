package com.yungu.expression.lexime;

import com.yungu.expression.token.Token;

import java.util.ArrayList;
import java.util.List;

public class LexicalContext {

    private final String text;

    private List<Token<?>> parsedTokens;

    public LexicalContext(String text) {
        this.text = text;
        this.parsedTokens = new ArrayList<>();
    }

    public String getOriginText() {
        return this.text;
    }

    public void addToken(Token<?> token) {
        parsedTokens.add(token);
    }

    public List<Token<?>> getParsedTokens() {
        return parsedTokens;
    }
}
