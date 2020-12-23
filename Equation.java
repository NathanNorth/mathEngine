public class Equation {

   private final String inputEquation; //always accessible unedited version of the original inputs
   private String cleanedEquation; //prep-ed and adjusted equation used by pemdas system
   private String evaluatedEquation; //results after pemdas
 
   //construction
   public Equation(String inputEquation) {
      this.inputEquation = inputEquation;
      
      //testing for pTable
      //System.out.println(pTable("4(5896*85/5(4-7)) + (3)")[2])git;
      
      evaluatedEquation = pemdas(clean(this.inputEquation));
   }
   
   //cleans input string to fix formatting requirements for pemdas method. This is also where program should error in the future for things like forgetting a parenthesis.
   private String clean(String in) {
      String cleanedEquation = in; //working string
      
      //works way through string finding instances of numbers outside of parenthesis (eg 2(2))
      for(int i = 1; i < cleanedEquation.length() - 1; i++) { //deliberately ignores last and first character to avoid string index OOB problems
         if(cleanedEquation.charAt(i) == '(' && Character.isDigit(cleanedEquation.charAt(i - 1)))
            cleanedEquation = cleanedEquation.substring(0, i) + "*" + cleanedEquation.substring(i);
         else if(cleanedEquation.charAt(i) == ')' && Character.isDigit(cleanedEquation.charAt(i + 1)))
            cleanedEquation = cleanedEquation.substring(0, i + 1) + "*" + cleanedEquation.substring(i + 1); //this is i+1 because the * comes after the closed parenthesis
      }

      //addition and subtraction cleaning
      cleanedEquation = cleanedEquation.replace(" ", ""); //delete all whitespace
      cleanedEquation = cleanedEquation.replace("--", "+"); //double negative is a positive
      cleanedEquation = cleanedEquation.replace("-", "+-"); //any subtraction is + a negative number
      cleanedEquation = cleanedEquation.replace("++", "+"); //corrects for potential problems with previous line of code (2+-2 becomes 2++-2)
      return cleanedEquation;
   }

   //preconditions: every subtraction is + a negative number. Returns a string because we want to implement variables eventually
   private String pemdas(String stringIn) {
      
      //PARENTHESIS AND RECCURSION
      if(stringIn.contains("(")) {
         int[] pIndex = pTable(stringIn); //array with all the parenthesis we are looking at
         
         //arrays represent the list of values that need to be replaced, and correspondingly what to replace them with
         String[] beReplaced = new String[pIndex.length]; //aPos *should* be long enough because its based on how many parenthesis per layer there are
         String[] replaceW = new String[pIndex.length];
         
         //populate arrays of terms to replace and be replaced by
         for(int o = 0; o < pIndex.length; o += 2) { //loops for the *effective* length of pIndex (pIndex is actually initilized too long as currently written)
         
            //records the area that needs to be replaces according to pIndex
            beReplaced[o] = stringIn.substring(pIndex[o], pIndex[o + 1] + 1);
            
            //finds what to replace it by reccuring pemdas until we have no parenth left
            replaceW[o] = pemdas(stringIn.substring((pIndex[o] + 1), pIndex[o + 1]));
            
            //debug printing
            System.out.println("String in: " + stringIn);
            System.out.println("To replace: " + beReplaced[o] + " Replace with: " + replaceW[o]);
            System.out.println("Result: " + stringIn.replace(beReplaced[o], replaceW[o]));
         }
         
         //use arrays to replace calculated terms. Increment by two because I'm too lazy to implement proper array creation (YET)
         for(int y = 0; y < pIndex.length; y += 2) {
            stringIn = stringIn.replace(beReplaced[y], replaceW[y]); //scuffed lol
         }
      }

      String[] addition = stringIn.split("\\+"); //creates array of things to add
      
      //handle multiplication in the addition array      
      for(int i = 0; i < addition.length; i++) {
         String[] multiplication = addition[i].split("\\*"); //splits any additives that multiply
         
         //handling division in the multiplication array
         for(int k = 0; k < multiplication.length; k++) {
            String[] division = multiplication[k].split("\\/");
               
               //deal with exponents that are in the division array
               for(int z = 0; z < division.length; z++) {
                  String[] exponents = division[z].split("\\^");
                  
                                    
                  //the great divide
            
                  
                  double exp = Double.parseDouble(exponents[0]);
                  for(int q = 1; q < exponents.length; q++) {
                     exp = Math.pow(exp, Double.parseDouble(exponents[q]));
                  }
                  division[z] = "" + exp;
               }

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
      int aPos = 0; //THIS MUST BE RESET HERE so recursion works properly. How many parenthesis to worry about
      
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
      
      //SCUFFED CITY, REMAKING ARRAY TO BE THE ACCURATE LENGTH
      int[] sArray = new int[aPos];
      for(int i = 0; i < sArray.length; i++)
         sArray[i] = array[i];
      
      return sArray;
   }
   
   //ONLY HANDLES STRINGS
   private void arrayDebug(String[] arrayIn) {
      for(int i = 0; i < arrayIn.length; i++)
         System.out.println(arrayIn[i]);
   }
   
   public String toString() {
      return evaluatedEquation;
   }
}