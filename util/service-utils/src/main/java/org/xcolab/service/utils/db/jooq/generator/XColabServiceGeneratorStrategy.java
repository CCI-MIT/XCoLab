package org.xcolab.service.utils.db.jooq.generator;

import org.jooq.util.Definition;

import java.util.Arrays;
import java.util.List;

public class XColabServiceGeneratorStrategy extends XColabGeneratorStrategy {

    private static final String INTERFACE_PATH_FORMAT = "org.xcolab.client.%s.pojo.%s";
    //TODO: COLAB-2918: remove when new client/server architecture is used for all services
    private static final List<String> clients = Arrays.asList("tracking", "content", "moderation");

    @Override
    public List<String> getJavaClassImplements(Definition definition, Mode mode) {
        List<String> list = super.getJavaClassImplements(definition, mode);

        if (mode == Mode.POJO) {
            String clientName = definition.getOutputName()
                    .substring(0, definition.getOutputName().indexOf(PREFIX_SEPARATOR));
            if (clients.stream().anyMatch(client -> clientName.contains(client))) {
                list.add(String.format(INTERFACE_PATH_FORMAT, clientName,
                        getJavaClassName(definition, Mode.INTERFACE)));
            }
        }

        return list;
    }

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        String className = super.getJavaClassName(definition, mode);

        if (mode == Mode.POJO) {
            String clientName = definition.getOutputName()
                    .substring(0, definition.getOutputName().indexOf(PREFIX_SEPARATOR));
            if (clients.stream().anyMatch(client -> clientName.contains(client))) {
                className += "Impl";
            }
        }
        return className;
    }
}
