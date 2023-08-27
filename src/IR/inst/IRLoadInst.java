package src.IR.inst;

import src.IR.entity.*;
import src.IR.type.*;
import src.IR.*;

public class IRLoadInst extends IRInst {
  public IRRegister destReg;
  public IREntity srcAddr;
  public IRType type;

  public IRLoadInst(IRBasicBlock block, IRRegister destReg, IREntity srcAddr) {
    super(block);
    this.destReg = destReg;
    this.srcAddr = srcAddr;
    this.type = destReg.type;
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
  @Override
  public String toString() {
    return destReg + " = load " + type + ", " + srcAddr.toStringWithType();
  }
}