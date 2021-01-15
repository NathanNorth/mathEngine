package expressions;

public class DivExpression extends Expression {
    private Expression leftE;
    private Expression rightE;

    public DivExpression(Expression leftE, Expression rightE) {
        this.rightE = rightE;
        this.leftE = leftE;
    }

    public String toString() {
        return leftE.toString() + "/" + rightE.toString();
    }
}
