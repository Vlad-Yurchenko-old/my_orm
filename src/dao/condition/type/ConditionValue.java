package dao.condition.type;

import dao.condition.Condition;

public class ConditionValue extends Condition{

    private Object value;

    private ConditionValue(){
    }

    public ConditionValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
