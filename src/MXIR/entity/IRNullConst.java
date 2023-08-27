package src.MXIR.entity;

import src.MXIR.type.*;

public class IRNullConst extends IRConst {
  public IRNullConst() {
    super(irNullType);
  }

  public IRNullConst(IRType type) {
    super(type);
  }

  @Override
  public String toString() {
    return "null";
  }

  @Override
  public String toStringWithType() {
    return type == irNullType ? toString()  : type + " " + toString();
  }
}