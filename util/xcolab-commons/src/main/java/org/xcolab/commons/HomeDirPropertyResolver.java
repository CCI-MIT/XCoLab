package org.xcolab.commons;

import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.ResourcePropertySource;

import org.xcolab.commons.exceptions.InternalException;

import java.io.File;
import java.io.IOException;

public class HomeDirPropertyResolver extends StandardEnvironment {

    private static final String HOME_DIR = System.getProperty("user.home");

    private final File propertiesFile;
    private final File yamlFile;

    public HomeDirPropertyResolver(String fileBaseName, boolean required) {
        final String fileBasePath = HOME_DIR + File.separator + fileBaseName;

        final String propertiesFilePath = fileBasePath + ".properties";
        propertiesFile = new File(propertiesFilePath);

        final String yamlFilePath = fileBasePath + ".yml";
        yamlFile = new File(yamlFilePath);

        if (!(propertiesFile.exists() || yamlFile.exists()) && required) {
            throw new InternalException("No " + fileBasePath + ".properties/.yml file found.");
        }
    }

    @Override
    protected void customizePropertySources(MutablePropertySources propertySources) {
        super.customizePropertySources(propertySources);
        if (propertiesFile.exists()) {
            try {
                FileSystemResource fileSystemResource = new FileSystemResource(propertiesFile);
                propertySources.addLast(new ResourcePropertySource(fileSystemResource));
            } catch (IOException e) {
                throw new InternalException("Error loading properties file: " + propertiesFile.getAbsolutePath(), e);
            }
        }
        if (yamlFile.exists()) {
            try {
                FileSystemResource fileSystemResource = new FileSystemResource(yamlFile);
                propertySources.addLast(new ResourcePropertySource(fileSystemResource));
            } catch (IOException e) {
                throw new InternalException("Error loading YAML file: " + yamlFile.getAbsolutePath(), e);
            }
        }
    }
}
