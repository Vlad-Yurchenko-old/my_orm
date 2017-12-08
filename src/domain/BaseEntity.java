package domain;

import java.lang.reflect.Field;

public class BaseEntity<T extends Object>{

    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public String toString() {
        Class entity = this.getClass();
        String str = entity.getName() + "{id = " + id + ", ";
        Field[] fields = entity.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                str += fields[i].getName() + " = " + fields[i].get(this);
                if (i != fields.length - 1) str += ", ";
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        str += "}";
        return str;
    }
}
