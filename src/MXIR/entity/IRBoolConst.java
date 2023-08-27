package src.MXIR.entity;

import static src.util.SomethingExisting.irBoolType;

public class IRBoolConst extends IRConst {
  public boolean val;

  public IRBoolConst(boolean val) {
    super(irBoolType);
    this.val = val;
  }

  @Override
  public String toString() {
    return val ? "1" : "0";
  }

  @Override
  public String toStringWithType() {
    return "i8 " + toString();
  }
}