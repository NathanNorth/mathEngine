package expressions;

//import expressions.ExpressionLiteral;

public class AddExpression extends dExpression {

   private Expression leftE;
   private Expression rightE;

   public AddExpression(Expression leftE, Expression rightE) {
      super.type = '+';

      this.rightE = rightE;
      this.leftE = leftE;
   }

   @Override
   public Expression getRightE() {
      return rightE;
   }

   @Override
   public Expression getLeftE() {
      return leftE;
   }

   public Expression distribute() {
      return new AddExpression(leftE.distribute(), rightE.distribute());
   }

   public String toString() { return leftE.toString() + "+" + rightE.toString(); }
}