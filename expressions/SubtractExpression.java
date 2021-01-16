package expressions;

public class SubtractExpression extends dExpression {
    private Expression leftE;
    private Expression rightE;

    public SubtractExpression(Expression leftE, Expression rightE) {
        super.type = '-';

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

//        //distributes the negative if the right hand side is plural
//        if(rightE.type != 'L') {
//            //Expression right = rightE.distribute(); //this is for parenthesis, probably breaks stuff if this distributes a plural
//            Expression tempLeftE = new SubtractExpression(leftE, ((dExpression)rightE).getLeftE());
//            return new SubtractExpression(tempLeftE, ((dExpression)rightE).getRightE()).distribute();
//        }
//
//        //TODO: ALL OF THIS IS GARBAGE AND HARD TO FOLLOW. LITERALLY DOES WORK PEPEGA.

        return new SubtractExpression(leftE.distribute(), rightE.distribute());
    }
    public String toString() { return leftE.toString() + "-" + rightE.toString(); }
}
