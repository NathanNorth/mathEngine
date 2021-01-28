package expressions;

//import expressions.ExpressionLiteral;

public class AddExpression extends TwoSidedExpression {

   public AddExpression(Expression leftE, Expression rightE) {
      super('+', leftE, rightE);
   }

   public Expression distribute() {
      return new AddExpression(leftE.distribute(), rightE.distribute());
   }

   public String toString() { return leftE.toString() + "+" + rightE.toString(); }
}