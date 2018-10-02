package org.xcolab.service.utils.db.jooq.generator;

import org.jooq.util.Definition;
import org.jooq.util.GeneratorStrategy.Mode;
import org.jooq.util.JavaGenerator;
import org.jooq.util.JavaWriter;
import org.jooq.util.RoutineDefinition;
import org.jooq.util.TableDefinition;
import org.jooq.util.TypedElementDefinition;
import org.jooq.util.UDTDefinition;

import java.util.List;

public class XColabGenerator extends JavaGenerator {

    protected void generateInterface(TableDefinition table, JavaWriter out) {
        this.generateInterface0(table, out);
    }

    private final void generateInterface0(Definition tableOrUDT, JavaWriter out) {
        String className = this.getStrategy().getJavaClassName(tableOrUDT, Mode.INTERFACE);
        List<String> interfaces = out.ref(this.getStrategy().getJavaClassImplements(tableOrUDT, Mode.INTERFACE));
        this.printPackage(out, tableOrUDT, Mode.INTERFACE);
        if (tableOrUDT instanceof TableDefinition) {
            this.generateInterfaceClassJavadoc((TableDefinition)tableOrUDT, out);
        } else {
            this.generateUDTInterfaceClassJavadoc((UDTDefinition)tableOrUDT, out);
        }

        this.printClassAnnotations(out, tableOrUDT.getSchema());
        if (tableOrUDT instanceof TableDefinition) {
            this.printTableJPAAnnotation(out, (TableDefinition)tableOrUDT);
        }

        out.println("public interface %s [[before=extends ][%s]] {", new Object[]{className, interfaces});

        List<? extends TypedElementDefinition<?>> typedElements = this.getTypedElements(tableOrUDT);

        for(int i = 0; i < typedElements.size(); ++i) {
            TypedElementDefinition<?> column = (TypedElementDefinition)typedElements.get(i);
            if (!this.generateImmutableInterfaces()) {
                if (tableOrUDT instanceof TableDefinition) {
                    this.generateInterfaceSetter(column, i, out);
                } else {
                    this.generateUDTInterfaceSetter(column, i, out);
                }
            }

            if (tableOrUDT instanceof TableDefinition) {
                this.generateInterfaceGetter(column, i, out);
            } else {
                this.generateUDTInterfaceGetter(column, i, out);
            }
        }

//        if (!this.generateImmutableInterfaces()) {
//            String local = this.getStrategy().getJavaClassName(tableOrUDT, Mode.INTERFACE);
//            String qualified = out.ref(this.getStrategy().getFullJavaClassName(tableOrUDT, Mode.INTERFACE));
//            ((JavaWriter)out.tab(1)).header("FROM and INTO", new Object[0]);
//            ((JavaWriter)out.tab(1)).javadoc("Load data from another generated Record/POJO implementing the common interface %s", new Object[]{local});
//            ((JavaWriter)out.tab(1)).println("public void from(%s from);", new Object[]{qualified});
//
//            ((JavaWriter)out.tab(1)).javadoc("Copy data into another generated Record/POJO implementing the common interface %s", new Object[]{local});
//            ((JavaWriter)out.tab(1)).println("public <E extends %s> E into(E into);", new Object[]{qualified});
//        }

        if (tableOrUDT instanceof TableDefinition) {
            this.generateInterfaceClassFooter((TableDefinition)tableOrUDT, out);
        } else {
            this.generateUDTInterfaceClassFooter((UDTDefinition)tableOrUDT, out);
        }

        out.println("}");
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
