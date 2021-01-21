package expressions;

public class ExpressionLiteral extends Expression {
   private String val;
   public final boolean isConstant;
   
   public ExpressionLiteral(String in) {
      super('L');
      val = in;

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