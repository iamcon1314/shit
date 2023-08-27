package src.ast;

import src.ast.stmt.*;
import src.ast.expr.*;
import src.utils.*;

public class VarDefUnitNode extends Node {
    public TypeNode type;
    public String varName;
    public ExprNode initVal;

    public VarDefUnitNode(Position pos, TypeNode type, String name) {
        super(pos);
        this.type = type;
        this.varName = name;
    }
    public VarDefUnitNode(Position pos, TypeNode type, String name, ExprNode initVal) {
        super(pos);
        this.type = type;
        this.varName = name;
        this.initVal = initVal;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}