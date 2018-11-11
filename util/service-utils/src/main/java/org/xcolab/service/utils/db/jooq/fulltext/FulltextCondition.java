package org.xcolab.service.utils.db.jooq.fulltext;

import org.jooq.Configuration;
import org.jooq.Context;
import org.jooq.Field;
import org.jooq.QueryPart;
import org.jooq.impl.CustomCondition;
import org.jooq.impl.DSL;

public class FulltextCondition extends CustomCondition {

    private final String query;
    private final FulltextSearchModifier searchModifier;
    private final Field<?>[] fields;

    public FulltextCondition(String query, Field<?>... fields) {
        this(query, FulltextSearchModifier.NONE, fields);
    }

    public FulltextCondition(String query, FulltextSearchModifier searchModifier, Field<?>... fields) {
        super();
        this.query = query;
        this.searchModifier = searchModifier;
        this.fields = fields;
    }

    public Field<Double> as(String alias) {
        return new FulltextField(alias, this).as(alias);
    }

    @Override
    public void accept(Context<?> context) {
        context.visit(delegate(context.configuration()));
    }

    private QueryPart delegate(Configuration configuration) {
        switch (configuration.dialect().family()) {
            case MYSQL:
            case MARIADB:
                StringBuilder sql = new StringBuilder("MATCH (");
                boolean isFirst = true;
                for (Field<?> field : fields) {
                    if (isFirst) {
                        sql.append(field.getName());
                        isFirst = false;
                    } else {
                        sql.append(", ").append(field.getName());
                    }
                }
                sql.append(") AGAINST (?");
                if (searchModifier != FulltextSearchModifier.NONE) {
                    sql.append(" ").append(searchModifier.name());
                }
                sql.append(")");
                return DSL.condition(sql.toString(), query);
            default:
                throw new UnsupportedOperationException("Dialect not supported: "
                        + configuration.dialect());
        }
    }
}
