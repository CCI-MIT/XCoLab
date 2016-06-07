package org.xcolab.service.utils.db.jooq;

import org.jooq.Field;

import org.xcolab.service.utils.db.jooq.fulltext.FulltextMatchClause;

public final class CustomDsl {

    private CustomDsl() {
    }

    public static FulltextMatchClause match(Field<?>... fields) {
        return new FulltextMatchClause(fields);
    }
}
