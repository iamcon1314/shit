//package IR.inst;
package src.MXIR.inst;
import src.MXIR.*;

public abstract class IRInst {
  
  public IRBasicBlock parentBlock = null;
  public abstract String toString();

  public IRInst(IRBasicBlock block) {
    this.parentBlock = block;
  }

  public abstract void accept(IRVisitor visitor);
}

