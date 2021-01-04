public class Equation {

   private String equationIn;
   private String[] brokenEq = new String[3]; //this array is the left right and operator of our equation
   public final boolean result;
   public double difference;
   
   //just for testing
   public static void main(String[] args) {
      Equation bruh = new Equation("124*2>124");
      System.out.println(bruh.result);
   }
   
   //returns truth when given all three options
   public Equation(String equationInput) {
      equationIn = equationInput;
      
      result = solve(this.equationIn);
   }
   
   private boolean solve(String equationIn) {
      //finds location of operator
      int index = Processor.indexOfOperator(equationIn);
      
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
         difference = leftEq.toDouble() - rightEq.toDouble();
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