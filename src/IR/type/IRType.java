package src.IR.type;

import src.IR.entity.*;
import src.util.SomethingExisting;

public abstract class IRType implements SomethingExisting {
  public String name;
  public int size;  // cnt of bit

  public IRType(String name) {
    this.name = name;
  }

  public IRType(String name, int size) {
    this.name = name;
    this.size = size;
  }

  public abstract String toString();
  public abstract IREntity defaultValue();
}

