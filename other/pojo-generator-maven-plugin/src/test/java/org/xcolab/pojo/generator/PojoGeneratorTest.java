package org.xcolab.pojo.generator;

import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.WithoutMojo;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

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

        File outputDirectory = (File) rule.getVariableValueFromObject(pojoGenerator, "outputDirectory");
        assertNotNull(outputDirectory);

        File interfaceDirectory = (File) rule.getVariableValueFromObject(pojoGenerator, "interfaceDirectory");
        assertNotNull(interfaceDirectory);

        pojoGenerator.execute();

        File touch = new File(outputDirectory, "touch.txt");
        assertTrue(touch.exists());
    }

    @Test
    @WithoutMojo
    public void test() {

    }
}
