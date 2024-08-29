import com.yungu.expression.lexime.LexicalAnalysis;
import com.yungu.expression.lexime.LexicalContext;
import com.yungu.expression.token.Token;

import java.util.List;

public class LexicalAnalysisTest {
    public static void main(String[] args) {
        LexicalContext lexicalContext1 = new LexicalContext("let a=1111  +  3222+2;\n" +
                "for(c in ls){" +
                "  c+=a;" +
                "}");
        LexicalAnalysis lexicalAnalysis = new LexicalAnalysis(lexicalContext1);
        lexicalAnalysis.analyse();
        List<Token<?>> parsedTokens = lexicalContext1.getParsedTokens();
        System.out.println(parsedTokens);

        System.out.println(System.currentTimeMillis());

    }
}
