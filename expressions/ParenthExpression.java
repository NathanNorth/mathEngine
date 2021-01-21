package expressions;

public class ParenthExpression extends Expression {

   private Expression contents;
   public final boolean isLiteral;
   
   public ParenthExpression(Expression contents) {
      super(contents.type); //parenthesis are the type of their contents
      this.contents = contents;

      //flag for the distribute class to use
      isLiteral = contents instanceof ExpressionLiteral;
   }

   public Expression distribute() {
      return contents.distribute();
   }
   
   public String toString() {
      return "(" + contents.toString() + ")";
   } 
}