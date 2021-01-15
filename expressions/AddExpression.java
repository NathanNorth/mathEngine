package expressions;

//import expressions.ExpressionLiteral;

public class AddExpression extends Expression {

   private Expression leftE;
   private Expression rightE;

   public AddExpression(Expression leftE, Expression rightE) {
      this.rightE = rightE;
      this.leftE = leftE;
   }

   public String toString() { return leftE.toString() + "+" + rightE.toString(); }
}