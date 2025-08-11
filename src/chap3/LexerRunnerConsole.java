package chap3;

import stone.*;
import java.io.StringReader;

public class LexerRunnerConsole {
    public static void main(String[] args) throws ParseException {
        // Sample code to tokenize for demonstration
        String sampleCode = "if (x > 10) {\n    result = x * 2;\n} else {\n    result = 0;\n}";
        
        System.out.println("Sample code to tokenize:");
        System.out.println(sampleCode);
        System.out.println("\nTokens:");
        
        Lexer l = new Lexer(new StringReader(sampleCode));
        for (Token t; (t = l.read()) != Token.EOF;) {
            System.out.println("=> " + t.getText());
        }
    }
}
