//package IR.type;
package src.MXIR.type;
import src.MXIR.entity.*;

import static src.util.SomethingExisting.irIntConst0;

public class IRIntType extends IRType {
  public int bitWidth;

  public IRIntType(int bitWidth) {
    super("i" + String.valueOf(bitWidth), bitWidth / 8);
    this.bitWidth = bitWidth;
  }

  @Override
  public String toString() {
    return "i" + String.valueOf(bitWidth);
  }

  @Override
  public IREntity defaultValue() {
    return irIntConst0;
  }
}