package expressions;

import javax.swing.text.StyledEditorKit;

public class MultExpression extends dExpression {
   private Expression leftE;
   private Expression rightE;
   
   public MultExpression(Expression leftE, Expression rightE) {
      super.type = '*';

      this.rightE = rightE;
      this.leftE = leftE;
   }

   public Expression distribute() {

      //finds out right and left are plural by checking if they aren't a literal
      boolean rightP = rightE.type != 'L'; //right plural
      boolean leftP = leftE.type != 'L'; //left plural

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

      //both are plural (foil time)
      if(leftP && rightP) {
         //TODO: Foil terms
      }

      return null;
   }

   private Expression multDistribute(Expression literal, Expression set) {
      if (precedenceI('*') > precedenceI(set.type)) { //if we should distribute
         Expression tempLeftE = new MultExpression(literal, ((dExpression)set).getLeftE());
         Expression tempRightE = new MultExpression(literal, ((dExpression)set).getRightE());

         //we MUST call distributes on our left and right sides, this is the key to nested distribution
         return getExpressionChar(set.type, tempLeftE.distribute(), tempRightE.distribute());
      }
      else return new MultExpression(literal, set.distribute()); //if set has higher precedence we dont distribute
   }

   public String toString() {
      return leftE.toString() + "*" + rightE.toString();
   }
}