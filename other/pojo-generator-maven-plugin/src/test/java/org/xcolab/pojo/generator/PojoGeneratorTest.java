package org.xcolab.pojo.generator;

import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PojoGeneratorTest {

    @Rule
    public MojoRule rule = new MojoRule();

    @Test
    public void testSomething() throws Exception {
        File pom = new File("target/test-classes/project-to-test/");
        assertNotNull(pom);
        assertTrue(pom.exists());

        PojoGenerator pojoGenerator = (PojoGenerator) rule.lookupConfiguredMojo(pom, "generate");
        assertNotNull(pojoGenerator);

        File outputDirectory =
                (File) rule.getVariableValueFromObject(pojoGenerator, "outputDirectory");
        assertNotNull(outputDirectory);

        File interfaceDirectory =
                (File) rule.getVariableValueFromObject(pojoGenerator, "interfaceDirectory");
        assertNotNull(interfaceDirectory);

        String packageSuffix = (String) rule.getVariableValueFromObject(pojoGenerator, "packageSuffix");
        assertNotNull(packageSuffix);

        List<File> interfaceFiles = Files.walk(interfaceDirectory.toPath())
                .filter(Files::isRegularFile)
                .map(path -> path.toFile())
                .filter(file -> file.getAbsolutePath().endsWith(".java"))
                .collect(Collectors.toList());

        assertEquals(2, interfaceFiles.size());

        pojoGenerator.execute();

        File outputPackage = new File(outputDirectory, packageSuffix.replaceAll("\\.", "/"));
        List<File> classFiles = Files.walk(outputPackage.toPath())
                .filter(Files::isRegularFile)
                .map(path -> path.toFile())
                .filter(file -> file.getAbsolutePath().endsWith(".java"))
                .collect(Collectors.toList());

        for (File file : classFiles) {
            assertTrue(file.exists());
        }
    }

    private static String getClassName(String className) {
        if (className.length() >= 2
                && className.charAt(0) == 'I'
                && Character.isUpperCase(className.charAt(1))) {

            return className.substring(1);
        }
        return className;
    }
}
