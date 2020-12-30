import java.text.DecimalFormat; //we need this to avoid passing in garbage in scientific format
import java.math.BigDecimal; //for things that just round themselves because built different

public class Processor {

   public static String process(String in) {
      if(in.equals("noSign")) return "";
      
      int index = indexOfOperator(in);
      
      //GUESS AND CHECK ALGORYTHM
      if(in.contains("x") && index != 0 && index != in.length() - 1) {
         //weird out of 10 algorythm 
         return "" + solveFor("x", in, -Double.MAX_VALUE / 2, Double.MAX_VALUE / 2, 10, 0); //this margin of error is cringe and doesnt work for all values
         
         //binary algorythm
         //return "" + solveFor2("x", in, -Double.MAX_VALUE / 2, Double.MAX_VALUE / 2, 0);
      }
      
      if(in.contains("x")) { //this only runs if there is an x and a unfinished expression
         return "";
      }
      
      //AS PRESENTLY WRITTEN DOES NOT PROVIDE ADEQUATE FEEDBACK FOR <= AND >
      if(index == 0) { //if input equation is an expression starting with =
         Expression num1 = new Expression(in.substring(1, in.length()));
         return num1.toString();
      }
      else if(index == in.length() - 1) { //if input equation is an expression ending with =
         Expression num1 = new Expression(in.substring(0, in.length() - 1));
         return num1.toString();
      }
      else {
         Equation eq1 = new Equation(in);
         return "" + eq1.result;
      }
   }
   
   private static double solveFor2(String letter, String in, double min, double max, double error) { //cuts represent the number of different tests that will be run before comparing for closest answer
           
      DecimalFormat df = new DecimalFormat("0");
      df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
      
      double guessInput = random(min, max); //array of all inputs to test for
      
      String guess = in.replace(letter, "(" + df.format(guessInput) + ")");
      Equation eq1 = new Equation(guess);
      double guessOutput = eq1.difference;
      
      if(guessOutput == 0) return guessInput;
      if(guessOutput < 0) return solveFor2(letter, in, guessInput, max, error);
      if(guessOutput > 0) return solveFor2(letter, in, min, guessInput, error);
      
      return 0; //this should never run
   }

   
   //preconditions: read the variable names
   //postconditions a double representing a x value, don't input equations with multiple x vals
   private static double solveFor(String letter, String in, double min, double max, int cuts, double error) { //cuts represent the number of different tests that will be run before comparing for closest answer
      Double[] guessInput = new Double[cuts]; //array of all inputs to test for
      Double[] guessOutput = new Double[cuts]; //array of all outputs based on input
      double range = max - min;
      double mult = range / cuts; //mult represents the size between each different guessInput
         
      DecimalFormat df = new DecimalFormat("0");
      df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
      
      //populate guessInput
      for(int i = 0; i < cuts; i++) {
         guessInput[i] = i * mult + min;
      }
      
      //populate guessOutput
      for(int i = 0; i < cuts; i ++) {
         
         String guess = in.replace(letter, "(" + df.format(guessInput[i]) + ")");
         Equation eq1 = new Equation(guess);
         guessOutput[i] = eq1.difference;
         
      }
      
      //find the smallest guessOuput and records the associated guessinput
      double bestGuess = Double.MAX_VALUE;
      double bestInput = 0; //SCUFFED SHOULD NOT RETURN 0 IN CASE OF OOB INPUTS
      for(int i = 0; i < cuts; i++) {
         if(Math.abs(guessOutput[i]) < bestGuess) {
            bestGuess = Math.abs(guessOutput[i]);
            bestInput = guessInput[i];
         }
      }
      
      //if our current best guess is within our margin of error then return the input that made it
      if(bestGuess <= error || mult == 0) { //if our mult is 0 the program is looping might as well return a probably accurate number NEEDS MORE TESTING
         return round(bestInput, error);
      }
      else { //else we run ourselves again but with more focused guess range
      
         
         System.out.println("Best Guess: " + bestGuess + "\nMult: " + mult + "\nRange: " + range);
         
         return solveFor("x", in, bestInput - mult, bestInput + mult, cuts, error); //maybe mult / 2 ARGUE WITH ERIC
      }
   }
   
   private static double round(double in, double places) {
      if(places != 0) //checking if no margin of error was passed in
         return Math.round(in / places) * places;
      else
         return in;
   }
   
   //returns the location of operators
   public static int indexOfOperator(String in) {
      int index = in.indexOf('=');
      if(index == -1) index = in.indexOf('<'); //if there is no = we test for '>' and '<'
      if(index == -1) index = in.indexOf('>');
      if(index == -1) index = in.indexOf('~');
      if(index == -1) index = in.indexOf('`');
      
      return index;
   }
   
   
   private static double random(double min, double max) {
      return (Math.random() * (max - min) + min);
   }
   
}