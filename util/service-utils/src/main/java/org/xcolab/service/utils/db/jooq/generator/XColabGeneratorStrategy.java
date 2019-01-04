package org.xcolab.service.utils.db.jooq.generator;

import org.xcolab.commons.jooq.JooqGeneratorStrategy;

public class XColabGeneratorStrategy extends JooqGeneratorStrategy {

    protected static final String PREFIX_SEPARATOR = "__";

    @Override
    protected String customizeJavaClassName(String originalName) {
        String unprefixedName = cleanUpSchemeAndPrefix(originalName);
        return super.customizeJavaClassName(unprefixedName);
    }

    @Override
    protected String customizeJavaIdentifier(String originalName) {
        String unprefixedName = cleanUpSchemeAndPrefix(originalName);
        return super.customizeJavaIdentifier(unprefixedName);
    }

    private String cleanUpSchemeAndPrefix(String val) {
        final int prefixIndex = val.indexOf(PREFIX_SEPARATOR);
        if (prefixIndex != -1) {
            return val.substring(prefixIndex + PREFIX_SEPARATOR.length());
        }
        return val;
    }
}
