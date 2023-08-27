package src.ast;

//package ast;

import src.ast.stmt.*;
import java.util.ArrayList;
import src.utils.*;

public class VarDefNode extends StmtNode {
    public ArrayList<VarDefUnitNode> units = new ArrayList<VarDefUnitNode>();

    public VarDefNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
