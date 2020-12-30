public class Cleaner {

   public static String highLevelClean(String in) { 
      // replaces two character operators with single character alternatives to make other methods run smoother
      in = in.replace(">=", "~");
      in = in.replace("<=", "`");
      
      // input has no = < > <= >=

      if (Processor.indexOfOperator(in) == -1) {
         // Ends with operators, operator gets chopped
         if (in.endsWith("+") || in.endsWith("-") || in.endsWith("*") || in.endsWith("/") || in.endsWith("^")) {
            in = in.substring(0, in.length() - 1);
         }

         // Consecutive operators return nosign
         if (in.contains("++") || in.contains("**") || in.contains("//") || in.contains("^^") || in.substring(0, 2).equals("--")) {
            return "noSign";
         }

         // Make Parenthesis Method, looking at pTable (but not using it) for info
            // Start parenthesis but no end fills in end and returns in
            // End parenthesis but no start returns nosign

         // * or / or ^ at start returns nosign

      }   
      return in;
   }

}