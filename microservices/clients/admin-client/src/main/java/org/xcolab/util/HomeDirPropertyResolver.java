package org.xcolab.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.ResourcePropertySource;

import org.xcolab.util.exceptions.InternalException;

import java.io.File;
import java.io.IOException;

public class HomeDirPropertyResolver extends StandardEnvironment {

    private static final Logger _log = LoggerFactory.getLogger(HomeDirPropertyResolver.class);

    private static final String HOME_DIR = System.getProperty("user.home");
    private static final String FILE_BASE_NAME = ".xcolab.application";
    private static final String FILE_BASE_PATH = HOME_DIR + File.separator + FILE_BASE_NAME;

    //.properties file
    private static final String PROPERTIES_FILE_PATH = FILE_BASE_PATH + ".properties";
    private static final File PROPERTIES_FILE = new File(PROPERTIES_FILE_PATH);

    //.yml file
    private static final String YAML_FILE_PATH = FILE_BASE_PATH + ".yml";
    private static final File YAML_FILE = new File(YAML_FILE_PATH);

    public HomeDirPropertyResolver(boolean required) {
        if (!(PROPERTIES_FILE.exists() || YAML_FILE.exists()) && required) {
            throw new InternalException("No property files found: " + PROPERTIES_FILE_PATH);
        }
    }

    @Override
    protected void customizePropertySources(MutablePropertySources propertySources) {
        super.customizePropertySources(propertySources);
        try {
            propertySources.addLast(
                    new ResourcePropertySource(new FileSystemResource(PROPERTIES_FILE)));
            propertySources.addLast(
                    new ResourcePropertySource(new FileSystemResource(YAML_FILE)));

        } catch (IOException e) {
            _log.warn("No properties file with allowed extension found in {}", PROPERTIES_FILE_PATH);
        }
    }
}
