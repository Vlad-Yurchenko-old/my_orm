package query.mysql;

import query.QueryBuilder;
import org.stringtemplate.v4.ST;

public class DropTableQueryBuilder implements QueryBuilder<String> {

    private static final String dropTableTemplate =
            "drop table <tableName>";


    @Override
    public String build(String tableName) {
        ST generator = new ST(dropTableTemplate);
        generator.add("tableName", tableName);
        return generator.render();
    }

}
