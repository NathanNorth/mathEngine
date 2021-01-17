package expressions;

import java.util.ArrayList;

public class Expression {

   protected char type = 'e';

   public Expression() { }

   //purely for overriding
   public Expression distribute() { return null; }

   public static Expression parse(String in) {
      ArrayList<Integer> numList = new ArrayList<>(); //list of index for operators
      ArrayList<Character> charList = new ArrayList<>(); //list of all our operators

      int pos = 0; //how deep inside parenthesis we are

      //populates numlist and charlist ignoring parenthesis
      for(int i = 0; i < in.length(); i++) {
         if(in.charAt(i) == '(') pos++;
         if(in.charAt(i) == ')') pos--;

         //runs every time we have a char and aren't inside parenthesis
         if(pos == 0 && isOperator(Character.toString(in.charAt(i)))) {
            numList.add(i);
            charList.add(in.charAt(i));
         }
      }

      //if the only thing inputted to us is parenthesis then just parse whats inside as a parenthexpression
      if(in.charAt(0) == '(' && numList.size() == 0) {
         return new ParenthExpression(parse(in.substring(1, in.length() - 1)));
      }

      //if we get a literal send it back
      if (numList.size() == 0) return new ExpressionLiteral(in);

      //if we are a single operation or the right has precedence do math normally
      if(numList.size() == 1 || precedenceI(charList.get(0)) < precedenceI(charList.get(1))){
         //divide our equation along the first operator
         String left = in.substring(0, numList.get(0));
         String right = in.substring(numList.get(0) + 1);
         return getExpressionChar(charList.get(0), parse(left), parse(right)); //the left side should already be a literal but we parse just in case its parenthesis
      }

      //if we have cringe precendence order we parse until we die
      if(precedenceI(charList.get(0)) > precedenceI(charList.get(1))) {

         String left = in.substring(0, numList.get(1));
         String right = in.substring(numList.get(1) + 1);

         //find the item in charlist that has lower precedence than the one before it, and split THERE instead of just one ahead

         //returns an expression based on the second operator
         return getExpressionChar(charList.get(1), parse(left), parse(right));
      }

      //if we have repeated characters we need to be careful about how we call them
      if(precedenceI(charList.get(0)) == precedenceI(charList.get(1))) {

         //if the upcoming loop doesn't find a character of lower precedence, we have default values that won't break subtraction
         int index = numList.size() - 1;
         String left = in.substring(0, numList.get(index));
         String right = in.substring(numList.get(index) + 1);

         int currentPrec = precedenceI(charList.get(0)); //keeping track of the lowest precedence we've found

         //this ugly loop finds the lowest precedence to the right of our repeated character
         for(int i = 0; i < charList.size(); i++) {
            if(precedenceI(charList.get(i)) < currentPrec) {
               left = in.substring(0, numList.get(i));
               right = in.substring(numList.get(i) + 1);
               index = i;
               currentPrec = precedenceI(charList.get(i));
            }
         }

         //if we did manage to find something with lower precedence we recur from that number
         return getExpressionChar(charList.get(index), parse(left), parse(right)); //return code parsed to be split at the right index
      }

      //should never run
      return null;
   }

   //returns an expression based on a char and two passed through expressions
   public static Expression getExpressionChar(char c, Expression left, Expression right) {
      switch(c) {
         case '+':
            return new AddExpression(left, right);

         case '-':
            return new SubtractExpression(left, right);

         case '*':
            return new MultExpression(left, right);

         case '/':
            return new DivExpression(left, right);

         case '^':
            return new PowExpression(left, right);
      }
      //should never run
      return null;
   }

   //TODO: Make this a public final list or smth. Kinda dumb as a method
   //returns an int value of precedence for a given operator
   public static int precedenceI(char in) {
      if(in == '+') return 0;
      if(in == '-') return 1;
      if(in == '*') return 2;
      if(in == '/') return 3;
      if(in == '^') return 4;
      return -1;
   }

   private static Boolean isOperator(String in) {
      return in.matches("[\\^*+/-]");
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