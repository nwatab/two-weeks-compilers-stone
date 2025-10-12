package chap11;
import java.io.FileReader;
import java.io.FileNotFoundException;
import chap6.BasicEvaluator;
import chap6.Environment;
import chap8.Natives;
import stone.BasicParser;
import stone.ClosureParser;
import stone.CodeDialog;
import stone.Lexer;
import stone.ParseException;
import stone.Token;
import stone.ast.ASTree;
import stone.ast.NullStmnt;

public class EnvOptInterpreter {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        if (args.length < 1) {
            System.err.println("Usage: java NativeInterpreter <source file>");
            System.exit(1);
        }
        String path = args[0];
        Lexer lexer = new Lexer(new FileReader(path));
        run(new ClosureParser(), new Natives().environment(new ResizableArrayEnv()), lexer);
    }
    public static void run(BasicParser bp, Environment env, Lexer lexer) throws ParseException {
        while (lexer.peek(0) != Token.EOF) {
            ASTree t = bp.parse(lexer);
            if (!(t instanceof NullStmnt)) {
                ((EnvOptimizer.ASTreeOptEx) t).lookup(
                    ((EnvOptimizer.EnvEx2) env).symbols()
                );
                Object r = ((BasicEvaluator.ASTreeEx)t).eval(env);
                System.out.println("=> " + r);
            }
        }
    }
}