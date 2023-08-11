package src.ast.exprnode;

import src.ast.*;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;


//import javax.swing.text.Position;
public class AssignExprNode extends BinaryExprNode {

  public AssignExprNode(Position pos, ExprNode lhs, ExprNode rhs) {
    super(pos, lhs, "=", rhs);
    this.isLeftValue=true;
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