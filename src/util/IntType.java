package src.util;

import src.ast.othernode.Type;

import static src.util.SomethingExisting.*;

public class IntType {
        public String op;
        public Type optype;
        public IntType(String op){
                this.op=op;
                if(op.equals("*")||
                        op.equals("/")||
                        op.equals("%")||
                        op.equals("-")||
                        op.equals(">>")||
                        op.equals("<<")||
                        op.equals("&")||
                        op.equals("^")||
                        op.equals("|")
                ){
                        optype=Int;
                }
        }

}
