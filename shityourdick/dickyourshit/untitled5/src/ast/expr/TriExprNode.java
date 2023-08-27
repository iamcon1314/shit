package src.ast.expr;
import src.ast.*;
import src.utils.*;
public class TriExprNode extends ExprNode {
    public ExprNode cond;
    public ExprNode lhs;
    public ExprNode rhs;
    public TriExprNode(Position pos, ExprNode cond, ExprNode lhs, ExprNode rhs) {
        super(pos);
        this.lhs = lhs;
        this.cond = cond;
        this.rhs = rhs;
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
