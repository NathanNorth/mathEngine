public class Cleaner {
   
   public String highLevelClean(String in) { 
      //replaces two character operators with single character alternatives to make other methods run smoother
      in = in.replace(">=", "~");
      in = in.replace("<=", "`");
      
      return in;
   }
}