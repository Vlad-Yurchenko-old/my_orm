package dao.condition.type;

import dao.condition.Condition;

public class ConditionCompare extends Condition {

    private Object A;
    private Object B;
    private String compareOperation;

    private ConditionCompare(){
    }

    public ConditionCompare(Object a, Object b, String compareOperation) {
        A = a;
        B = b;
        this.compareOperation = compareOperation;
    }

    public Object getA() {
        return A;
    }

    public void setA(Object a) {
        A = a;
    }

    public Object getB() {
        return B;
    }

    public void setB(Object b) {
        B = b;
    }

    public String getCompareOperation() {
        return compareOperation;
    }

    public void setCompareOperation(String compareOperation) {
        this.compareOperation = compareOperation;
    }

    @Override
    public String toString() {
        return A + " " + compareOperation + " " + B;
    }
}
