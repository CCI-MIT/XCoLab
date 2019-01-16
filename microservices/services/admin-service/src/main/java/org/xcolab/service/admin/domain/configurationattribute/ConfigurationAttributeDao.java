package org.xcolab.service.admin.domain.configurationattribute;

import org.xcolab.client.admin.pojo.IConfigurationAttribute;

import java.util.Optional;

public interface ConfigurationAttributeDao {

    IConfigurationAttribute create(IConfigurationAttribute pojo);

    Optional<IConfigurationAttribute> getConfigurationAttribute(String attributeName, String locale);

    boolean update(IConfigurationAttribute pojo);
}
