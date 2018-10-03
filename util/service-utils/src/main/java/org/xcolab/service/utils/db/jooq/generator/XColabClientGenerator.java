package org.xcolab.service.utils.db.jooq.generator;

import org.jooq.util.CatalogDefinition;
import org.jooq.util.Definition;
import org.jooq.util.GeneratorStrategy.Mode;
import org.jooq.util.JavaGenerator;
import org.jooq.util.JavaWriter;
import org.jooq.util.RoutineDefinition;
import org.jooq.util.SchemaDefinition;
import org.jooq.util.TableDefinition;
import org.jooq.util.TypedElementDefinition;
import org.jooq.util.UDTDefinition;

import java.util.Iterator;
import java.util.List;

public class XColabClientGenerator extends JavaGenerator {

    @Override
    protected void printTableJPAAnnotation(JavaWriter out, TableDefinition table) {
        super.printTableJPAAnnotation(out, table);

        // TODO COLAB-2918: do this only for interfaces

        String clientName = table.getOutputName().substring(0, table.getOutputName().indexOf(XColabGeneratorStrategy.PREFIX_SEPARATOR));
        String className = getStrategy().getJavaClassName(table, Mode.POJO);
        String pojoFQDN = String.format("org.xcolab.client.%s.pojo.tables.pojos.%s", clientName, className);

        StringBuilder annotation = new StringBuilder();
        annotation.append(String.format("@%s(", out.ref("com.fasterxml.jackson.databind.annotation.JsonDeserialize")));
        annotation.append(String.format("as = %s.class)", out.ref(pojoFQDN)));
        out.println(annotation.toString());
    }
}
