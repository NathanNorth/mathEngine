package expressions;

//import expressions.ExpressionLiteral;

public class AddExpression extends Expression {

   private Expression leftE;
   private Expression rightE;
   
   public AddExpression(Expression rightE, Expression leftE) {
      this.rightE = rightE;
      this.leftE = leftE;
   }
   
   public String toString() {
      return rightE.toString() + "+" + leftE.toString();
   }
}