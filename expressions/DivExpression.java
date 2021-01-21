package expressions;

public class DivExpression extends TwoSidedExpression {
    private Expression leftE;
    private Expression rightE;

    public DivExpression(Expression leftE, Expression rightE) {
        super('/');

        this.rightE = rightE;
        this.leftE = leftE;
    }

    public Expression distribute() {

        //finds out right and left are plural by checking if they are effective plural. We distribute here in case the distributed types aren't what they seem.
        boolean rightP = precedenceI(rightE.distribute().type) < precedenceI('*'); //right plural
        boolean leftP = precedenceI(leftE.distribute().type) < precedenceI('*'); //left plural

        //if we have literal literals "4/5" or effective lit and lit "a*b/e"
        if((!leftP && rightE.type == 'L') || (leftE.distribute().type == '^' && rightE.distribute().type == '^')) {
            return new DivExpression(leftE.distribute(), rightE.distribute()); //distribute for parenthesis
        }

        /*Runs when we have two effective literals "a*b/c*d" or two effective plurals (we cant distribute) "a+b/c+d".
        No distribution here.*/
        if((!leftP && !rightP) || (leftP && rightP)) {
            return new DivExpression(leftE.distribute(), rightE.distribute());
        }

        //left literal right effective plural
        if(!leftP && rightP) {
            return divDistribute1(leftE.distribute(), rightE.distribute());
        }

        //left plural right literal
        if(leftP && !rightP) {
            return divDistribute2(leftE.distribute(), rightE.distribute());
        }

        //should never run
        return null;
    }

    //distributes if literal / plural
    private Expression divDistribute1(Expression literal, Expression set) {
        if (precedenceI('*') > precedenceI(set.type)) { //if we should distribute
            Expression tempLeftE = new DivExpression(literal, ((TwoSidedExpression)set).getLeftE()).distribute();
            Expression tempRightE = new DivExpression(literal, ((TwoSidedExpression)set).getRightE()).distribute(); //TODO: DOES THIS NEED TO REDISTRIBUTE

            //we MUST call distributes on our left and right sides, this is the key to nested distribution
            return getExpressionChar(set.type, tempLeftE.distribute(), tempRightE.distribute());
        }
        else { //TODO: Should be irrelevant as presently written since we only call this method with an effective plural
            //if denominator is plural put it in parenthesis for clarity
            if(set.type != 'L') return new DivExpression(literal, new ParenthExpression(set.distribute()));
            //if denom is literal we good
            else return new DivExpression(literal, set.distribute()); //if set has higher precedence we dont distribute
        }
    }

    //divides a plural by a literal
    private Expression divDistribute2(Expression set, Expression literal) {
        if (precedenceI('*') > precedenceI(set.type)) { //if we should distribute
            Expression tempLeftE = new DivExpression(((TwoSidedExpression)set).getLeftE(), literal).distribute();
            Expression tempRightE = new DivExpression(((TwoSidedExpression)set).getRightE(), literal).distribute();

            //we MUST call distributes on our left and right sides, this is the key to nested distribution
            return getExpressionChar(set.type, tempLeftE.distribute(), tempRightE.distribute());
        }
        else {
            //if denominator is plural put it in parenthesis for clarity
            if(literal.type != 'L') return new DivExpression(literal, new ParenthExpression(set.distribute()));
            //if denom is literal we good
            else return new DivExpression(literal, set.distribute()); //if set has higher precedence we dont distribute
        }
    }

    @Override
    public Expression getLeftE() { return leftE; }

    @Override
    public Expression getRightE() { return rightE; }

    public String toString() {
        return "[" + leftE.toString() + "]/[" + rightE.toString() +"]";
    }
}
