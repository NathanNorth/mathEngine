import java.util.Scanner;
import expressions.*;

public class StringTesting {
   
   public static void main(String[] args) {
      //Debug
      expressions.Expression pog = expressions.Expression.parse("2*(1+1*(13+6))");
      System.out.println(pog);
   
      System.out.println("Equation:");
      Scanner in = new Scanner(System.in);
      String num1 = Processor.process(Cleaner.highLevelClean(in.nextLine()));
      System.out.println("Final result: " + num1);
      System.out.println("Number of expressions used to solve: " + Expression.numInit);
   }
}