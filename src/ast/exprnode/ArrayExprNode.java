package src.ast.exprnode;
import src.ast.othernode.Type;
import src.ast.*;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;


//import javax.swing.text.Position;
public class ArrayExprNode extends ExprNode {

  public ExprNode array;
  public ExprNode index;

  public ArrayExprNode(Position pos, ExprNode array, ExprNode index) {
    super(pos);
    this.array = array;
    this.index = index;
    this.isLeftValue=true;
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
    return true;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }
}