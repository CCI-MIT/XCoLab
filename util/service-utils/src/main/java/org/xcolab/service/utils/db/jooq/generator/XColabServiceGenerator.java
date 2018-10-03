package org.xcolab.service.utils.db.jooq.generator;

import org.jooq.util.Definition;
import org.jooq.util.GeneratorStrategy.Mode;
import org.jooq.util.JavaGenerator;
import org.jooq.util.JavaWriter;
import org.jooq.util.RoutineDefinition;
import org.jooq.util.TableDefinition;
import org.jooq.util.TypedElementDefinition;
import org.jooq.util.UDTDefinition;

import java.util.Iterator;
import java.util.List;

public class XColabServiceGenerator extends JavaGenerator {

    @Override
    protected void generatePojoClassFooter(TableDefinition table, JavaWriter out) {
        super.generatePojoClassFooter(table, out);

        if (table.getName().contains("tracking")) { //TODO: COLAB-2918: remove when new client/server architecture is used for all services
            this.printFromAndInto(out, (Definition)table);
        }
    }

    private void printFromAndInto(JavaWriter out, Definition tableOrUDT) {
        String clientName = tableOrUDT.getOutputName().substring(0, tableOrUDT.getOutputName().indexOf(XColabGeneratorStrategy.PREFIX_SEPARATOR));
        String qualified = out.ref(String.format(XColabGeneratorStrategy.INTERFACE_PATH_FORMAT, clientName, this.getStrategy().getJavaClassName(tableOrUDT, Mode.INTERFACE)));

        out.tab(1).header("FROM and INTO", new Object[0]);
        out.tab(1).overrideInheritIf(false);
        out.tab(1).println("public void from(%s from) {", new Object[]{qualified});
        Iterator var4 = this.getTypedElements(tableOrUDT).iterator();

        while(var4.hasNext()) {
            TypedElementDefinition<?> column = (TypedElementDefinition)var4.next();
            String setter = this.getStrategy().getJavaSetterName(column, Mode.INTERFACE);
            String getter = this.getStrategy().getJavaGetterName(column, Mode.INTERFACE);
            out.tab(2).println("%s(from.%s());", new Object[]{setter, getter});
        }

        out.tab(1).println("}");
        out.tab(1).overrideInherit();
        out.tab(1).println("public <E extends %s> E into(E into) {", new Object[]{qualified});
        out.tab(2).println("into.from(this);");
        out.tab(2).println("return into;");
        out.tab(1).println("}");
    }

    private List<? extends TypedElementDefinition<? extends Definition>> getTypedElements(Definition definition) {
        if (definition instanceof TableDefinition) {
            return ((TableDefinition)definition).getColumns();
        } else if (definition instanceof UDTDefinition) {
            return ((UDTDefinition)definition).getAttributes();
        } else if (definition instanceof RoutineDefinition) {
            return ((RoutineDefinition)definition).getAllParameters();
        } else {
            throw new IllegalArgumentException("Unsupported type : " + definition);
        }
    }
}
