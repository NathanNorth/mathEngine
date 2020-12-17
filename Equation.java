public class Equation {

   private final String inputEquation; //always accessible unedited version of the original inputs
   private String cleanedEquation; //prep-ed and adjusted equation used by pemdas system
   private String evaluatedEquation; //results after pemdas

   public Equation(String inputEquation) {
      this.inputEquation = inputEquation;
      
      evaluatedEquation = pemdas(clean(this.inputEquation));
   }
   
   private String clean(String in) {
      return in;
   }

   //preconditions: every subtractions is + a negative number
   private String pemdas(String stringIn) {
      

      String[] addition = stringIn.split("[+]"); //creates array of things to add
      
      //sort through addition table and multiply numbers      
      for(int i = 0; i < addition.length; i++) {
         String[] multiplication = addition[i].split("[*]"); //splits any additives that multiply
         
         
         //the great divide
         
         double mult = 1; //running total
         for(int j = 0; j < multiplication.length; j++) {
            mult *= Double.parseDouble(multiplication[j]);
         }
         addition[i] = "" + mult;
      }      
      
      double add = 0; //holds the current running total
      for(int i = 0; i < addition.length; i++) {
         add += Double.parseDouble(addition[i]); //VALUEOF VS PARSEDOUBLE
      }
      return "" + add;
   }
   
   public String toString() {
      return evaluatedEquation;
   }
   
}