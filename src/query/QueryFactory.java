package query;

import query.mysql.CreateTableQueryBuilder;
import query.mysql.DropTableQueryBuilder;

import java.util.HashMap;
import java.util.Map;

public class QueryFactory {

    private static Map<Class, QueryBuilder> queryBuilders = new HashMap<>();

    static {
        queryBuilders.put(CreateTableQueryBuilder.class, new CreateTableQueryBuilder());
        queryBuilders.put(DropTableQueryBuilder.class, new DropTableQueryBuilder());
    }

    public static <T extends QueryBuilder> T get(Class<T> type) {
        return (T) queryBuilders.get(type);
    }

}
