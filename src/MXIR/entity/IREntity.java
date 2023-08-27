package src.MXIR.entity;

import src.util.*;
import src.MXIR.type.*;
import src.ASM.assembly.operand.*;

public abstract class IREntity implements SomethingExisting {
  public IRType type;

  public Reg asmReg;
  
  IREntity(IRType type) {
    this.type = type;
  }

  public abstract String toString();

  public abstract String toStringWithType();
}