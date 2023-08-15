package src.util;

import src.ast.othernode.FuncDefNode;
import src.ast.othernode.Type;

public interface SomethingExisting {
    Type VoidOPr = new Type("void");
    Type IntOPr = new Type("int");
    Type BoolOPr = new Type("bool");
    Type StringOPr = new Type("string");
    Type NullOPr = new Type("null");
    Type ThisOPr = new Type("this");
    Type AutoOPr = new Type("auto");

    FuncDefNode PrintFunc = new FuncDefNode(null, VoidOPr, "print", StringOPr, 1);
    FuncDefNode PrintlnFunc = new FuncDefNode(null, VoidOPr, "println", StringOPr, 1);
    FuncDefNode PrintIntFunc = new FuncDefNode(null, VoidOPr, "printInt", IntOPr, 1);
    FuncDefNode PrintlnIntFunc = new FuncDefNode(null, VoidOPr, "printlnInt", IntOPr, 1);
    FuncDefNode GetStringFunc = new FuncDefNode(null, StringOPr, "getString", null, 0);
    FuncDefNode GetIntFunc = new FuncDefNode(null, IntOPr, "getInt", null, 0);
    FuncDefNode ToStringFunc = new FuncDefNode(null, StringOPr, "toString", IntOPr, 1);

    FuncDefNode StringLengthFunc = new FuncDefNode(null, IntOPr, "length", null, 0);
    FuncDefNode StringSubStringFunc = new FuncDefNode(null, StringOPr, "substring", IntOPr, 2);
    FuncDefNode StringParseIntFunc = new FuncDefNode(null, IntOPr, "parseInt", null, 0);
    FuncDefNode StringOrdFunc = new FuncDefNode(null, IntOPr, "ord", IntOPr, 1);
    FuncDefNode ArraySizeFunc = new FuncDefNode(null, IntOPr, "size", null, 0);
}

;
