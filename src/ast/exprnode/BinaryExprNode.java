package src.ast.exprnode;
import src.ast.othernode.Type;
import src.ast.*;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;


//import javax.swing.text.Position;
public class BinaryExprNode extends ExprNode {
  public String op;
  public ExprNode lhs;
  public ExprNode rhs;
  public BinaryExprNode(Position pos, ExprNode lhs, String op, ExprNode rhs) {
    super(pos);
    this.lhs = lhs;
    this.op = op;
    this.rhs = rhs;
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