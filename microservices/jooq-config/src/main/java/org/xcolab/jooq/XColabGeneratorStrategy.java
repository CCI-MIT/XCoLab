package org.xcolab.jooq;

import org.jooq.tools.StringUtils;
import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

public class XColabGeneratorStrategy extends DefaultGeneratorStrategy {

    @Override
    public String getJavaIdentifier(Definition definition) {
        // regex to split camel case taken (and adapted) from
        // https://stackoverflow.com/questions/7593969/regex-to-split-camelcase-or-titlecase-advanced/7599674#7599674

        final String unprefixedTableName = definition.getOutputName().replace("xcolab_", "");
        final String underscoreSeparatedName = StringUtils.join(
                unprefixedTableName.split("(?<=[a-z0-9])(?=[A-Z0-9])|(?<=[A-Z0-9])(?=[A-Z0-9][a-z0-9])"), "_");
        return underscoreSeparatedName.toUpperCase();
    }

    @Override
    public String getJavaSetterName(Definition definition, Mode mode) {
        return "set" + toUpperCase(definition.getOutputName());
    }

    @Override
    public String getJavaGetterName(Definition definition, Mode mode) {
        return "get" + toUpperCase(definition.getOutputName());
    }

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        StringBuilder result = new StringBuilder();
        if (definition.getOutputName().startsWith("xcolab_")) {
            result.append(definition.getOutputName().replace("xcolab_", ""));
            if (mode == Mode.DEFAULT) {
                result.append("Table");
            }
        } else {
            result.append(StringUtils.toCamelCase(definition.getOutputName()));
        }
        if (mode == Mode.RECORD) {
            result.append("Record");
        } else if (mode == Mode.DAO) {
            result.append("Dao");
        } else if (mode == Mode.INTERFACE) {
            result.insert(0, "I");
        }

        return result.toString();
    }

    private static String toUpperCase(String string) {
        return string != null && !string.isEmpty()?Character.toUpperCase(string.charAt(0)) + string.substring(1):string;
    }
}