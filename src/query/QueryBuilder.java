package query;

public interface QueryBuilder<T> {

    default String build(T generate) {
        return "";
    }

    default String build() {
        return "";
    }

}
