package org.xcolab.commons.jooq;

import org.jooq.tools.StringUtils;
import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;
import org.jooq.util.GeneratorStrategy;

/**
 * This {@link GeneratorStrategy} appends a suffix to table classes to distinguish them from POJOs.
 *
 * It is built to be more customizable in the way the name is generated.
 */
public class JooqGeneratorStrategy extends DefaultGeneratorStrategy {

    /**
     * This method allows customizing the class name before pre- or postfixes are added.
     *
     * @param originalName The name of the table.
     * @return The name to be used as identifier.
     */
    protected String customizeJavaClassName(String originalName) {
        return StringUtils.toCamelCase(originalName
                .replace(' ', '_')
                .replace('-', '_')
                .replace('.', '_'));
    }

    /**
     * This method allows customizing the pre- or postfixes for different modes.
     *
     * @param identifierName The generated identifier name.
     * @param mode The mode.
     * @return The final identifier name.
     */
    protected String customizeJavaClassNameForMode(String identifierName, Mode mode) {
        StringBuilder result = new StringBuilder(identifierName);
        if (mode == Mode.DEFAULT) {
            result.append("Table");
        } else if (mode == Mode.RECORD) {
            result.append("Record");
        } else if (mode == Mode.DAO) {
            result.append("Dao");
        } else if (mode == Mode.INTERFACE) {
            result.insert(0, "I");
        }
        return result.toString();
    }

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        String result = customizeJavaClassName(definition.getOutputName());
        return customizeJavaClassNameForMode(result, mode);
    }

    protected String customizeJavaIdentifier(String originalName) {
        return originalName.toUpperCase();
    }

    @Override
    public String getJavaIdentifier(Definition definition) {
        return customizeJavaIdentifier(definition.getOutputName());
    }
}
