package org.xcolab.service.admin.domain.configurationattribute;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ConfigurationAttribute;

import java.util.Optional;

import static org.xcolab.model.Tables.CONFIGURATION_ATTRIBUTE;

@Repository
public class ConfigurationAttributeDaoImpl implements ConfigurationAttributeDao {

    private final DSLContext dslContext;

    @Autowired
    public ConfigurationAttributeDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public ConfigurationAttribute create(ConfigurationAttribute pojo) {
        dslContext.insertInto(CONFIGURATION_ATTRIBUTE)
                .set(CONFIGURATION_ATTRIBUTE.NAME, pojo.getName())
                .set(CONFIGURATION_ATTRIBUTE.ADDITIONAL_ID, pojo.getAdditionalId())
                .set(CONFIGURATION_ATTRIBUTE.STRING_VALUE, pojo.getStringValue())
                .set(CONFIGURATION_ATTRIBUTE.NUMERIC_VALUE, pojo.getNumericValue())
                .set(CONFIGURATION_ATTRIBUTE.REAL_VALUE, pojo.getRealValue())
                .execute();
        return pojo;
    }

    @Override
    public Optional<ConfigurationAttribute> getConfigurationAttribute(String attributeName) {
        final Record attributeRecord = dslContext.select()
                .from(CONFIGURATION_ATTRIBUTE)
                .where(CONFIGURATION_ATTRIBUTE.NAME.eq(attributeName))
                .fetchOne();
        if (attributeRecord == null) {
            return Optional.empty();
        }
        return Optional.of(attributeRecord.into(ConfigurationAttribute.class));
    }
}
