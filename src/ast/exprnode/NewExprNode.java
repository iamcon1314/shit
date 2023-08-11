package src.ast.exprnode;

import src.ast.*;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;
//import javax.swing.text.Position;
import java.util.ArrayList;
import src.ast.othernode.Type;
public class NewExprNode extends ExprNode {
  public String typeName;
  public int dimension = 0;
  public ArrayList<ExprNode> sizeList = new ArrayList<ExprNode>();
  public NewExprNode(Position pos, String typeName) {
    super(pos);
    this.typeName = typeName;
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