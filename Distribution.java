import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Distribution {
   
   //for debug purposes only
   public static void main(String[] args) {
      System.out.println(distribute("2+2*(1*(4+8)+3*(a+7))+3+78*(1+1)"));
   }
   
   //assumes 2*(1+1). (1+1)*2 does not work. Need to implement into some type of clean
   public static String distribute(String in) {
      int[] pTable = Expression.pTable(in);
      String firstH;
      String secondH;
      
      //weird inefficient reccursion
      if(pTable.length > 2 && in.charAt(pTable[1] + 1) == '+') { //char check is for safety, eventually should be moved to the predistribute clean
         firstH = in.substring(0, pTable[1] + 1);
         secondH = in.substring(pTable[1] + 2, in.length());
         return distribute(firstH) + "+" + distribute(secondH);
      }
      
      
      List<String> pieces = new ArrayList<>(); //all the different parenthesis on the same level
      
      int pStart = pTable[0];
      int pEnd = pTable[1];
      String operator = in.charAt(pStart - 1) + "";
      String outside = in.substring(0, pStart - 1);
      String inside = in.substring(pStart + 1, pEnd);
      
      //recursion
      if(inside.contains("(")) {
         inside = distribute(inside);
      }
      
      List<String> outsideA = unlockedList(outside.split("\\+"));
      
      String[] insideA = inside.split("\\+");
      List<String> results = new ArrayList<String>();
      
      //raw distribution
      if(operator.matches("[\\*\\/]")) {
         for(int l = 0; l < insideA.length; l++) {
            results.add(outsideA.get(outsideA.size() - 1) + operator + insideA[l]);
         }
      }
      
      
      outsideA.remove(outsideA.size() - 1); //remove last list element so lists can be combined
      results.addAll(outsideA); //combine lists
      
      return String.join("+", results);
   }
   
   private static List unlockedList(String[] in) { 
      List<String> list = new ArrayList<>();
      list.addAll(Arrays.asList(in));
      return list;
   }
}