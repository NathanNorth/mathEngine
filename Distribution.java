import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Distribution {
   
   //for debug purposes only
   public static void main(String[] args) {
      System.out.println(distribute("2+2*(1+3*(a+7))+3+78*(1+1)"));
   }
   
   //assumes 2*(1+1). (1+1)*2 does not work. Need to implement into low level clean
   public static String distribute(String in) {
      int[] pTable = Expression.pTable(in);
      
      List<String> pieces = new ArrayList<>(); //all the different parenthesis on the same level
      
      for(int i = 0; i < pTable.length / 2; i++) {
         //this block fundamentally can't handle multiple parenthesis on the same level
         int pStart = pTable[i * 2];
         int pEnd = pTable[i * 2 + 1];
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
         
         
         if(operator.matches("[\\*\\/]")) {
            for(int l = 0; i < insideA.length; i++) {
               results.add(outsideA.get(outsideA.size() - 1) + operator + insideA[i]);
            }
         }
         
         
         outsideA.remove(outsideA.size() - 1); //remove last list element so lists can be combined
         results.addAll(outsideA); //combine lists
         
         pieces.add(String.join("+", results));
         
      }
      
      return String.join("+", pieces); //maybe rejoining everything is cringe
   }
   
   private static List unlockedList(String[] in) { 
      List<String> list = new ArrayList<>();
      list.addAll(Arrays.asList(in));
      return list;
   }
}