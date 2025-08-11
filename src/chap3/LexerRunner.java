package chap3;

import stone.*;
// does not work easily on Dockerfile due to missing GUI libraries
public class LexerRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        for (Token t; (t = l.read()) != Token.EOF;) {
            System.out.println("=> " + t.getText());
        }
    }
}