package org.xcolab.service.utils.db.jooq.fulltext;

public enum FulltextSearchModifier {
    NONE, IN_BOOLEAN_MODE, IN_NATURAL_LANGUAGE_MODE,
    IN_NATURAL_LANGUAGE_MODE_WITH_QUERY_EXPANSION, WITH_QUERY_EXPANSION;
}
