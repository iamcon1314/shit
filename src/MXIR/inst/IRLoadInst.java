//package IR.inst;
package src.MXIR.inst;
import src.MXIR.entity.*;
import src.MXIR.type.*;
import src.MXIR.*;

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
  public String toString() {
    return destReg + " = load " + type + ", " + srcAddr.toStringWithType();
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}