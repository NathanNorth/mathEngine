public class Equation {

   private String equationIn;
   private String[] brokenEq = new String[3]; //this array is the left right and operator of our equation
   private boolean result;
   
   //just for testing
   public static void main(String[] args) {
      Equation bruh = new Equation("124>=124");
      System.out.println(bruh.result);
   }
   
   //returns truth when given all three options
   public Equation(String equationInput) {
      equationIn = equationInput;
      
      //deals with less than and greater than by replacing them with placeholder characters so that its easier to create brokenEq
      equationIn = equationIn.replace(">=", "~");
      equationIn = equationIn.replace("<=", "`");
      
      result = solve(this.equationIn);
   }
   
   private boolean solve(String equationIn) {
      //finds location of operator
      int index = equationIn.indexOf('=');
      if(index == -1) index = equationIn.indexOf('<'); //if there is no = we test for '>' and '<'
      if(index == -1) index = equationIn.indexOf('>');
      if(index == -1) index = equationIn.indexOf('~');
      if(index == -1) index = equationIn.indexOf('`');
      
      //brakes up equation into arrays
      brokenEq[0] = equationIn.substring(0, index);
      brokenEq[1] = equationIn.substring(index, index + 1);
      brokenEq[2] = equationIn.substring(index + 1, equationIn.length());
      
      //creates expression objects
      Expression leftEq = new Expression(brokenEq[0]);
      Expression rightEq = new Expression(brokenEq[2]);
      
      //evaluates for a boolean
      switch(brokenEq[1]) {
         case "=":
         return leftEq.toDouble() == rightEq.toDouble();
      
         case "<":
         return leftEq.toDouble() < rightEq.toDouble();
         
         case ">":
         return leftEq.toDouble() > rightEq.toDouble();
         
         case "~": //greater than or equals to
         return leftEq.toDouble() > rightEq.toDouble() || leftEq.toDouble() == rightEq.toDouble();
         
         case "`": //less than or equals to
         return leftEq.toDouble() < rightEq.toDouble() || leftEq.toDouble() == rightEq.toDouble();
      }
      return false; //satisfy java, incase some loser passes through garbage
   }

}