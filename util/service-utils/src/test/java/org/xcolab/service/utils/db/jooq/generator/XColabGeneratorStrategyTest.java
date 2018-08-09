package org.xcolab.service.utils.db.jooq.generator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XColabGeneratorStrategyTest {

    private final XColabGeneratorStrategy strategy = new XColabGeneratorStrategy();

    @Test
    public void customizeJavaClassName__givenSnakeCase__shouldReturnCamelCase() {
        String input = "my_snake_case_name";
        String expected = "MySnakeCaseName";
        assertEquals("Output is not camel case.", expected,
                strategy.customizeJavaClassName(input));
    }

    @Test
    public void customizeJavaClassName__givenPrefixedName__shouldReturnUnprefixedName() {
        String input = "prefix__snake_case";
        String expected = "SnakeCase";
        assertEquals("Prefix removal did not work.", expected,
                strategy.customizeJavaClassName(input));
    }

    @Test
    public void customizeJavaIdentifier__givenPrefixedName__shouldReturnUnprefixedName() {
        String input = "prefix__snake_case";
        String expected = "SNAKE_CASE";
        assertEquals("Prefix removal did not work.", expected,
                strategy.customizeJavaIdentifier(input));
    }
}
