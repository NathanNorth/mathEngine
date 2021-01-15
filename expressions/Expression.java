package expressions;

import java.util.ArrayList;

public class Expression {
   public Expression() {
   
   }
   
   public static Expression parse(String in) {
      ArrayList<Integer> numList = new ArrayList<>(); //list of index for operators
      ArrayList<Character> charList = new ArrayList<>();

      ArrayList<Integer> numListP = new ArrayList<>(); //list of index for parenthesis
      int pos = 0; //how deep inside parenthesis we are

      for(int i = 0; i < in.length(); i++) {
         if(pos == 0 && in.charAt(i) == '(') numListP.add(i);
         if(in.charAt(i) == '(') pos++;
         if(in.charAt(i) == ')') pos--;
         if(pos == 0 && in.charAt(i) == ')') numListP.add(i);

         //runs every time we have a char and aren't inside parenthesis
         if(pos == 0 && isOperator(Character.toString(in.charAt(i)))) {
            numList.add(i);
            charList.add(in.charAt(i));
         }
      }
      if(numList.size() == 0 && in.charAt(0) == '(') {
         return new ParenthExpression(parse(in.substring(1, in.length() - 1)));
      }

      //if we get a literal send it back
      if (numList.size() == 0) return new ExpressionLiteral(in);

      //if we are a single operation or the right has precedence do math normally
      if(numList.size() == 1 || precedenceI(charList.get(0)) < precedenceI(charList.get(1))){
         //divide our equation along the first operator
         String left = in.substring(0, numList.get(0));
         String right = in.substring(numList.get(0) + 1);
         return getExpressionChar(charList.get(0), parse(left), parse(right));
      }

      //if we have cringe precendence order we parse until we die
      if(precedenceI(charList.get(0)) >= precedenceI(charList.get(1))) {
         //divide our expression along the second operator
         String left = in.substring(0, numList.get(1));
         String right = in.substring(numList.get(1) + 1);
         //returns an expression based on the second operator
         return getExpressionChar(charList.get(1), parse(left), parse(right));
      }

      //should never run
      return null;
   }

   //returns an expression based on a char and two passed through expressions
   private static Expression getExpressionChar(char c, Expression left, Expression right) {
      switch(c) {
         case '+':
            return new AddExpression(left, right);

         case '-':
            return new SubtractExpression(left, right);

         case '*':
            return new MultExpression(left, right);
      }
      //should never run
      return null;
   }

   //returns an int value of precedence for a given operator
   private static int precedenceI(char in) {
      if(in == '+') return 0;
      if(in == '-') return 1;
      if(in == '*') return 2;
      if(in == '/') return 3;
      //if(in == '') return 4;
      return -1;
   }

   private static Boolean isOperator(String in) {
      return in.matches("[*+-]");
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