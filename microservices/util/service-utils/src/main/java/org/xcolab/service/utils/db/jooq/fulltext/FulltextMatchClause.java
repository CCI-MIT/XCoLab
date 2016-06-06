package org.xcolab.service.utils.db.jooq.fulltext;

import org.jooq.Field;

public class FulltextMatchClause {

    private final Field<?>[] fields;

    public FulltextMatchClause(Field<?>... fields) {
        this.fields = fields;
    }

    public FulltextCondition against(String condition) {
        return against(condition, FulltextSearchModifier.NONE);
    }

    public FulltextCondition against(String condition, FulltextSearchModifier searchModifier) {
        return new FulltextCondition(condition, searchModifier, fields);
    }
}
