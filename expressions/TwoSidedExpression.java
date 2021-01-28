package expressions;

//The backbone of all binary expressions
public abstract class TwoSidedExpression extends Expression {

    protected Expression leftE;
    protected Expression rightE;

    TwoSidedExpression(char type, Expression leftE, Expression rightE) {
        super(type);
        this.leftE = leftE;
        this.rightE = rightE;
    }

    public Expression getLeftE() {
        return leftE;
    }

    public Expression getRightE() {
        return rightE;
    }

    public Expression getBase() {
        return leftE;
    }

    public Expression getPower() {
        return rightE;
    }
}
