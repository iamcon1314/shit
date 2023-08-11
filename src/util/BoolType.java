package src.util;

import src.ast.othernode.Type;

import static src.util.SomethingExisting.Bool;
import static src.util.SomethingExisting.Int;

public class BoolType {
    public String op;
    public Type optype;
    public BoolType(String op){
        this.op=op;
        if(op.equals("&&")||
                op.equals("||")
        ){
            optype=Bool;
        }
    }
}
