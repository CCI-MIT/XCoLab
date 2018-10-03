package org.xcolab.service.utils.db.jooq.generator;

import org.jooq.util.Definition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class XColabClientGeneratorStrategy extends XColabGeneratorStrategy {

    @Override
    public String getJavaPackageName(Definition definition, Mode mode) {
        String packageName = super.getJavaPackageName(definition, mode);

        if (mode == Mode.INTERFACE) {
            packageName = packageName.replace(".tables.interfaces", "");
        }

        return packageName;
    }
}
