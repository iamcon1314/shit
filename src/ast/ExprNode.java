package src.ast;

import src.ast.*;
//import src.ast.stmt.*;
import src.ast.othernode.FuncDefNode;
import src.util.*;
import src.ast.othernode.Type;
public abstract class ExprNode extends Node {
  public String str;
  public Type type;
  public FuncDefNode funcDef = null;
  public boolean isLeftValue;
  public ExprNode(Position pos) {
    super(pos);
  }
  public abstract Type gettype();
  public abstract String getstr();
  public abstract boolean isLeftValue();
};
