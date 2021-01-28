package expressions;

public class MultExpression extends TwoSidedExpression {
   
   public MultExpression(Expression leftE, Expression rightE) {
      super('*',leftE, rightE);
   }

   public Expression distribute() {

      rightE = rightE.distribute();
      leftE = leftE.distribute();

      //checks if right and left side are EFFECTIVE plurals/literals //TODO: This should make the rest of this class (especially multdistribute) way simpler
      boolean rightP = precedenceI(rightE.type) < precedenceI('*'); //right plural
      boolean leftP = precedenceI(leftE.type) < precedenceI('*'); //left plural

      //two effective literals
      if(!leftP && !rightP) {
         return new MultExpression(leftE, rightE);
      }

      //left is literal and right is plural

      /* We distribute the left because we don't know if its a literal in parenthesis eg. "((2))*(7+1)". We distribute
      the right because it probably has parenthesis around it and might even be nested 'a*(b*(c+d)) */
      if(!leftP && rightP) {
         return multDistribute(leftE, rightE);
      }

      //right is literal and left is plural
      if(leftP && !rightP) {
         return multDistribute(rightE, leftE);
      }

      //both are plural (foil time). This is really just adding the distribution of the left to the distribution of the right
      if(leftP && rightP) { //this looks very similar to the multDistribute method (todo: maybe implement reccursion)
         Expression leftL = ((TwoSidedExpression)leftE).getLeftE(); //distribute to rid of parenthesis
         Expression leftR = ((TwoSidedExpression)leftE).getRightE();
         Expression firstD = multDistribute(leftL, rightE);
         Expression secondD = multDistribute(leftR, rightE);

         //initialize the right side inside parenthesis and distribute incase theres a negative that needs to distribute
         return getExpressionChar(leftE.type, firstD, secondD).distribute();
      }

      return null;
   }

   private Expression multDistribute(Expression literal, Expression set) {
      Expression tempLeftE = new MultExpression(literal, ((TwoSidedExpression)set).getLeftE());
      Expression tempRightE = new MultExpression(literal, ((TwoSidedExpression)set).getRightE());

      //we MUST call distributes on our left and right sides, this is the key to nested distribution
      return getExpressionChar(set.type, tempLeftE, tempRightE).distribute();
   }

   public String toString() {
      String leftS;
      String rightS;

      if(leftE.type == 'L' || leftE.type == '*')  leftS =  leftE.toString();
      else leftS = "[" + leftE.toString() + "]";
      if(rightE.type == 'L' || rightE.type == '*')  rightS =  rightE.toString();
      else rightS = "[" + rightE.toString() + "]";

      return leftS + "*" + rightS;
   }
}