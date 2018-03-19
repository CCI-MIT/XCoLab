package org.xcolab.commons;

import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.ResourcePropertySource;

import org.xcolab.commons.exceptions.InternalException;

import java.io.File;
import java.io.IOException;

public class HomeDirPropertyResolver extends StandardEnvironment {

    public HomeDirPropertyResolver(String fileBaseName, boolean required) {
        final String fileBasePath = System.getProperty("user.home") + File.separator + fileBaseName;

        final String propertiesFilePath = fileBasePath + ".properties";
        final File propertiesFile = new File(propertiesFilePath);

        final String yamlFilePath = fileBasePath + ".yml";
        final File yamlFile = new File(yamlFilePath);

        if (!(propertiesFile.exists() || yamlFile.exists()) && required) {
            throw new InternalException("No " + fileBasePath + ".properties/.yml file found.");
        }

        addPropertySource(propertiesFile);
        addPropertySource(yamlFile);
    }

    private void addPropertySource(File propertiesFile) {
        if (propertiesFile.exists()) {
            try {
                FileSystemResource fileSystemResource = new FileSystemResource(propertiesFile);
                getPropertySources().addLast(new ResourcePropertySource(fileSystemResource));
            } catch (IOException e) {
                throw new InternalException("Error loading properties file: "
                        + propertiesFile.getAbsolutePath(), e);
            }
        }
    }
}
