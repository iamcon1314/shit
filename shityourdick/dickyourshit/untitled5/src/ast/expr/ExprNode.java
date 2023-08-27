package src.ast.expr;

import src.ast.*;
//import src.ast.stmt.*;
import src.utils.*;

public abstract class ExprNode extends Node {
  public String str;
  public Type type;
  public FuncDefNode funcDef = null;

  public ExprNode(Position pos) {
    super(pos);
  }

  public abstract boolean isLeftValue();
};