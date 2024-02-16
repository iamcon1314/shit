package src;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import src.ASM.assembly.ASMModule;
import src.ASM.backend.InstSelector;
import src.ASM.backend.RegAllocator;
import src.Builder.IRBuilder;
import src.MXIR.IRProgram;
import src.Scope.GlobalScope;
import src.ast.othernode.ProgramNode;
import src.frontend.ASTBuilder;
import src.frontend.SemanticChecker;
import src.frontend.SymbolCollector;
import src.grammar.MxLexer;
import src.grammar.MxParser;
import src.myerror.MxErrorListener;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
//import org.antlr.v4.runtime.tree.*;
//
//import front.*;
//import back.IRBuilder;
//import back.InstSelector;
//import back.RegAllocator;

public class Compiler {
        public static void writeToFile(String fileName, String content) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        writer.write(content);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        public static void main(String[] args) throws Exception {

//        try {
            CharStream input = CharStreams.fromStream(new FileInputStream("1.cpp"));
//                CharStream input = CharStreams.fromStream(System.in);
                MxLexer lexer = new MxLexer(input);
                lexer.removeErrorListeners();
                lexer.addErrorListener(new MxErrorListener());
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                MxParser parser = new MxParser(tokens);
                parser.removeErrorListeners();
                parser.addErrorListener(new MxErrorListener());
                ParseTree tree = parser.program();
                ASTBuilder astBuilder = new ASTBuilder();
                ProgramNode ast = (ProgramNode) astBuilder.visit(tree);
                GlobalScope globalScope = new GlobalScope();
                new SymbolCollector(globalScope).visit(ast);
                new SemanticChecker(globalScope).visit(ast);

                IRProgram irprogram = new IRProgram();
                new IRBuilder( irprogram,globalScope).visit(ast);
//            String content1 = irprogram.toString();
//            writeToFile("1.ll", content1);

                ASMModule asmModule = new ASMModule();
                new InstSelector(asmModule).visit(irprogram);
                new RegAllocator(asmModule).work();
                String content = asmModule.toString();
//            writeToFile("1.s", content);
                System.out.print(content);
//        }
//        catch (Throwable gb){
//            System.out.print(gb.toString());
//        }
        }
}