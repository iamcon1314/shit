package src.IR.inst;

import src.IR.entity.*;
import src.IR.type.*;
import src.IR.*;
public class IRZextInst extends IRCastInst {
  public IRZextInst(IRBasicBlock block, IRRegister dest, IREntity val, IRType type) {
    super(block, val, type, dest);
  }

  @Override
  public String toString() {
    return dest + " = zext " + val.toStringWithType() + " to " + targetType;
  }
} 