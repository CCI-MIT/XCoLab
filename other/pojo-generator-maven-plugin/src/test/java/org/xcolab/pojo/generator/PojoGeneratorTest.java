package org.xcolab.pojo.generator;

import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.WithoutMojo;
import org.jboss.forge.roaster.model.util.Strings;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PojoGeneratorTest {

    @Rule
    public MojoRule rule = new MojoRule() {
        @Override
        protected void before() throws Throwable {
        }

        @Override
        protected void after() {
        }
    };

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

        String packageName = (String) rule.getVariableValueFromObject(pojoGenerator, "packageName");
        assertNotNull(packageName);

        File[] files = interfaceDirectory.listFiles();
        assertEquals(1, files.length);

        pojoGenerator.execute();

        File outputPackage = new File(outputDirectory, packageName.replaceAll("\\.", "/"));

        for (File file : files) {
            File javaFile = new File(outputPackage, getClassName(file.getName()));
            assertTrue(javaFile.exists());
        }
    }

    @Test
    @WithoutMojo
    public void test() {

    }

    private static String getClassName(String name) {
        name = name.substring(1);
        return Strings.capitalize(name);
    }
}
