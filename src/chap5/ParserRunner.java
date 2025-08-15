package chap5;
import stone.ast.ASTree;
import stone.*;
import java.io.FileReader;
import java.io.IOException;


public class ParserRunner {
    public static void main(String[] args) throws ParseException, IOException {
        String filename = "sample.stone";
        if (args.length > 0) {
            filename = args[0];
        }
        
        Lexer l = new Lexer(new FileReader(filename));
        BasicParser bp = new BasicParser();
        while (l.peek(0) != Token.EOF) {
            ASTree ast = bp.parse(l);
            System.out.println("=> " + ast.toString());
        }
    }
}