package src.IR.inst;

import src.IR.entity.*;
import src.IR.type.*;
import src.IR.*;

public abstract class IRInst {
  
  public IRBasicBlock parentBlock = null;
  public abstract String toString();
  public abstract void accept(IRVisitor visitor);
  public IRInst(IRBasicBlock block) {
    this.parentBlock = block;
  }
}

