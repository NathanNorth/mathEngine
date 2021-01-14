package expressions;

public class SubtractExpression extends Expression {
    private Expression leftE;
    private Expression rightE;

    public SubtractExpression(Expression rightE, Expression leftE) {
        this.rightE = rightE;
        this.leftE = leftE;
    }

    public String toString() {
        return rightE.toString() + "-" + leftE.toString();
    }
}
