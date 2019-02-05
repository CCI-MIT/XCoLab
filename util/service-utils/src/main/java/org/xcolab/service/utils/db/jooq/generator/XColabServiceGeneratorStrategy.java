package org.xcolab.service.utils.db.jooq.generator;

import org.jooq.util.Definition;
import org.jooq.util.TypedElementDefinition;

import java.util.Arrays;
import java.util.List;

public class XColabServiceGeneratorStrategy extends XColabGeneratorStrategy {

    private static final String INTERFACE_PATH_FORMAT = "org.xcolab.client.%s.pojo.%s";
    //TODO: COLAB-2918: remove when new client/server architecture is used for all services
    private static final List<String> clients = Arrays.asList("tracking", "content", "modeling", "comment", "admin", "contest", "activity","user");

    protected static final String SPECIAL_CASES_PREFIX_SEPARATOR = "_";

    @Override
    public List<String> getJavaClassImplements(Definition definition, Mode mode) {
        List<String> list = super.getJavaClassImplements(definition, mode);

        if (mode == Mode.POJO) {
            String clientName = extractClientName(definition);

            if (clients.stream().anyMatch(client -> clientName.contains(client))) {
                list.add(String.format(INTERFACE_PATH_FORMAT, clientName,
                        getJavaClassName(definition, Mode.INTERFACE)));
            }
        }

        return list;
    }

    private String extractClientName(Definition definition) {

        if(definition.getOutputName().indexOf(PREFIX_SEPARATOR) != -1) {
            return definition.getOutputName()
                    .substring(0, definition.getOutputName().indexOf(PREFIX_SEPARATOR));
        } else {
            return definition.getOutputName()
                    .substring(0, definition.getOutputName().indexOf(SPECIAL_CASES_PREFIX_SEPARATOR));
        }
    }

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        String className = super.getJavaClassName(definition, mode);

        if (mode == Mode.POJO) {


            String clientName = extractClientName(definition);

            if (clients.stream().anyMatch(client -> clientName.contains(client))) {
                className += "Impl";
            }
        }
        return className;
    }


    @Override
    public String getJavaGetterName(Definition definition, Mode mode) {
        String getterName = super.getJavaGetterName(definition, mode);
        if (definition instanceof TypedElementDefinition && mode == Mode.POJO) {
            TypedElementDefinition def = (TypedElementDefinition) definition;
            if ("BOOLEAN".equals(def.getType().getType())) {
                getterName = getterName.replaceFirst("get", "is");
            }
        }
        return getterName;
    }
}
