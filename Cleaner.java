public class Cleaner {
   
   public static String highLevelClean(String in) { 
      //replaces two character operators with single character alternatives to make other methods run smoother
      in = in.replace(">=", "~");
      in = in.replace("<=", "`");
      
      if(Processor.indexOfOperator(in) == -1) {
         return "noSign";
      }
      return in;
   }
}