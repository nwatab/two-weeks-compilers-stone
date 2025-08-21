package chap6;
import stone.*;
import stone.ast.ASTree;
import stone.ast.NullStmnt;
import java.io.FileReader;
import java.io.IOException;

public class BasicInterpreter {
    public static void main(String[] args) throws ParseException, IOException {
        if (args.length < 1) {
            System.err.println("Usage: java BasicInterpreter <source file>");
            System.exit(1);
        }
        String path = args[0];
        Lexer lexer = new Lexer(new FileReader(path));
        run(new BasicParser(), new BasicEnv(), lexer);
    }
    public static void run(BasicParser bp, Environment env, Lexer lexer) throws ParseException, IOException {
        while (lexer.peek(0) != Token.EOF) {
            ASTree t = bp.parse(lexer);
            if (!(t instanceof NullStmnt)) {
                Object r = ((BasicEvaluator.ASTreeEx) t).eval(env);
                System.out.println("=> " + r);
            }
        }
    }
}