public class Cleaner {

   public static String highLevelClean(String in) { 
      //replaces two character operators with single character alternatives to make other methods run smoother
      in = in.replace(">=", "~");
      in = in.replace("<=", "`");
      
      if (Processor.indexOfOperator(in) == -1) {
         return "noSign";
      }   
      if (in.endsWith("+") || in.endsWith("-") || in.endsWith("*") || in.endsWith("/")) {
         System.out.println("detecting operator at end");
         in = in.substring(0, in.length() - 1);
      }
      return in;
   }

}