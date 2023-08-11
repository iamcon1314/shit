package src.ast.exprnode;

import src.ast.*;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;


//import javax.swing.text.Position;
public class PreAddExprNode extends UnaryExprNode {
  public PreAddExprNode(Position pos, String op, ExprNode expr) {
    super(pos, op, expr);
    isLeftValue=true;
  }

  @Override
  public boolean isLeftValue() {
    return true;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}
