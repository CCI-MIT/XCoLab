package org.xcolab.jooq;

import org.jooq.tools.StringUtils;
import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class XColabGeneratorStrategy extends DefaultGeneratorStrategy {


    private Properties properties;

    private String schema;

    private static final String TABLE_PREFIX = "xcolab_";

    public XColabGeneratorStrategy(){

        InputStream input = null;
        try {
            input = new FileInputStream("aplication.properties");
            properties.load(input);
            schema = properties.getProperty("db.schema");
        } catch (IOException ignored) {
            schema = null;
        }
    }

    @Override
    public String getJavaIdentifier(Definition definition) {
        // regex to split camel case taken (and adapted) from
        // https://stackoverflow.com/questions/7593969/regex-to-split-camelcase-or-titlecase-advanced/7599674#7599674

        final String unprefixedTableName = cleanUpSchemeAndPrefix(definition.getOutputName());
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
        if (definition.getOutputName().startsWith(TABLE_PREFIX)) {
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
    private String cleanUpSchemeAndPrefix(String val){
        if (schema !=null && TABLE_PREFIX.contains(schema)){
            return val.replace(schema, "").replace(TABLE_PREFIX, "");
        }else {
            return val.replace(TABLE_PREFIX, "");
        }
    }

    private static String toUpperCase(String string) {
        return string != null && !string.isEmpty()?Character.toUpperCase(string.charAt(0)) + string.substring(1):string;
    }
}