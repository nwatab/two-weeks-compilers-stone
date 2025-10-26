package chapB;
import java.util.Arrays;
import java.io.FileReader;
import java.io.FileNotFoundException;
import stone.*;
import stone.ast.*;

public class ExprParser {
    private Lexer lexer;

    public ExprParser(Lexer p) {
        lexer = p;
    }
    public ASTree expression() throws ParseException {
        ASTree left = term();
        while (isToken("+") || isToken("-")) {
            ASTLeaf op = new ASTLeaf(lexer.read());
            ASTree right = term();
            left = new BinaryExpr(Arrays.asList(left, op, right));
        }
        return left;
    }
    public ASTree term() throws ParseException {
        ASTree left = factor();
        while (isToken("*") || isToken("/")) {
            ASTLeaf op = new ASTLeaf(lexer.read());
            ASTree right = factor();
            left = new BinaryExpr(Arrays.asList(left, op, right));
        }
        return left;
    }
    public ASTree factor() throws ParseException {
        if (isToken("(")) {
            token("(");
            ASTree e = expression();
            token(")");
            return e;
        }
        else {
            Token t = lexer.read();
            if (t.isNumber()) {
                NumberLiteral n = new NumberLiteral(t);
                return n;
            }
            else
                throw new ParseException(t);
        }
    }
    void token(String name) throws ParseException {
        Token t = lexer.read();
        if (!(t.isIdentifier() && name.equals(t.getText())))
            throw new ParseException(t);
    }
    boolean isToken(String name) throws ParseException {
        Token t = lexer.peek(0);
        return t.isIdentifier() && name.equals(t.getText());
    }
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        if (args.length != 1) {
            System.out.println("Usage: java chapB.ExprParser <sourcefile>");
            return;
        }
        String filename = args[0];
        FileReader reader = new FileReader(filename);
        Lexer lexer = new Lexer(reader);
        ExprParser parser = new ExprParser(lexer);
        ASTree t = parser.expression();
        System.out.println("=> " + t);
    }
}