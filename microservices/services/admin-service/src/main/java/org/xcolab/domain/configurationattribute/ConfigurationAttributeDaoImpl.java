package org.xcolab.domain.configurationattribute;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.xcolab.model.tables.pojos.ConfigurationAttribute;

import static org.xcolab.model.Tables.CONFIGURATION_ATTRIBUTE;

public class ConfigurationAttributeDaoImpl implements ConfigurationAttributeDao {
    @Autowired
    private DSLContext dslContext;

    @Override
    public ConfigurationAttribute getConfigurationAttribute(String attributeName) {
        return dslContext.select()
                .from(CONFIGURATION_ATTRIBUTE)
                .where(CONFIGURATION_ATTRIBUTE.NAME.eq(attributeName))
                .fetchOne().into(ConfigurationAttribute.class);
    }
}
