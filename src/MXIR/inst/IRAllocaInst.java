//package IR.inst;

package src.MXIR.inst;
import src.MXIR.entity.*;
import src.MXIR.type.*;
import src.MXIR.*;

public class IRAllocaInst extends IRInst {
  public IRType type;
  public IRRegister allocaReg;
  public IRAllocaInst(IRBasicBlock block, IRType type, IRRegister allocaReg) {
    super(block);
    this.type = type;
    this.allocaReg = allocaReg;
  }

  @Override
  public String toString() {
    return allocaReg + " = alloca " + type;
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}

