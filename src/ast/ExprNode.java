package src.ast;

import src.MXIR.entity.IREntity;
import src.MXIR.entity.IRRegister;
import src.MXIR.type.IRType;
import src.ast.*;
//import src.ast.stmt.*;
import src.ast.othernode.FuncDefNode;
import src.util.*;
import src.ast.othernode.Type;
public abstract class ExprNode extends Node {
  public String str;
  public Type type;
  public IRRegister storePtr = null; // for left value in IR
  public IREntity IRvalue = null;      // for IR
  public FuncDefNode funcDef = null;
  public boolean isLeftValue;
  public ExprNode(Position pos) {
    super(pos);
  }
  public abstract Type gettype();
  public abstract String getstr();
  public abstract boolean isLeftValue();
  public IRType getIRType() {
    return IRvalue.type;
  }
};
