package expressions;

public class Expression {
   public Expression() {
   
   }
   
   public static Expression parse(String in) {
      for(int i = 0; i < in.length(); i++) {
         if(Character.toString(in.charAt(i)).matches("[*+]")) {
            String left = in.substring(0, i);
            String right = in.substring(i, in.length());
            switch(in.charAt(i)) {
               case '+':
                  return AdditionExpression(new ExpressionLiteral(left), parse(right));
            }
         }
      }
      return new Expression();
   }
   
   public String toString() {
      return "ERROR: THIS SHOULD ALWAYS BE OVERRIDEN";
   }
}