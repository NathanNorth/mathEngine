package expressions;

public class SubtractExpression extends Expression {
    private Expression leftE;
    private Expression rightE;

    public SubtractExpression(Expression leftE, Expression rightE) {
        this.rightE = rightE;
        this.leftE = leftE;
    }

    public String toString() { return leftE.toString() + "-" + rightE.toString(); }
}
