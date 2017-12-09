package parser.models;

public class Field {

    private String name = "";
    private String entityField = "";

    public Field() {
    }

    public Field(String name, String entityField) {
        this.name = name;
        this.entityField = entityField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityField() {
        return entityField;
    }

    public void setEntityField(String entityField) {
        this.entityField = entityField;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", entityField='" + entityField + '\'' +
                '}';
    }

}
