package parser.models;

public class ForeignKey {

    private Field field;
    private String fkEntityName;

    public ForeignKey(){
    }

    public ForeignKey(Field field, String fkEntityName) {
        this.field = field;
        this.fkEntityName = fkEntityName;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getFkEntityName() {
        return fkEntityName;
    }

    public void setFkEntityName(String fkEntityName) {
        this.fkEntityName = fkEntityName;
    }

    @Override
    public String toString() {
        return "ForeignKey{" +
                "field=" + field +
                ", fkEntityName='" + fkEntityName + '\'' +
                '}';
    }

}
