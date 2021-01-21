import java.util.Scanner;
import expressions.*;

public class StringTesting {
   
   public static void main(String[] args) {
      //Debug
      //String test = "1-(2-3+4-(5-(7+8)))";
      //String test = "1*(2+3*(4+5*(6-7*8)))";
      //String test = "3-(4*5)";
      //String test = "1/2/3";
      //String test = "c*(13/(a+b*7))";
      //String test = "(a/(1/(b-6)+c*e))*(1+2)";
      //String test = "(1/(2+3))/a";
      String test = "a/b^2";
      expressions.Expression pog = expressions.Expression.parse(test);
      System.out.println(pog);
      expressions.Expression bog = expressions.Expression.parse(test).distribute();
      System.out.print(bog);
   
      System.out.println("Equation:");
      Scanner in = new Scanner(System.in);
      String num1 = Processor.process(Cleaner.highLevelClean(in.nextLine()));
      System.out.println("Final result: " + num1);
      System.out.println("Number of expressions used to solve: " + Expression.numInit);
   }
}