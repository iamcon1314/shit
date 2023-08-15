package src.util;

import src.ast.othernode.Type;

import static src.util.SomethingExisting.IntOPr;

public class MoreType {
    public String op;
    public String optype;
    public MoreType(String op){
        this.op=op;
        if(op.equals("+")||
                op.equals("<=")||
                op.equals(">=")||
                op.equals("<")||
                op.equals(">")
        ){
            optype="MoreType";
        }
    }
}
