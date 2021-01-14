package expressions;

import java.util.ArrayList;

public class Expression {
   public Expression() {
   
   }
   
   public static Expression parse(String in) {
      ArrayList<Integer> numList = new ArrayList<>();
      ArrayList<Character> charList = new ArrayList<>();
      int pos = 0;
      for(int i = 0; i < in.length(); i++) {
         //runs every time we have a char
         if(isOperator(Character.toString(in.charAt(i)))) {
            numList.add(i);
            charList.add(in.charAt(i));
         }
      }
      //if we get a literal send it back
      if (numList.size() == 0) return new ExpressionLiteral(in);

      //if we are a single operation or the right has precedence do math normally
      if(numList.size() == 1 || precedenceI(charList.get(0)) < precedenceI(charList.get(1))){
         //divide our equation along the first operator
         String left = in.substring(0, numList.get(0));
         String right = in.substring(numList.get(0) + 1);
         switch(charList.get(0)) {
               case '+':
                  return new AddExpression(new ExpressionLiteral(left), parse(right));

               case '-':
                  return new SubtractExpression(new ExpressionLiteral(left), parse(right));

               case '*':
                  return new MultExpression(new ExpressionLiteral(left), parse(right));

//               case '(': //REQUIRES OPERATORS TO BE IN FRONT OF PARENTHESIS
//                  int endP = findEnd(in, i); //finds the range we should be passing in
//                  return new ParenthExpression(parse(in.substring(i + 1, endP))); //just passes in everything
            }
      }

      //if we have cringe precendence order we parse until we die
      if(precedenceI(charList.get(0)) >= precedenceI(charList.get(1))) {
         //divide our equation along the first operator
         String left = in.substring(0, numList.get(1));
         String right = in.substring(numList.get(1) + 1);
         switch(charList.get(1)) {
            case '+':
               return new AddExpression(parse(left), parse(right));

            case '-':
               return new SubtractExpression(parse(left), parse(right));

            case '*':
               return new MultExpression(parse(left), parse(right));

//               case '(': //REQUIRES OPERATORS TO BE IN FRONT OF PARENTHESIS
//                  int endP = findEnd(in, i); //finds the range we should be passing in
//                  return new ParenthExpression(parse(in.substring(i + 1, endP))); //just passes in everything
         }
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