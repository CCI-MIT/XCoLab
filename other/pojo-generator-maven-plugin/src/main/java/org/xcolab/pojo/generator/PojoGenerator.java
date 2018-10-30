package org.xcolab.pojo.generator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.util.Refactory;
import org.jboss.forge.roaster.model.util.Strings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.COMPILE)
public class PojoGenerator extends AbstractMojo {

    @Parameter(property = "interfaceDirectory", required = true)
    private File interfaceDirectory;

    @Parameter(property = "packageName", required = true)
    private String packageName;

    @Parameter(defaultValue = "${project.build.directory}", property = "outputDirectory", required = true)
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException {
        List<JavaInterfaceSource> interfaces = getInterfaces();

        List<JavaClassSource> pojos = createPojos(interfaces);

        for (JavaClassSource pojo : pojos) {
            System.out.println(pojo.toString());
        }

        File f = outputDirectory;

        if (!f.exists()) {
            f.mkdirs();
        }

        File touch = new File(f, "touch.txt");

        FileWriter w = null;
        try {
            w = new FileWriter(touch);

            w.write("touch.txt");
        } catch (IOException e) {
            throw new MojoExecutionException("Error creating file " + touch, e);
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    private List<JavaInterfaceSource> getInterfaces() {
        if (!interfaceDirectory.exists()) {
            throw new IllegalArgumentException("interfaceDirectory " + interfaceDirectory + " does not exist!");
        }
        if (!interfaceDirectory.isDirectory()) {
            throw new IllegalArgumentException("interfaceDirectory " + interfaceDirectory + " is not a directory!");
        }

        List<JavaInterfaceSource> interfaces = new ArrayList<>();
        File[] interfaceFiles = interfaceDirectory.listFiles();
        for (File interfaceFile : interfaceFiles) {
            try {
                JavaInterfaceSource interfaceType = Roaster.parse(JavaInterfaceSource.class, interfaceFile);
                interfaces.add(interfaceType);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return interfaces;
    }

    private List<JavaClassSource> createPojos(List<JavaInterfaceSource> interfaces) {
        List<JavaClassSource> pojos = new ArrayList<>();
        for (JavaInterfaceSource src : interfaces) {
            JavaClassSource pojo = Roaster.create(JavaClassSource.class);

            pojo.setPackage(packageName).setName(PojoGenerator.getClassName(src.getName()));
            pojo.addInterface(src);

            List<FieldSource<JavaClassSource>> fields = new ArrayList<>();
            for (MethodSource<JavaInterfaceSource> method : src.getMethods()) {
                if (method.getReturnType().isType("void")
                        && method.getName().startsWith("set")
                        && method.getParameters().size() == 1) {

                    org.jboss.forge.roaster.model.Parameter parameter = method.getParameters().get(0);

                    FieldSource<JavaClassSource> field = pojo.addField()
                            .setName(parameter.getName())
                            .setType(parameter.getType().getSimpleName())
                            .setPrivate();
                    fields.add(field);

                }
            }

            PojoGenerator.createEmptyConstructor(pojo);
            PojoGenerator.createFullConstructor(pojo, fields);
            PojoGenerator.createCopyConstructor(pojo, src.getName(), fields);

            PojoGenerator.createGettersAndSetters(pojo, fields);

            Refactory.createHashCodeAndEquals(pojo, fields.toArray(new FieldSource[0]));
            Refactory.createToStringFromFields(pojo, fields);

            pojos.add(pojo);
        }
        return pojos;
    }

    private static String getClassName(String name) {
        if (!name.startsWith("I")) {
            throw new IllegalArgumentException("Interface " + name + " does not start with 'I'");
        }
        name = name.substring(1);
        return Strings.capitalize(name);
    }

    private static void createEmptyConstructor(JavaClassSource pojo) {
        pojo.addMethod()
                .setPublic()
                .setConstructor(true)
                .setBody("");
    }

    private static void createFullConstructor(JavaClassSource pojo, List<FieldSource<JavaClassSource>> fields) {
        String parameters = fields.stream().map(field -> {
            return field.getType().getSimpleName() + " " + field.getName();
        }).collect(Collectors.joining(", "));

        String body = fields.stream().map(field -> {
            return String.format("this.%s = %s;", field.getName(), field.getName());
        }).collect(Collectors.joining("\n"));

        pojo.addMethod()
                .setPublic()
                .setConstructor(true)
                .setParameters(parameters)
                .setBody(body);
    }

    private static void createCopyConstructor(JavaClassSource pojo, String interfaceType, List<FieldSource<JavaClassSource>> fields) {
        String parameter = interfaceType + " value";

        String body = fields.stream().map(field -> {
            return String.format("this.%s = value.%s;", field.getName(), field.getName());
        }).collect(Collectors.joining("\n"));

        pojo.addMethod()
                .setPublic()
                .setConstructor(true)
                .setParameters(parameter)
                .setBody(body);
    }

    private static void createGettersAndSetters(JavaClassSource pojo, List<FieldSource<JavaClassSource>> fields) {
        for (FieldSource<JavaClassSource> field : fields) {
            String capitalizedName = Strings.capitalize(field.getName());
            String parameterString = field.getType().getSimpleName() + " " + field.getName();
            String bodySetter = String.format("this.%s = %s;", field.getName(), field.getName());
            String bodyGetter = String.format("return %s;", field.getName());

            pojo.addMethod()
                    .setPublic()
                    .setReturnTypeVoid()
                    .setName("get" + capitalizedName)
                    .setParameters(parameterString)
                    .setBody(bodySetter)
                    .addAnnotation(Override.class);

            pojo.addMethod()
                    .setPublic()
                    .setReturnType(field.getType())
                    .setName("get" + capitalizedName)
                    .setBody(bodyGetter)
                    .addAnnotation(Override.class);
        }
    }
}
