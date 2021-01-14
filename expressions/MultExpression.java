package expressions;

public class MultExpression extends Expression {
   private Expression leftE;
   private Expression rightE;
   
   public MultExpression(Expression rightE, Expression leftE) {
      this.rightE = rightE;
      this.leftE = leftE;
   }
   
   public String toString() {
      return rightE.toString() + "*" + leftE.toString();
   }
}