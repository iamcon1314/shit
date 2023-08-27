package src.MXIR.entity;


import static src.util.SomethingExisting.irCondType;

public class IRCondConst extends IRConst {
  public boolean val;

  public IRCondConst(boolean val) {
    super(irCondType);
    this.val = val;
  }

  @Override
  public String toString() {
    return val ? "true" : "false";
  }

  @Override
  public String toStringWithType() {
    return "i1 " + toString();
  }
}