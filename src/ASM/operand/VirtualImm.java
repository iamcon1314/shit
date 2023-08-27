package src.ASM.operand;

import src.ASM.inst.*;
import src.IR.entity.*;

import java.util.LinkedList;
public class VirtualImm extends Reg {
  int value;
  public VirtualImm(int value) {
    this.value = value;
  }

  public VirtualImm(IRConst constVal) {
    if (constVal instanceof IRIntConst) {
      value = ((IRIntConst) constVal).val;
    } else if (constVal instanceof IRBoolConst) {
      value = ((IRBoolConst) constVal).val ? 1 : 0;
    } else if (constVal instanceof IRCondConst) {
      value = ((IRCondConst) constVal).val ? 1 : 0;
    } else {
      value = 0;
    }
  }

  public String toString() {
    return Integer.toString(value);
  }
}