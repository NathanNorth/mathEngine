import java.util.Scanner;

public class StringTesting {
   
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      Equation num1 = new Equation(in.nextLine());
      System.out.println("Final result: " + num1);
   }
}