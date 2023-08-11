package src.util;

import src.ast.othernode.FuncDefNode;
import src.ast.othernode.Type;

public interface SomethingExisting {
    Type Void = new Type("void");
    Type Int = new Type("int");
    Type Bool = new Type("bool");
    Type String = new Type("string");
    Type Null = new Type("null");
    Type This = new Type("this");
    Type Auto = new Type("auto");

    FuncDefNode PrintFunc = new FuncDefNode(null, Void, "print", String, 1);
    FuncDefNode PrintlnFunc = new FuncDefNode(null, Void, "println", String, 1);
    FuncDefNode PrintIntFunc = new FuncDefNode(null, Void, "printInt", Int, 1);
    FuncDefNode PrintlnIntFunc = new FuncDefNode(null, Void, "printlnInt", Int, 1);
    FuncDefNode GetStringFunc = new FuncDefNode(null, String, "getString", null, 0);
    FuncDefNode GetIntFunc = new FuncDefNode(null, Int, "getInt", null, 0);
    FuncDefNode ToStringFunc = new FuncDefNode(null, String, "toString", Int, 1);

    FuncDefNode StringLengthFunc = new FuncDefNode(null, Int, "length", null, 0);
    FuncDefNode StringSubStringFunc = new FuncDefNode(null, String, "substring", Int, 2);
    FuncDefNode StringParseIntFunc = new FuncDefNode(null, Int, "parseInt", null, 0);
    FuncDefNode StringOrdFunc = new FuncDefNode(null, Int, "ord", Int, 1);
    FuncDefNode ArraySizeFunc = new FuncDefNode(null, Int, "size", null, 0);
}

;
