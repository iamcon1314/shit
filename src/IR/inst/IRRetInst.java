package src.IR.inst;

import src.IR.entity.*;
import src.IR.type.*;
import src.IR.*;

public class IRRetInst extends IRTerminalInst {
  public IREntity val;

  public IRRetInst(IRBasicBlock block, IREntity val) {
    super(block);
    this.val = val;
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
  @Override
  public String toString() {
    return "ret " + val.toStringWithType();
  }
}