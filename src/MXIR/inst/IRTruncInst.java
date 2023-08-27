//package IR.inst;
package src.MXIR.inst;
import src.MXIR.entity.*;
import src.MXIR.type.*;
import src.MXIR.*;

public class IRTruncInst extends IRCastInst {
  public IRTruncInst(IRBasicBlock block, IRRegister dest, IREntity val, IRType type) {
    super(block, val, type, dest);
  }

  @Override
  public String toString() {
    return dest + " = trunc " + val.toStringWithType() + " to " + targetType;
  }
} 