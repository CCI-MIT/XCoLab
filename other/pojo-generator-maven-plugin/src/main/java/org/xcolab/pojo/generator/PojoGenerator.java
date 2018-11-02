package org.xcolab.pojo.generator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.jboss.forge.roaster.ParserException;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.util.Refactory;
import org.jboss.forge.roaster.model.util.Strings;
import org.jboss.forge.roaster.model.util.Types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class PojoGenerator extends AbstractMojo {

    private static final Pattern PACKAGE_PATTERN =
            Pattern.compile("^[a-z][a-z0-9_]*(\\.[a-z0-9_]+)+[0-9a-z_]");

    @Parameter(property = "interfaceDirectory", required = true)
    private File interfaceDirectory;

    @Parameter(property = "packageName", required = true)
    private String packageName;

    @Parameter(defaultValue = "target/generated-sources/roaster", property = "outputDirectory",
            required = true)
    private File outputDirectory;

    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException {
        validateParameters();

        project.addCompileSourceRoot(outputDirectory.getPath());

        List<JavaInterfaceSource> interfaces = getInterfaces();

        List<JavaClassSource> pojos = createPojos(interfaces);

        File outputPackage = new File(outputDirectory, packageName.replaceAll("\\.", "/"));

        if (!outputPackage.exists()) {
            outputPackage.mkdirs();
        }

        for (JavaClassSource pojo : pojos) {
            File javaFile = new File(outputPackage, pojo.getName() + ".java");
            try (FileWriter w = new FileWriter(javaFile)) {
                w.write(pojo.toString());
            } catch (IOException e) {
                throw new MojoExecutionException("Error creating file " + javaFile, e);
            }
        }
    }

    private void validateParameters() {
        if (!interfaceDirectory.exists()) {
            throw new IllegalArgumentException(
                    "interfaceDirectory " + interfaceDirectory + " does not exist!");
        }
        if (!interfaceDirectory.isDirectory()) {
            throw new IllegalArgumentException(
                    "interfaceDirectory " + interfaceDirectory + " is not a directory!");
        }

        if (!PACKAGE_PATTERN.matcher(packageName).matches()) {
            throw new IllegalArgumentException(
                    "packageName " + packageName + " does not comply to the naming conventions.");
        }
    }

    private List<JavaInterfaceSource> getInterfaces() {
        List<JavaInterfaceSource> interfaces = new ArrayList<>();
        File[] interfaceFiles =
                interfaceDirectory.listFiles(pathname -> pathname.getName().endsWith(".java"));
        for (File interfaceFile : interfaceFiles) {
            try {
                JavaInterfaceSource interfaceSrc =
                        Roaster.parse(JavaInterfaceSource.class, interfaceFile);
                interfaces.add(interfaceSrc);
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException(
                        "File " + interfaceFile.getAbsolutePath() + " does not exist.", e);
            } catch (ParserException e) {
                throw new IllegalArgumentException(
                        "File " + interfaceFile.getAbsolutePath() + " cannot be parsed", e);
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
                if (isSetter(method) && !method.isDefault()) {
                    org.jboss.forge.roaster.model.Parameter parameter = verifyParameterName(method);

                    FieldSource<JavaClassSource> field = pojo.addField()
                            .setName(parameter.getName())
                            .setType(parameter.getType().getName())
                            .setPrivate();

                    fields.add(field);
                    if (!Types.isJavaLang(parameter.getType().getQualifiedName())
                            && !Types.isBasicType(parameter.getType().getQualifiedName())) {
                        pojo.addImport(parameter.getType().getQualifiedName());
                    }
                }
            }

            PojoGenerator.createEmptyConstructor(pojo);
            PojoGenerator.createFullConstructor(pojo, fields);
            PojoGenerator.createCopyConstructor(pojo, fields);

            PojoGenerator.createGettersAndSetters(pojo, fields);

            Refactory.createHashCodeAndEquals(pojo, fields.toArray(new FieldSource[0]));
            Refactory.createToStringFromFields(pojo, fields);

            pojos.add(pojo);
        }
        return pojos;
    }

    private org.jboss.forge.roaster.model.Parameter verifyParameterName(
            MethodSource<JavaInterfaceSource> method) {
        org.jboss.forge.roaster.model.Parameter parameter = method.getParameters().get(0);
        String parameterName = parameter.getName();
        String methodName = method.getName();
        if (methodName.endsWith(Strings.capitalize(parameterName))) {
            return parameter;
        }
        throw new IllegalArgumentException(
                "Setter name '" + methodName + "' with parameter '" + parameterName
                        + "' does not match required format (setSomeValue(int value)).");
    }

    private boolean isSetter(MethodSource<JavaInterfaceSource> method) {
        return method.getReturnType().isType("void")
                && method.getName().startsWith("set")
                && method.getParameters().size() == 1;
    }

    private static String getClassName(String className) {
        if (className.length() >= 2
                && className.charAt(0) == 'I'
                && Character.isUpperCase(className.charAt(1))) {
            
            return className.substring(1);
        }
        return className;
    }

    private static void createEmptyConstructor(JavaClassSource pojo) {
        pojo.addMethod()
                .setPublic()
                .setConstructor(true)
                .setBody("");
    }

    private static void createFullConstructor(JavaClassSource pojo,
            List<FieldSource<JavaClassSource>> fields) {
        String parameters = fields.stream().map(field -> {
            return field.getType() + " " + field.getName();
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

    private static void createCopyConstructor(JavaClassSource pojo,
            List<FieldSource<JavaClassSource>> fields) {
        String parameter = pojo.getName() + " value";

        String body = fields.stream().map(field -> {
            return String.format("this.%s = value.%s;", field.getName(), field.getName());
        }).collect(Collectors.joining("\n"));

        pojo.addMethod()
                .setPublic()
                .setConstructor(true)
                .setParameters(parameter)
                .setBody(body);
    }

    private static void createGettersAndSetters(JavaClassSource pojo,
            List<FieldSource<JavaClassSource>> fields) {
        for (FieldSource<JavaClassSource> field : fields) {
            String capitalizedName = Strings.capitalize(field.getName());
            String parameterString = field.getType().getName() + " " + field.getName();
            String bodySetter = String.format("this.%s = %s;", field.getName(), field.getName());
            String bodyGetter = String.format("return %s;", field.getName());

            pojo.addMethod()
                    .setPublic()
                    .setReturnTypeVoid()
                    .setName("set" + capitalizedName)
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
