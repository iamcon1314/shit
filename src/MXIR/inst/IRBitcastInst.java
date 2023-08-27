//package IR.inst;
package src.MXIR.inst;
import src.MXIR.entity.*;
import src.MXIR.type.*;
import src.MXIR.*;

public class IRBitcastInst extends IRCastInst {
  public IRBitcastInst(IRBasicBlock block, IREntity val, IRType type, IRRegister dest) {
    super(block, val, type, dest);
  }

  @Override
  public String toString() {
    return dest + " = bitcast " + val.toStringWithType() + " to " + targetType;
  }
}