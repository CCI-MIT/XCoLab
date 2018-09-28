package org.xcolab.service.utils.db.jooq.generator;

import org.jooq.util.Definition;

import org.xcolab.commons.jooq.JooqGeneratorStrategy;

import java.util.List;

public class XColabGeneratorStrategy extends JooqGeneratorStrategy {

    private static final String PREFIX_SEPARATOR = "__";

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
        if (prefixIndex != -1 ) {
            return val.substring(prefixIndex + PREFIX_SEPARATOR.length());
        }
        return val;
    }

    @Override
    public List<String> getJavaClassImplements(Definition definition, Mode mode) {
        List<String> list = super.getJavaClassImplements(definition, mode);

        if(mode == Mode.POJO) {
            String clientName = definition.getOutputName().substring(0, definition.getOutputName().indexOf(PREFIX_SEPARATOR));
            list.add("org.xcolab.client." + clientName + ".pojo." + getJavaClassName(definition, Mode.INTERFACE));
        }

        return list;
    }
}
