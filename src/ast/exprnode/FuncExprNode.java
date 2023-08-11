package src.ast.exprnode;
import src.ast.othernode.Type;
import src.ast.*;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;


//import javax.swing.text.Position;
public class FuncExprNode extends ExprNode {
  public ExprNode funcName;
  public ExprListNode args=null;

  public boolean isargsempty=false;
  public FuncExprNode(Position pos, ExprNode func) {
    super(pos);
    this.funcName = func;
    this.isLeftValue=false;
  }
  @Override
  public Type gettype() {
    return type;
  }

  @Override
  public java.lang.String getstr() {
    return str;
  }


  @Override
  public boolean isLeftValue() {
    return false;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}