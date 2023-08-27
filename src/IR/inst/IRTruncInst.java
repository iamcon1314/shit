package src.IR.inst;

import src.IR.entity.*;
import src.IR.type.*;
import src.IR.*;

public class IRTruncInst extends IRCastInst {
  public IRTruncInst(IRBasicBlock block, IRRegister dest, IREntity val, IRType type) {
    super(block, val, type, dest);
  }

  @Override
  public String toString() {
    return dest + " = trunc " + val.toStringWithType() + " to " + targetType;
  }
} 