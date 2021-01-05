public class Cleaner {

   public static String highLevelClean(String in) { 
      // replaces two character operators with single character alternatives to make other methods run smoother
      in = in.replace(">=", "~");
      in = in.replace("<=", "`");
      
      //prevents string OOB
      if(in.equals("")) return "noSign";
      
      // input has no = < > <= >=      
      if (Processor.indexOfOperator(in) == -1) {
         
         // Negative combined with certain operators in certain ways return nosign
         if (in.contains("-*") || in.contains("-^") || in.contains("-/")) {
            return "noSign";
         }

         // * or / or ^ at start returns nosign
         if (in.startsWith("*") || in.startsWith("/") || in.startsWith("^")) {
            return "noSign";
         }

         // Checks if one operation is followed by any other
         for (int i = 1; i < in.length(); i++) {
            if (isNonNegOperator(in.charAt(i)) && isNonNegOperator(in.charAt(i-1))) {
               return "noSign";
            }
         }

         // Ends with operators, operator gets chopped
         if (isOperator(in.charAt(in.length() - 1))) {
            in = in.substring(0, in.length() - 1);
         }

         // If there are 2+ extra start parenthesis or extra end parenthesis, return nosign
         if (parenthesisCount(in) < 0 || parenthesisCount(in) > 1) {
            return "noSign";
         }
         
         // If there is 1 end parenthesis missing, add the end parenthesis
         if (parenthesisCount(in) == 1) {
            in = in + ")";
         }
         return in + "=";
      }   
      return in;
   }

   // Checks the number of parentheses in the input
   private static int parenthesisCount (String in) {
      int count = 0;
      for (int i = 0; i < in.length(); i++) {
         // Open parenthesis bring count up 1, closed parenthesis bring count down 1
         if (in.charAt(i) == '(') {
            count++;
         }
         if (in.charAt(i) == ')') {
            count--;
         }
      }
      return count;
   }

   // Checks if a character is an operator
   private static boolean isOperator (char c) {
      if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
         return true;
      } else return false;
   }

   // Checks if a character is an operator that isn't negative
   private static boolean isNonNegOperator (char c) {
      if (c == '+' || c == '*' || c == '/' || c == '^') {
         return true;
      } else return false;
   }

}