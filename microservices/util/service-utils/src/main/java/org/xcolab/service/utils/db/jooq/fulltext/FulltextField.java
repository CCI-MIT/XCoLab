package org.xcolab.service.utils.db.jooq.fulltext;

import org.jooq.Context;
import org.jooq.impl.CustomField;
import org.jooq.impl.SQLDataType;

public class FulltextField extends CustomField<Double> {

    private final FulltextCondition fulltextCondition;

    public FulltextField(String name, FulltextCondition fulltextCondition) {
        super(name, SQLDataType.DOUBLE);
        this.fulltextCondition = fulltextCondition;
    }

    @Override
    public void accept(Context<?> context) {
        fulltextCondition.accept(context);
    }
}
