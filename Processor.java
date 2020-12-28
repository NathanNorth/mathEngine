public class Processor {

   public static String process(String in) {
      if(in.equals("noSign")) return "";
      
      int index = indexOfOperator(in);
      
      //GUESS AND CHECK ALGORYTHM
      if(in.contains("x") && index != 0 && index != in.length() - 1) {
         return "" + solveFor("x", in, 1000, 0);
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
   
   //REWRITE TO HAVE START RANGE END RANGE YAAAAS
   private static double solveFor(String letter, String in, double times, double start) {
      Double[] guesses = new Double[20];
      int pos = 0;
      
      //currently checks positives and negatives because im dumb and hate my life
      for(double i = 0; i < 10; i ++) {
         double plug = (i + start) * times / 10; //this is cringe and hard to trage
         
         String guess = in.replace(letter, "(" + plug + ")");
         Equation eq1 = new Equation(guess);
         guesses[pos] = eq1.difference;
         pos++;
            
         String guessNegative = in.replace(letter, "(" + -plug + ")");
         Equation eq2 = new Equation(guessNegative); 
         guesses[pos] = eq2.difference;
         pos++;
      } //NOTE LAST ITEM OF ARRAY MIGHT BE SCUFFED
      
      double bestGuess = times;
      int bestAPos = -1;
      for(int i = 0; i < guesses.length; i++) {
         if(Math.abs(guesses[i]) < bestGuess) {
            bestGuess = Math.abs(guesses[i]);
            bestAPos = i;
         }
      }
      
      //attrocious demon code from hell becuse my array is needlessly complicated
      double realI = 0;
      if(bestAPos % 2 == 1) {
         realI =  -(bestAPos - 1) / 2;
      }
      else {
         realI = bestAPos / 2;
      }
      
      realI = (realI + start) * times / 10;  //recreates the plug value which is the actual x val we care about
      if(bestGuess == 0) {
         return realI;
      }
      else {
         return solveFor("x", in, times / 10, realI);
      }
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
}