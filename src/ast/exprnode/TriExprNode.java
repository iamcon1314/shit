package src.ast.exprnode;
import src.ast.*;
import src.util.*;
//package src.frontend;
//import grammar;
//package src.ast;
//import grammar;
import src.ast.othernode.Type;

//import javax.swing.text.Position;
public class TriExprNode extends ExprNode {
    public ExprNode cond;
    public ExprNode lhs;
    public ExprNode rhs;
    public TriExprNode(Position pos, ExprNode cond, ExprNode lhs, ExprNode rhs) {
        super(pos);
        this.lhs = lhs;
        this.cond = cond;
        this.rhs = rhs;
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
