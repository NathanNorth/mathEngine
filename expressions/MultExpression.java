package expressions;

public class MultExpression extends TwoSidedExpression {
   private Expression leftE;
   private Expression rightE;
   
   public MultExpression(Expression leftE, Expression rightE) {
      super('*');

      this.rightE = rightE;
      this.leftE = leftE;
   }

   public Expression distribute() {

      //checks if right and left side are EFFECTIVE plurals/literals //TODO: This should make the rest of this class (especially multdistribute) way simpler
      boolean rightP = precedenceI(rightE.distribute().type) < precedenceI('*'); //right plural
      boolean leftP = precedenceI(leftE.distribute().type) < precedenceI('*'); //left plural

      //two literals
      if(!leftP && !rightP) {
         return new MultExpression(leftE.distribute(), rightE.distribute()); //distribute to rid of parenthesis
      }

      //left is literal and right is plural

      /* We distribute the left because we don't know if its a literal in parenthesis eg. "((2))*(7+1)". We distribute
      the right because it probably has parenthesis around it and might even be nested*/
      if(!leftP && rightP) {
         return multDistribute(leftE.distribute(), rightE.distribute());
      }

      //right is literal and left is plural
      if(leftP && !rightP) {
         return multDistribute(rightE.distribute(), leftE.distribute());
      }

      //both are plural (foil time). This is really just adding the distribution of the left to the distribution of the right
      if(leftP && rightP) { //this looks very similar to the multDistribute method (maybe implement reccursion)
         Expression leftL = ((TwoSidedExpression)leftE.distribute()).getLeftE();
         Expression leftR = ((TwoSidedExpression)leftE.distribute()).getRightE();
         Expression firstD = multDistribute(leftL.distribute(), rightE.distribute());
         Expression secondD = multDistribute(leftR.distribute(), rightE.distribute());

         //innitiallize the right side inside parenthesis and distribute incase theres a negative that needs to distribute
         return getExpressionChar(leftE.type, firstD, new ParenthExpression(secondD)).distribute();
      }

      return null;
   }

   private Expression multDistribute(Expression literal, Expression set) {
      if (precedenceI('*') > precedenceI(set.type)) { //if we should distribute
         Expression tempLeftE = new MultExpression(literal, ((TwoSidedExpression)set).getLeftE()).distribute(); //TODO: THIS WASN'T ORIGINALLY DISTRIBUTED, NEED MORE TESTING TO VALIDATE
         Expression tempRightE = new MultExpression(literal, ((TwoSidedExpression)set).getRightE()).distribute(); //maybe the previous line to-do makes this distribute irrelevant

         //we MUST call distributes on our left and right sides, this is the key to nested distribution
         return getExpressionChar(set.type, tempLeftE.distribute(), tempRightE.distribute());
      }
      else return new MultExpression(literal, set.distribute()); //if set has higher precedence we dont distribute
   }

   @Override
   public Expression getLeftE() { return leftE; }

   @Override
   public Expression getRightE() { return rightE; }

   public String toString() {
      return leftE.toString() + "*" + rightE.toString();
   }
}