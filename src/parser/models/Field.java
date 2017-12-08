package parser.models;

public class Field {

    private String name;
    private String entityField;
    private String type;

    public Field() {
    }

    public Field(String name, String entityField, String type) {
        this.name = name;
        this.entityField = entityField;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
                ", type='" + type + '\'' +
                '}';
    }

}
