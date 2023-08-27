package src.IR.type;

import src.IR.entity.*;

public class IRVoidType extends IRType {
  public IRVoidType() {
    super("void", 0);
  }

  @Override
  public String toString() {
    return "void";
  }

  @Override
  public IREntity defaultValue() {
    return null;
  }
}