package src.util;

import src.MXIR.entity.*;
import src.MXIR.type.*;
import src.MXIR.type.IRVoidType;
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

    FuncDefNode StringLengthFunc = new FuncDefNode(null, IntOPr, "length", "string", null, 0);
    FuncDefNode StringSubStringFunc = new FuncDefNode(null, StringOPr, "substring", "string", IntOPr, 2);
    FuncDefNode StringParseIntFunc = new FuncDefNode(null, IntOPr, "parseInt", "string", null, 0);
    FuncDefNode StringOrdFunc = new FuncDefNode(null, IntOPr, "ord", "string", IntOPr, 1);
    FuncDefNode ArraySizeFunc = new FuncDefNode(null, IntOPr, "size", null, 0);











    // ------------------ IR Builtin Types ------------------

    IRType irVoidType = new IRVoidType();
    IRType irIntType = new IRIntType(32);
    IRType irNullType = new IRPtrType(irVoidType);
    IRType irBoolType = new IRIntType(8), irCharType = irBoolType;
    IRType irCondType = new IRIntType(1);










    // ------------------ IR Builtin Constants ------------------

    IRVoidConst irVoidConst = new IRVoidConst();
    IRCondConst irTrueConst = new IRCondConst(true);
    IRCondConst irFalseConst = new IRCondConst(false);
    IRBoolConst irBoolTrueConst = new IRBoolConst(true);
    IRBoolConst irBoolFalseConst = new IRBoolConst(false);
    IRIntConst irIntConst0 = new IRIntConst(0);
    IRIntConst irIntConst1 = new IRIntConst(1);
    IRIntConst irIntConstn1 = new IRIntConst(-1);
    IRType irStringType = new IRPtrType(irCharType);
//    IRNullConst irNullConst = new IRNullConst();
    IRType irIntPtrType = new IRPtrType(irIntType);
    IRIntConst irIntConst4 = new IRIntConst(4);
}

;
