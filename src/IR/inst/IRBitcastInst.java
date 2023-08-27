package src.IR.inst;

import src.IR.entity.*;
import src.IR.type.*;
import src.IR.*;

public class IRBitcastInst extends IRCastInst {
  public IRBitcastInst(IRBasicBlock block, IREntity val, IRType type, IRRegister dest) {
    super(block, val, type, dest);
  }

  @Override
  public String toString() {
    return dest + " = bitcast " + val.toStringWithType() + " to " + targetType;
  }
}