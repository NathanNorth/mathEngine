package expressions;

public class ParenthExpression extends Expression {

   private Expression contents;
   
   public ParenthExpression(Expression contents) {
      this.contents = contents;
   
   }
   
   public String toString() {
      return "(" + contents.toString() + ")";
   } 
}