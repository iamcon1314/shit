package src.ast.exprnode;
import src.ast.*;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;


//import javax.swing.text.Position;
public class VarExprNode extends AtomExprNode {
  public VarExprNode(Position pos, String str) {
    super(pos, str);
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
