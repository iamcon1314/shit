package src.IR.inst;

import src.IR.entity.*;
import src.IR.type.*;
import src.IR.*;
public abstract class IRTerminalInst extends IRInst {
  public IRTerminalInst(IRBasicBlock block) {
    super(block);
  }
}

