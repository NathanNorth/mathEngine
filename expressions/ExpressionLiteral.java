package expressions;

public class ExpressionLiteral extends Expression {
   private String val;
   public final boolean isConstant;
   
   public ExpressionLiteral(String in) {
      val = in;
      super.type = 'L';

      //sets constant flag if number is number
      isConstant = in.matches("[0-9]");
   }

   public Expression distribute() {
      return this;
   }
   
   public String toString() {
      return val;
   }
}