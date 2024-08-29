package com.yungu.expression.lexime;

import com.yungu.expression.NumberUtils;
import com.yungu.expression.token.*;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.List;

public class LexicalAnalysis {

    private LexicalContext lexicalContext;

    private StringCharacterIterator iterator;

    private char currentChar;
    private char beforeChar;

    public LexicalAnalysis(LexicalContext lexicalContext) {
        this.lexicalContext = lexicalContext;
        iterator = new StringCharacterIterator(lexicalContext.getOriginText());
        currentChar = iterator.current();
    }

    private boolean next() {
        beforeChar = currentChar;
        currentChar = iterator.next();
        return beforeChar != StringCharacterIterator.DONE;
    }

    public void analyse() {
        Token<?> target = null;
        while ((target = scan()) != null) {
            lexicalContext.addToken(target);
        }
    }

    public Token<?> scan() {

        if (currentChar == CharacterIterator.DONE) {
            return null;
        }

        for (; currentChar != CharacterIterator.DONE; ) {
            if (currentChar == '\t' || currentChar == '\n' || currentChar == ' ') {
                next();
                continue;
            } else {
                break;
            }
        }
        if (currentChar == CharacterIterator.DONE) {
            return null;
        }


        //仅支持 long,double
        if (isNumber(currentChar)) {
            StringBuilder sb = new StringBuilder();
            do {
                sb.append(currentChar);
            } while (next() && isNumber(currentChar) || isDot(currentChar));
            Number number = NumberUtils.parseNumber(sb.toString());
            return new NumberToken(number, sb.toString());

        }

        if (Character.isJavaIdentifierPart(currentChar)) {
            StringBuilder sb = new StringBuilder();
            do {
                sb.append(currentChar);

            } while (next() && !isBlank() && isJavaIdentifier());

            StringToken stringToken = new StringToken(sb.toString());
            if (KeywordToken.canConvert(stringToken)) {
                return new KeywordToken(stringToken.getValue(null));
            }
            return stringToken;
        }

        CharToken charToken = new CharToken(currentChar);
        next();
        return charToken;
    }

    private boolean isDot(char ch) {
        return ch == '.';
    }

    private boolean isJavaIdentifier() {
        return Character.isJavaIdentifierPart(currentChar);
    }

    private boolean isBlank() {
        return currentChar == ' ' || currentChar == '\t';
    }

    private boolean isAlphabet(char ch) {
        return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
    }

    private boolean isNumber(char ch) {
        int i = ch - '0';
        return 0 <= i && i <= 9;
    }




}
