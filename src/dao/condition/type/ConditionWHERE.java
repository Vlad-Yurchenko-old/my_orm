package dao.condition.type;

import dao.condition.Condition;

public class ConditionWHERE extends Condition{

    private Condition condition;

    public ConditionWHERE(){
    }

    public ConditionWHERE(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "WHERE " + condition;
    }
}
