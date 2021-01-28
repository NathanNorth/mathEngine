package expressions;

import java.util.Objects;

public class PowExpression extends Expression {
    private Expression base;
    private Expression power;

    public PowExpression(Expression base, Expression power) {
        super('^');

        this.base = base;
        this.power = power;
    }

    public Expression distribute() {

        base = base.distribute();
        power = power.distribute();

        //short circut check if power is int. We call distribute before casting incase the object is in redundant parenthesis '(a+b)^(c)'
        boolean isInt = power.type == 'L' &&
                ((ExpressionLiteral)power).isConstant &&
                ((ExpressionLiteral)power).isInt;

        //is base is powExpression we can multiply (doesnt work if pow is pow)
        if(base.type == '^') {
            Expression newPow = new MultExpression(((PowExpression)base).getPower(), power);
            return new PowExpression(((PowExpression)base).getBase(), newPow).distribute();
        }

        //if base is literal or pow
        if(precedenceI(base.distribute().type) > precedenceI('/')) {
            return new PowExpression(base, power);
        }

        //if base is * or / (which would only happen inside parenthesis) we will distribute the power
        if(base.type == '*' || base.type == '/') {
            Expression tempL = new PowExpression(((TwoSidedExpression)base).getLeftE(), power);
            Expression tempR = new PowExpression(((TwoSidedExpression)base).getRightE(), power);

            return getExpressionChar(base.type, tempL, tempR).distribute(); //distribute so that division won't look bad
        }

        /* This section of code will run if we have a power/base combo that cant be distributed to. We know this base
        (which must be a + or - at this point in the code) can't be distributed to by a nonInt and so we just return a
        pow statement when we don't have an int*/
        if(!isInt) {
            return new PowExpression(base, power);
        } //because we have an int here we CAN distribute by returning a bunch of foil-able multiplcation statements.
        else if(base.type == '-' || base.type == '+') { //we have int pow and base to distribute
            double powerVal = ((ExpressionLiteral)power).getDouble(); //we distribute here in case power is in redundant parenthesis

            if(powerVal < 0) { //subzero powers return a division sign with a positive power value below todo: maybe we want this to just distribute with - exponents?
                Expression newPow = new ExpressionLiteral(-1 * powerVal + "");
                return new DivExpression(new ExpressionLiteral("1"), new PowExpression(base, newPow)).distribute();
            }
            if(powerVal == 1) return base; //set to the 1st power is that set '(a+b)^1 == a+b'
            if(powerVal > 1) {
                Expression newPow = new ExpressionLiteral((int)(powerVal - 1) + "");
                return new MultExpression(base, new PowExpression(base, newPow)).distribute();
            }
        }
        //should never run
        return null;
    }

    public Expression getBase() {
        return base;
    }

    public Expression getPower() {
        return power;
    }

    public String toString() {
        //string representations of values
        String baseS;
        String powerS;

        //these find out if we want brackets for increased clarity. Brackets are redundant if base/pow is a parenth already
        if(base.type == 'L')  baseS =  base.toString();
        else baseS = "[" + base.toString() + "]";
        if(power.type == 'L')  powerS =  power.toString();
        else powerS = "[" + power.toString() + "]";

        return baseS + "^" + powerS;
    }
}