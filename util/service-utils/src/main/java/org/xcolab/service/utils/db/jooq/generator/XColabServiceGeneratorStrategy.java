package org.xcolab.service.utils.db.jooq.generator;

import org.jooq.util.Definition;

import java.util.List;

public class XColabServiceGeneratorStrategy extends XColabGeneratorStrategy {

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        String className = super.getJavaClassName(definition, mode);

        if (mode == Mode.POJO) {
            String clientName = definition.getOutputName().substring(0, definition.getOutputName().indexOf(PREFIX_SEPARATOR));
            if (clientName.contains("tracking")) { //TODO: COLAB-2918: remove when new client/server architecture is used for all services
                className += "Impl";
            }
        }
        return className;
    }
}
