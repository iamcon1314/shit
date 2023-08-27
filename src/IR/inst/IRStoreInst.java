package src.IR.inst;

import src.IR.entity.*;
import src.IR.type.*;
import src.IR.*;

public class IRStoreInst extends IRInst {
  public IREntity val, destAddr;

  public IRStoreInst(IRBasicBlock block, IREntity val, IREntity destAddr) {
    super(block);
    this.val = val;
    this.destAddr = destAddr;
  }
  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public String toString() {
    return "store " + val.toStringWithType() + ", " + destAddr.toStringWithType();
  }
}

