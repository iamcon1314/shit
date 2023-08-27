package src.IR.inst;

import src.IR.entity.*;
import src.IR.type.*;
import src.IR.*;
public class IRJumpInst extends IRTerminalInst {
  public IRBasicBlock toBlock;

  public IRJumpInst(IRBasicBlock block, IRBasicBlock toBlock) {
    super(block);
    this.toBlock = toBlock;
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
  @Override
  public String toString() {
    return "br label %" + toBlock.name;
  }
}