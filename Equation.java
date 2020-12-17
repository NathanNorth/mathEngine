public class Equation {

   private final String inputEquation; //always accessible unedited version of the original inputs
   private String cleanedEquation; //prep-ed and adjusted equation used by pemdas system
   private String evaluatedEquation; //results after pemdas
   
   //construction
   public Equation(String inputEquation) {
      this.inputEquation = inputEquation;
      
      //testing for pTable
      //System.out.println(pTable("4(5896*85/5(4-7)) + (3)")[2]);
      
      evaluatedEquation = pemdas(clean(this.inputEquation));
   }
   
   //cleans input string to fix formatting requirements for pemdas method
   private String clean(String in) {
      cleanedEquation = in.replace(" ", ""); //delete all whitespace
      cleanedEquation = cleanedEquation.replace("--", "+"); //double negative is a positive
      cleanedEquation = cleanedEquation.replace("-", "+-"); //any subtraction is + a negative number
      cleanedEquation = cleanedEquation.replace("++", "+"); //corrects for potential problems with previous line of code (2+-2 becomes 2++-2)
      return cleanedEquation;
   }

   //preconditions: every subtraction is + a negative number. Returns a string because we want to implement variables eventually
   private String pemdas(String stringIn) {

      String[] addition = stringIn.split("\\+"); //creates array of things to add
      
      //handle multiplication in the addition array      
      for(int i = 0; i < addition.length; i++) {
         String[] multiplication = addition[i].split("\\*"); //splits any additives that multiply
         
         //handling division in the multiplication array
         for(int k = 0; k < multiplication.length; k++) {
            String[] division = multiplication[k].split("\\/");
            
            //the great divide
            
            double div = Double.parseDouble(division[0]); //first item in the division array should be divided
            for(int l = 1; l < division.length; l++) {
               div = div / Double.parseDouble(division[l]);
            }
            multiplication[k] = "" + div;
         }
            
         double mult = 1; //running total
         for(int j = 0; j < multiplication.length; j++) {
            mult *= Double.parseDouble(multiplication[j]);
         }
         addition[i] = "" + mult;
      }      
      
      double add = 0; //holds the current running total
      for(int i = 0; i < addition.length; i++) {
         add += Double.parseDouble(addition[i]);
      }
      return "" + add;
   }
   
   //returns a table with the open and closes parenths
   private int[] pTable(String str) {
      int[] array = new int[str.length()];
      int pos = 0; //number relative to being inside paren
      int aPos = 0; //next number in the array
      
      //loops through string
      for(int i = 0; i < str.length(); i++) {
         if(str.charAt(i) == '(') {
            if(pos == 0) {
               array[aPos] = i;
               aPos++; //just increments to know what the next index in our array should be
            }
            pos++; //we are in a parenthesis so we increment this one
         }
         if(str.charAt(i) == ')') {
            pos--; //leaving parenthesis
            if(pos == 0) { //checks to make sure we are leaving the right parenthesis
               array[aPos] = i;
               aPos++;
            }
         }
      }
      return array;
   }
   
   //ONLY HANDLES STRING
   private void arrayDebug(String[] arrayIn) {
      for(int i = 0; i < arrayIn.length; i++)
         System.out.println(arrayIn[i]);
   }
   
   public String toString() {
      return evaluatedEquation;
   }
}