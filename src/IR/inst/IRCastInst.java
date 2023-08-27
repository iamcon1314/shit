package src.IR.inst;

import src.IR.IRBasicBlock;
import src.IR.IRVisitor;
import src.IR.entity.*;
import src.IR.type.IRType;

public abstract class IRCastInst extends IRInst {
    public IREntity val;
    public IRType targetType;
    public IRRegister dest;

    public IRCastInst(IRBasicBlock block, IREntity val, IRType type, IRRegister dest) {
        super(block);
        this.val = val;
        this.targetType = type;
        this.dest = dest;
    }

    @Override
    public final void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}