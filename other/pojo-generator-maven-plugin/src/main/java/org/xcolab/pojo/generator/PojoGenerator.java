package org.xcolab.pojo.generator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.jboss.forge.roaster.ParserException;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.util.Refactory;
import org.jboss.forge.roaster.model.util.Strings;
import org.jboss.forge.roaster.model.util.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Mojo(name = "generatePojos", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class PojoGenerator extends AbstractMojo {

    private static final Logger _log = LoggerFactory.getLogger(PojoGenerator.class);

    private static final Pattern PACKAGE_PATTERN =
            Pattern.compile("^[a-z][a-z0-9_]*(\\.[a-z0-9_]+)+[0-9a-z_]");

    @Parameter(property = "interfaceDirectory", required = true)
    private File interfaceDirectory;

    @Parameter(property = "implementationPackageSuffix", required = true)
    private String implementationPackageSuffix;

    @Parameter(defaultValue = "target/generated-sources/roaster", property = "outputDirectory",
            required = true)
    private File outputDirectory;

    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException {
        validateParameters();

        project.addCompileSourceRoot(outputDirectory.getPath());

        Map<JavaInterfaceSource, String> interfaces = getInterfaces();

        Map<JavaClassSource, String> pojos = createPojos(interfaces);

        final File outputPackage =
                new File(outputDirectory, implementationPackageSuffix.replaceAll("\\.", "/"));

        if (!outputPackage.exists()) {
            outputPackage.mkdirs();
        }

        for (Entry<JavaClassSource, String> pojoEntry : pojos.entrySet()) {
            File fileDir = outputPackage;
            if ("".equals(pojoEntry.getValue())) {
                fileDir = new File(outputPackage, pojoEntry.getValue());
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
            }
            File javaFile = new File(fileDir, pojoEntry.getKey().getName() + ".java");
            try (FileWriter w = new FileWriter(javaFile)) {
                w.write(pojoEntry.getKey().toString());
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

        if (!PACKAGE_PATTERN.matcher(implementationPackageSuffix).matches()) {
            throw new IllegalArgumentException(
                    "implementationPackageSuffix " + implementationPackageSuffix
                            + " does not comply to the naming conventions.");
        }
    }

    private Map<JavaInterfaceSource, String> getInterfaces() {
        Map<JavaInterfaceSource, String> interfaces = new HashMap<>();
        try {
            List<File> interfaceFiles = interfaceFiles = Files.walk(interfaceDirectory.toPath())
                    .filter(Files::isRegularFile)
                    .map(path -> path.toFile())
                    .filter(path -> path.getAbsolutePath().endsWith(".java"))
                    .collect(Collectors.toList());

            for (File interfaceFile : interfaceFiles) {
                try {
                    JavaSource javaSrc = Roaster.parse(JavaSource.class, interfaceFile);
                    if (javaSrc instanceof JavaInterfaceSource) {
                        JavaInterfaceSource interfaceSrc = (JavaInterfaceSource) javaSrc;
                        Path path = Paths.get(interfaceFile.toURI());
                        path = Paths.get(interfaceDirectory.toURI()).relativize(path);
                        String subdir = path.toString().replace(path.getFileName().toString(), "");
                        interfaces.put(interfaceSrc, subdir);
                    } else {
                        _log.warn("File {} is not an java interface", interfaceFile);
                    }
                } catch (FileNotFoundException e) {
                    throw new IllegalArgumentException(
                            "File " + interfaceFile.getAbsolutePath() + " does not exist.", e);
                } catch (ParserException e) {
                    throw new IllegalArgumentException(
                            "File " + interfaceFile.getAbsolutePath() + " cannot be parsed", e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return interfaces;
    }

    private Map<JavaClassSource, String> createPojos(Map<JavaInterfaceSource, String> interfaces) {
        Map<JavaClassSource, String> pojos = new HashMap<>();
        for (Entry<JavaInterfaceSource, String> srcEntry : interfaces.entrySet()) {
            JavaClassSource pojo = Roaster.create(JavaClassSource.class);

            String suffix = implementationPackageSuffix;
            if (!srcEntry.getValue().isEmpty()) {
                suffix += "." + srcEntry.getValue().replaceAll("/", "");
            }

            pojo.setPackage(srcEntry.getKey().getPackage() + "." + suffix)
                    .setName(PojoGenerator.getClassName(srcEntry.getKey().getName()));
            pojo.addAnnotation(JsonInclude.class).setEnumValue(Include.NON_NULL);
            pojo.addAnnotation(JsonIgnoreProperties.class).setLiteralValue("ignoreUnknown", "true");
            pojo.addInterface(srcEntry.getKey());
            pojo.addInterface(Serializable.class);

            List<FieldSource<JavaClassSource>> fields = new ArrayList<>();
            for (MethodSource<JavaInterfaceSource> method : srcEntry.getKey().getMethods()) {
                if (isSetter(method) && !method.isDefault()) {
                    org.jboss.forge.roaster.model.Parameter parameter = verifyParameterName(method);

                    FieldSource<JavaClassSource> field = pojo.addField()
                            .setName(parameter.getName())
                            .setType(parameter.getType().getName())
                            .setPrivate();

                    fields.add(field);
                    addImports(pojo, parameter.getType());
                }
            }

            PojoGenerator.createDefaultConstructor(pojo);
            PojoGenerator.createFullConstructor(pojo, fields);
            PojoGenerator.createCopyConstructor(pojo, fields);

            PojoGenerator.createGettersAndSetters(pojo, fields);

            PojoGenerator.duplicateDefaultMethods(pojo, srcEntry.getKey());

            Refactory.createHashCodeAndEquals(pojo, fields.toArray(new FieldSource[0]));
            Refactory.createToStringFromFields(pojo, fields);


            pojos.put(pojo, srcEntry.getValue());
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

    private static void createDefaultConstructor(JavaClassSource pojo) {
        pojo.addMethod()
                .setPublic()
                .setConstructor(true)
                .setBody("");
    }

    private static void createFullConstructor(JavaClassSource pojo,
            List<FieldSource<JavaClassSource>> fields) {
        String parameters = fields.stream()
                .map(field -> field.getType() + " " + field.getName())
                .collect(Collectors.joining(", "));

        String body = fields.stream()
                .map(field -> String.format("this.%s = %s;", field.getName(), field.getName()))
                .collect(Collectors.joining("\n"));

        pojo.addMethod()
                .setPublic()
                .setConstructor(true)
                .setParameters(parameters)
                .setBody(body);
    }

    private static void createCopyConstructor(JavaClassSource pojo,
            List<FieldSource<JavaClassSource>> fields) {
        String parameter = pojo.getName() + " value";

        String body = fields.stream()
                .map(field -> String
                        .format("this.%s = value.%s;", field.getName(), field.getName()))
                .collect(Collectors.joining("\n"));

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
            String getterPrefix = field.getType().isType(Boolean.class) ? "is" : "get";

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
                    .setName(getterPrefix + capitalizedName)
                    .setBody(bodyGetter)
                    .addAnnotation(Override.class);


            if (field.getType().isType(Boolean.class)) {
                pojo.addMethod()
                        .setPublic()
                        .setReturnType(field.getType())
                        .setName("get" + capitalizedName)
                        .setBody(String.format("return this.is%s();", capitalizedName))
                        .addAnnotation(Deprecated.class);
            }
        }
    }

    /*
    Adding the default methods as normal methods is required due to following bug:
    https://stackoverflow.com/questions/45422679/getter-in-an-interface-with-default-method-jsf
     */
    private static void duplicateDefaultMethods(JavaClassSource pojo, JavaInterfaceSource src) {
        List<MethodSource<JavaInterfaceSource>> defaultMethods =
                src.getMethods().stream().filter(m -> m.isDefault()).collect(Collectors.toList());
        for (MethodSource<JavaInterfaceSource> defaultMethod : defaultMethods) {
            if (!defaultMethod.hasAnnotation(Override.class)) {
                defaultMethod.addAnnotation(Override.class);
            }
            if (!defaultMethod.hasAnnotation(JsonIgnore.class)) {
                defaultMethod.addAnnotation(JsonIgnore.class);
            }
            if (!pojo.hasImport(JsonIgnore.class)) {
                pojo.addImport(JsonIgnore.class);
            }
            defaultMethod.setDefault(false);
            defaultMethod.setPublic();
            createDefaultMethodBody(pojo, defaultMethod, src.getName());
            pojo.addMethod(defaultMethod);
        }
    }

    /*
    1.: return ILocation.super.testDefaultGetter();
    2.: ILocation.super.testDefaultSetter(List<String> strings);
     */
    private static void createDefaultMethodBody(JavaClassSource pojo,
            MethodSource<JavaInterfaceSource> defaultMethod, String interfaceName) {
        String body = "";
        if (!defaultMethod.isReturnTypeVoid()) {
            body += "return ";
            addImports(pojo, defaultMethod.getReturnType());
        }
        body += interfaceName + ".super." + defaultMethod.getName() + "(";

        body += defaultMethod.getParameters().stream().map(parameter -> {
            addImports(pojo, parameter.getType());
            return parameter.getName();
        }).collect(Collectors.joining(", "));

        body += ");";
        defaultMethod.setBody(body);
    }

    private static void addImports(JavaClassSource pojo, Type<JavaInterfaceSource> type) {
        if (!Types.isJavaLang(type.getQualifiedName()) && !Types
                .isBasicType(type.getQualifiedName())) {
            pojo.addImport(type.getQualifiedName());
        }
        type.getTypeArguments().stream().forEach(genericType -> {
            pojo.addImport(genericType);
        });
    }
}
