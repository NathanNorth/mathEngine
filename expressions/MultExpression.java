package expressions;

public class MultExpression extends Expression {
   private Expression leftE;
   private Expression rightE;
   
   public MultExpression(Expression leftE, Expression rightE) {
      this.rightE = rightE;
      this.leftE = leftE;
   }
   
   public String toString() {
      return leftE.toString() + "*" + rightE.toString();
   }
}