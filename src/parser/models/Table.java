package parser.models;

import java.util.ArrayList;

public class Table {

    private String name;
    private String entityName;
    private Field PK;
    private ArrayList<Field> fields;

    public Table() {
        name = "";
        entityName = "";
        PK = null;
        fields = new ArrayList<>();
    }

    public Table(String name, String entityName, Field PK, ArrayList<Field> fields) {
        this.name = name;
        this.entityName = entityName;
        this.PK = PK;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field getPK() {
        return PK;
    }

    public void setPK(Field PK) {
        this.PK = PK;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", entityName='" + entityName + '\'' +
                ", PK=" + PK +
                ", fields=" + fields +
                '}';
    }

}
