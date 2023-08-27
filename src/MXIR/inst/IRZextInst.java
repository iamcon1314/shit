//package IR.inst;
package src.MXIR.inst;
import src.MXIR.entity.*;
import src.MXIR.type.*;
import src.MXIR.*;
public class IRZextInst extends IRCastInst {
  public IRZextInst(IRBasicBlock block, IRRegister dest, IREntity val, IRType type) {
    super(block, val, type, dest);
  }

  @Override
  public String toString() {
    return dest + " = zext " + val.toStringWithType() + " to " + targetType;
  }
} 