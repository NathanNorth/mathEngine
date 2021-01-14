package expressions;

public class ExpressionLiteral extends Expression {
   private String val;
   public final boolean isConstant;
   
   public ExpressionLiteral(String in) {
      val = in;
      
      //sets constant flag if number is number
      if(in.matches("[0-9]")) isConstant = true;
      else isConstant = false;
   }
   
   public String toString() {
      return val;
   }
}