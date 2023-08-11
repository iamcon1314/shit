package src.ast.exprnode;
import src.ast.othernode.Type;
import src.ast.*;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;


//import javax.swing.text.Position;
public class MemberExprNode extends ExprNode implements SomethingExisting {
  public ExprNode obj;
  public String member;

  public MemberExprNode(Position pos, ExprNode obj, String member) {
    super(pos);
    this.obj = obj;
    this.member = member;
    this.isLeftValue=true;
  }
  @Override
  public Type gettype() {
    return type;
  }

  @Override
  public java.lang.String getstr() {
    return member;
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