package expressions;

public class Expression {
   public Expression() {
   
   }
   
   public static Expression parse(String in) {
      for(int i = 0; i < in.length(); i++) {
         if(isOperator(Character.toString(in.charAt(i)))) {
            String left = in.substring(0, i);
            String right = in.substring(i + 1);
            switch(in.charAt(i)) {
               case '+':
                  return new AddExpression(new ExpressionLiteral(left), parse(right));

               case '-':
                  return new SubtractExpression(new ExpressionLiteral(left), parse(right));

               case '*':
                  return new MultExpression(new ExpressionLiteral(left), parse(right));

               case '(':
                  int endP = findEnd(in, i); //finds the range we should be passing in
                  return new ParenthExpression(parse(in.substring(i + 1, endP))); //just passes in everything
            }
         }
      }
      //if we find no operators we have a literal
      return new ExpressionLiteral(in);
   }
   
   private static Boolean isOperator(String in) {
      return in.matches("[*+(-]");
   }

   //finds the end parenthesis when given a string and a starting parenthesis
   private static int findEnd(String in, int start) {
      int pos = 0;
      for(int i = start; i < in.length(); i++) {
         if(in.charAt(i) == '(') pos++;
         if(in.charAt(i) == ')') pos--;
         if(pos == 0) return i;
      }
      return -1; //this should never run
   }

   public String toString() {
      return "ERROR: THIS SHOULD ALWAYS BE OVERRIDEN";
   }
}