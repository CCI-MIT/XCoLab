package org.xcolab.domain.configurationattribute;

import org.xcolab.model.tables.pojos.ConfigurationAttribute;

public interface ConfigurationAttributeDao {
    ConfigurationAttribute getConfigurationAttribute(String attributeName);
}
