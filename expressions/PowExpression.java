package expressions;

public class PowExpression extends Expression {
    private Expression base;
    private Expression power;

    public PowExpression(Expression base, Expression power) {
        this.base = base;
        this.power = power;
    }

    public String toString() { return base.toString() + "^" + power.toString();}
}
