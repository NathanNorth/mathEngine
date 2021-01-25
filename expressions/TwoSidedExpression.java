package expressions;

/*This class exists for the sole purpose of not having to but getleftE and getrightE in the expression class since that
is kind of irrelevant. Maybe at a future date I could make the code prettier by having the all the different double side
expressions use more inheritance features, but for now im lazy*/
public class TwoSidedExpression extends Expression {

    public TwoSidedExpression() {}

    //constructor chaining
    public TwoSidedExpression(char type) {
        super(type);
    }

    public Expression getLeftE() {return null;}
    public Expression getRightE() {return null;}
}
