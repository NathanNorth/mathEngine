package expressions;

public class SubtractExpression extends TwoSidedExpression {
    private Expression leftE;
    private Expression rightE;

    public SubtractExpression(Expression leftE, Expression rightE) {
        super('-', leftE, rightE);
    }

    public Expression distribute() {
        rightE = rightE.distribute();
        leftE = leftE.distribute();

        /* The way subtraction expressions are parsed means that we NEVER subtract anything except a literal (or an
        expression of higher precedence than subtraction). Any nested subtraction will always be in the left term. That
        means the ONLY time subtraction doesn't distribute identically to addition is when we have to subtract something
        in parenthesis. When that happens we have to distribute the negative hence this hard to follow bit of code. */
        if(rightE.type == '-' || rightE.type == '+') { //since types show through parenthesis we can check if we need to distribute just by checking the type
            //we get flipped sign
            char flipped;
            if(rightE.type == '-') flipped = '+';
            else flipped = '-';

            //we always subtract the first term in parenthesis (since it is inherently positive)
            Expression leftOfRight = ((TwoSidedExpression)rightE).getLeftE();
            Expression rightOfRight = ((TwoSidedExpression)rightE).getRightE();

            Expression temp = new SubtractExpression(leftE, leftOfRight);

            //depending on what the rightE equation is we flip the sign eg "1 - ( 1 + 2 ) becomes "1 - 1 -! 2"
            return getExpressionChar(flipped, temp.distribute(), rightOfRight).distribute();
        }

        return new SubtractExpression(leftE, rightE);
    }

    public String toString() {
        String leftS;
        String rightS;

        //parenthesis never needed  on the left
        leftS = leftE.toString();

        if (precedenceI(rightE.type) > precedenceI('-')) rightS = rightE.toString();
        else rightS = "[" + rightE.toString() + "]";

        return leftS + "-" + rightS;
    }
}
