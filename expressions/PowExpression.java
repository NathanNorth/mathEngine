package expressions;

public class PowExpression extends Expression {
    private Expression base;
    private Expression power;

    public PowExpression(Expression base, Expression power) {
        super('^');

        this.base = base;
        this.power = power;
    }

    public Expression distribute() {

        if(base.distribute().type == 'L' || base.distribute().type == '^') { //TODO: because we check base.distribute we shouldn't need to .distribute the next line. may make other code more efficient
            return new PowExpression(base.distribute(), power.distribute());
        }

        //if base is * or / (which would only happen inside parenthesis) we will distribute the power
        if(base.distribute().type == '*' || base.distribute().type == '/') {
            Expression tempL = new PowExpression(((TwoSidedExpression)base.distribute()).getLeftE(), power);
            Expression tempR = new PowExpression(((TwoSidedExpression)base.distribute()).getRightE(), power);

            return getExpressionChar(base.distribute().type, tempL, tempR).distribute(); //distribute so that division won't look bad
        }
        //figure out if pow is literal

        if(base.distribute().type == '-' || base.distribute().type == '+') { //and pow is literal
            return null;
        }
        return null;
    }

    public Expression getBase() {
        return base;
    }

    public Expression getPower() {
        return power;
    }

    public String toString() { return base.toString() + "^" + power.toString();}
}
