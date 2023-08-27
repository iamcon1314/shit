package src.IR.entity;

import src.ASM.operand.Reg;
import src.util.*;
import src.IR.type.*;

public abstract class IREntity implements SomethingExisting {
  public IRType type;
  public Reg asmReg;
  IREntity(IRType type) {
    this.type = type;
  }

  public abstract String toString();

  public abstract String toStringWithType();
}