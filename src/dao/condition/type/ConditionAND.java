package dao.condition.type;

import dao.condition.Condition;

public class ConditionAND extends Condition {

    private Condition A;
    private Condition B;

    private ConditionAND() {
    }

    public ConditionAND(Condition a, Condition b) {
        A = a;
        B = b;
    }

    public Condition getA() {
        return A;
    }

    public void setA(Condition a) {
        A = a;
    }

    public Condition getB() {
        return B;
    }

    public void setB(Condition b) {
        B = b;
    }

    @Override
    public String toString() {
        return A + " AND " + B;
    }
}
