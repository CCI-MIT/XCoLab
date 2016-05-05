package org.xcolab.service.admin.domain.configurationattribute;

import static org.xcolab.model.Tables.CONFIGURATION_ATTRIBUTE;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ConfigurationAttribute;

@Repository
public class ConfigurationAttributeDaoImpl implements ConfigurationAttributeDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public ConfigurationAttribute getConfigurationAttribute(String attributeName) {
        final Record attributeRecord = dslContext.select()
                .from(CONFIGURATION_ATTRIBUTE)
                .where(CONFIGURATION_ATTRIBUTE.NAME.eq(attributeName))
                .fetchOne();
        if (attributeRecord == null) {
            return null;
        }
        return attributeRecord.into(ConfigurationAttribute.class);
    }
}
