import java.util.Scanner;

public class StringTesting {
   
   public static void main(String[] args) {
      System.out.println("Equation:");
      Scanner in = new Scanner(System.in);
      String num1 = Processor.process(Cleaner.highLevelClean(in.nextLine()));
      System.out.println("Final result: " + num1);
   }
}