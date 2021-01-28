package expressions;

public class DivExpression extends TwoSidedExpression {

    public DivExpression(Expression leftE, Expression rightE) {
        super('/', leftE, rightE);
    }

    /* Earlier version of this method were a good example of over.distribution. Because we abstract out the recursive
    distribute calls to the outer layer if statements, the distribute submethods should not need any .distributes inside
    themselves */
    public Expression distribute() {
        leftE = leftE.distribute();
        rightE = rightE.distribute();

        //finds out right and left are plural by checking if they are effective plural. We distribute here in case the distributed types aren't what they seem.
        boolean rightP = precedenceI(rightE.type) < precedenceI('*'); //right plural
        boolean leftP = precedenceI(leftE.type) < precedenceI('*'); //left plural

        //if we have literal literals "4/5" or effective lit and lit "a*b/e"
        if((!leftP && rightE.type == 'L') || (leftE.type == '^' && rightE.type == '^')) {
            return new DivExpression(leftE, rightE); //distribute for parenthesis
        }

        /*Runs when we have two effective literals "a*b/c*d" or two effective plurals (we cant distribute) "a+b/c+d".
        No distribution here.*/
        if((!leftP && !rightP) || (leftP && rightP)) {
            return new DivExpression(leftE, rightE);
        }

        //left literal right effective plural
        if(!leftP && rightP) {
            return divDistribute1(leftE, rightE);
        }

        //left plural right literal
        if(leftP && !rightP) {
            return divDistribute2(leftE, rightE);
        }

        //should never run
        return null;
    }

    //distributes if literal / plural
    private Expression divDistribute1(Expression literal, Expression set) {
        Expression tempLeftE = new DivExpression(literal, ((TwoSidedExpression)set).getLeftE());
        Expression tempRightE = new DivExpression(literal, ((TwoSidedExpression)set).getRightE());

        //we MUST call distribute on this (which is an effective distribute on the left and right side) because of nested same operator expressions 'a/(b+c+d)'
        return getExpressionChar(set.type, tempLeftE, tempRightE).distribute();
    }

    //divides a plural by a literal
    private Expression divDistribute2(Expression set, Expression literal) {
        Expression tempLeftE = new DivExpression(((TwoSidedExpression)set).getLeftE(), literal);
        Expression tempRightE = new DivExpression(((TwoSidedExpression)set).getRightE(), literal);

        //we MUST call distribute on this (which is an effective distribute on the left and right side) because of nested same operator expressions
        return getExpressionChar(set.type, tempLeftE, tempRightE).distribute();
    }

    //see pow version of this method for comments
    public String toString() {
        String leftS;
        String rightS;

        if(leftE.type == 'L')  leftS =  leftE.toString();
        else leftS = "[" + leftE.toString() + "]";
        if(rightE.type == 'L')  rightS =  rightE.toString();
        else rightS = "[" + rightE.toString() + "]";

        return leftS + "/" + rightS;
    }
}
