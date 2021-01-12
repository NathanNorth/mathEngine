public class Distribution {
   
   //for debug purposes only
   public static void main(String[] args) {
      System.out.println(distribute("2+2*(1+1)"));
   }
   
   //assumes 2*(1+1). (1+1)*2 does not work. Need to implement into low level clean
   public static String distribute(String in) {
      int[] pTable = Expression.pTable(in);
      for(int i = 0; i < pTable.length / 2; i++) {
         int pStart = pTable[i * 2];
         int pEnd = pTable[i * 2 + 1];
         char Operator = in.charAt(pStart - 1);
         String outside = in.substring(0, pStart - 1);
         String inside = in.substring(pStart + 1, pEnd);
         
         //recursion
         if(inside.contains("(")) {
            inside = distribute(inside);
         }
         
         String[] outsideA = outside.split("+");
         String[] insideA = inside.split("+");
         
         
      }
      
      return "piss";
   }
}