package domain;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.StringJoiner;

public class BaseEntity<T extends Object> {

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
        StringJoiner stringJoiner = new StringJoiner(", ", entity.getSimpleName() + "(", ")");
        Arrays.stream(entity.getDeclaredFields()).forEach(field -> {
            try {
                field.setAccessible(true);
                stringJoiner.add(field.get(this).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return stringJoiner.toString();
    }

}
