public class Equation {

   private final String inputEquation; //always accessible unedited version of the original inputs
   private String cleanedEquation; //prep-ed and adjusted equation used by pemdas system
   private String evaluatedEquation; //results after pemdas

   public Equation(String inputEquation) {
      this.inputEquation = inputEquation;
      
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

   //preconditions: every subtraction is + a negative number
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
   
   public String toString() {
      return evaluatedEquation;
   }
}