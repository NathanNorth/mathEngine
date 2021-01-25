package expressions;

public class ExpressionLiteral extends Expression {
   final private String val;
   private final int sign;
   public final boolean isConstant;
   public final boolean isInt;

   /* The ExpressionLiteral class does a teeny tiny bit of parsing to figure out if it has a negative (when '_' is the
   first character). Then it figures out whether it is a number, and finally it figured out if it is an int. Important
   to note is the fact that the string value is stored WITHOUT the sign and so when returning values the sign needs to
   be sent. */
   public ExpressionLiteral(String in) {
      super('L');

      //if we have an underscore we are negative
      if(in.charAt(0) == '_') {
         val = in.substring(1);
         sign = -1;
      }
      else {
         val = in;
         sign = 1;
      }


      //sets constant flag if number is a number
      isConstant = val.matches("(.*)[0-9](.*)");
      isInt = isConstant && Double.parseDouble(val) % 1 == 0; //checks to see if we have an int constant TODO A LITTLE SCUFFED
   }

   public double getDouble() {
      if(isConstant) return sign * Double.parseDouble(val);
      else {
         System.out.println("WARNING: ATTEMPTING TO PARSE A NON CONSTANT VALUE AS DOUBLE, RETURNING -DOUBLE_MAX");
         return -Double.MAX_VALUE; //very clear error state (maybe too clear?)
      }
   }

   public Expression distribute() {
      return this;
   }
   
   public String toString() {
      if(sign == -1) return "-" + val;
      else return val;
   }
}