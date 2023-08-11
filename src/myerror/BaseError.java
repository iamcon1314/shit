package src.myerror;

import src.util.Position;

public class BaseError extends RuntimeException{
    public String message;
    public Position pos;
    public BaseError(Position pos, String message){
        this.message=message;
        this.pos=pos;
    }
    @Override
    public String toString()
    {
        return "MxCompiler Error: "+message+pos.toString();
    }
}

// package utils;

// public class BaseError extends RuntimeException {
//   public String message;
//   public Position pos;

//   public BaseError(Position pos, String message) {
//     this.message = message;
//     this.pos = pos;
//   }

//   @Override
//   public String toString() {
//     return "Error: " + message + " at " + pos.toString();
//   }
// }