//package src.ASM;package src.ASM.inst;
package src.ASM.inst;
import src.ASM.inst.*;
import java.util.LinkedList;

public class ASMCallInst extends ASMInst {
  String funcName;

  public ASMCallInst(String funcName) {
    this.funcName = funcName;
  }

  @Override
  public String toString() {
    return "call " + funcName;
  }
}