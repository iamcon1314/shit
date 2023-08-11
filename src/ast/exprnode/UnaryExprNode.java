package src.ast.exprnode;
import src.ast.othernode.Type;
import src.ast.*;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;


//import javax.swing.text.Position;
public class UnaryExprNode extends ExprNode {
  public String op;
  public ExprNode expr;

  public UnaryExprNode(Position pos, String op, ExprNode expr) {
    super(pos);
    this.op = op;
    this.expr = expr;
    this.isLeftValue=false;
  }
  @Override
  public Type gettype() {
    return type;
  }

  @Override
  public String getstr() {
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