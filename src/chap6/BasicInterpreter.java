package chap6;
import stone.*;
import stone.ast.ASTree;
import stone.ast.NullStmnt;
import java.io.FileReader;
import java.io.IOException;

public class BasicInterpreter {
    public static void main(String[] args) throws ParseException, IOException {
        run(new BasicParser(), new BasicEnv());
    }
    public static void run(BasicParser bp, Environment env) throws ParseException, IOException {
        Lexer lexer = new Lexer(new FileReader("src/chap6/sample.stone"));
        while (lexer.peek(0) != Token.EOF) {
            ASTree t = bp.parse(lexer);
            if (!(t instanceof NullStmnt)) {
                Object r = ((BasicEvaluator.ASTreeEx) t).eval(env);
                System.out.println("=> " + r);
            }
        }
    }
}