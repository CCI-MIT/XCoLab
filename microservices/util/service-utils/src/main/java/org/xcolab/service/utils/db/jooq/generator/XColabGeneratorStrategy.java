package org.xcolab.service.utils.db.jooq.generator;

import org.jooq.tools.StringUtils;
import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

public class XColabGeneratorStrategy extends DefaultGeneratorStrategy {

    private static final String[] TABLE_PREFIXES = {"xcolab_", "members_", "contest_",
            "content_", "proposal_", "comment_", "files_", "balloon_", "admin_", "activities_",
            "flagging_"};

    private static String toUpperCase(String string) {
        if (string != null && !string.isEmpty()) {
            return Character.toUpperCase(string.charAt(0))
                    + string.substring(1);
        } else {
            return string;
        }
    }

    @Override
    public String getJavaIdentifier(Definition definition) {
        // regex to split camel case taken (and adapted) from
        // https://stackoverflow.com/questions/7593969/regex-to-split-camelcase-or-titlecase-advanced/7599674#7599674

        final String unprefixedTableName = cleanUpSchemeAndPrefix(definition.getOutputName());
        final String underscoreSeparatedName = StringUtils.join(
                unprefixedTableName
                        .split("(?<=[a-z0-9])(?=[A-Z0-9])|(?<=[A-Z0-9])(?=[A-Z0-9][a-z0-9])"), "_");
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
        if (containsPrefix(definition.getOutputName())) {
            result.append(cleanUpSchemeAndPrefix(definition.getOutputName()));
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

    private boolean containsPrefix(String string) {
        for (String prefix : TABLE_PREFIXES) {
            if (string.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    private String cleanUpSchemeAndPrefix(String val) {
        String ret = val;
        for (String prefix : TABLE_PREFIXES) {
            ret = ret.replace(prefix, "");
        }
        return ret;
    }
}