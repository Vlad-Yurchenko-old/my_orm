package parser.models;

import java.util.ArrayList;

/*
* Пока не используется
* Класс для представления первичного ключа (составного)
* */
public class PrimaryKey {

    private ArrayList<Field> keys;

    public PrimaryKey() {
        keys = new ArrayList<>();
    }

    public PrimaryKey(ArrayList<Field> keys) {
        this.keys = keys;
    }

    public Field getKey(int i) {
        return keys.get(i);
    }

    public Field getKeyByName(String name) {
        for (Field field : keys) {
            if (field.getName().compareTo(name) == 0) {
                return field;
            }
        }
        return null;
    }

    public void addKey(Field field) {
        keys.add(field);
    }

    public ArrayList<Field> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<Field> keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "PrimaryKey{" +
                "keys=" + keys +
                '}';
    }
}
