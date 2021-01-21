package expressions;

public class SubtractExpression extends TwoSidedExpression {
    private Expression leftE;
    private Expression rightE;

    public SubtractExpression(Expression leftE, Expression rightE) {
        super('-');

        this.rightE = rightE;
        this.leftE = leftE;
    }

    @Override
    public Expression getLeftE() {
        return leftE;
    }

    @Override
    public Expression getRightE() {
        return rightE;
    }


    public Expression distribute() {

        /* The way subtraction expressions are parsed means that we NEVER subtract anything except a literal (or an
        expression of higher precedence than subtraction). Any nested subtraction will always be in the left term. That
        means the ONLY time subtraction doesn't distribute identically to addition is when we have to subtract something
        in parenthesis. When that happens we have to distribute the negative hence this hard to follow bit of code. */
        if(rightE.distribute().type == '-' || rightE.distribute().type == '+') { //since types show through parenthesis we can check if we need to distribute just by checking the type
            //we get flipped sign
            char flipped;
            if(rightE.distribute().type == '-') flipped = '+';
            else flipped = '-';

            //we always subtract the first term in parenthesis (since it is inherently positive)
            Expression leftOfRight = ((TwoSidedExpression)rightE.distribute()).getLeftE();
            Expression rightOfRight = ((TwoSidedExpression)rightE.distribute()).getRightE();

            Expression temp = new SubtractExpression(leftE.distribute(), leftOfRight);

            //depending on what the rightE equation is we flip the sign eg "1 - ( 1 + 2 ) becomes "1 - 1 -! 2"
            return getExpressionChar(flipped, temp.distribute(), rightOfRight.distribute()).distribute();
        }

        return new SubtractExpression(leftE.distribute(), rightE.distribute());
    }
    public String toString() { return leftE.toString() + "-" + rightE.toString(); }
}
