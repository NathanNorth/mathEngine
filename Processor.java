public class Processor {

   public static String process(String in) {
      if(in.equals("noSign")) return "";
      
      int index = indexOfOperator(in);
      
      //AS PRESENTLY WRITTEN DOES NOT PROVIDE ADEQUATE FEEDBACK FOR <= AND >
      if(index == 0) { //if input equation is an expression starting with =
         Expression num1 = new Expression(in.substring(1, in.length()));
         return num1.toString();
      }
      else if(index == in.length() - 1) { //if input equation is an expression ending with =
         Expression num1 = new Expression(in.substring(0, in.length() - 1));
         return num1.toString();
      }
      else {
         Equation eq1 = new Equation(in);
         return "" + eq1.result;
      }
   }
   
   //returns the location of operators
   public static int indexOfOperator(String in) {
      int index = in.indexOf('=');
      if(index == -1) index = in.indexOf('<'); //if there is no = we test for '>' and '<'
      if(index == -1) index = in.indexOf('>');
      if(index == -1) index = in.indexOf('~');
      if(index == -1) index = in.indexOf('`');
      
      return index;
   }
}