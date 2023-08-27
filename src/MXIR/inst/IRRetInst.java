//package IR.inst;
package src.MXIR.inst;
import src.MXIR.entity.*;
import src.MXIR.*;

public class IRRetInst extends IRTerminalInst {
  public IREntity val;

  public IRRetInst(IRBasicBlock block, IREntity val) {
    super(block);
    this.val = val;
  }
  
  @Override
  public String toString() {
    return "ret " + val.toStringWithType();
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}