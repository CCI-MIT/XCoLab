package org.xcolab.service.admin.domain.configurationattribute;

import org.xcolab.model.tables.pojos.ConfigurationAttribute;

import java.util.Optional;

public interface ConfigurationAttributeDao {

    ConfigurationAttribute create(ConfigurationAttribute pojo);

    Optional<ConfigurationAttribute> getConfigurationAttribute(String attributeName, String locale);

    boolean update(ConfigurationAttribute pojo);
}
