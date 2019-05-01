package org.xcolab.service.admin.domain.configurationattribute;

import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.admin.pojo.IConfigurationAttribute;
import org.xcolab.client.admin.pojo.tables.pojos.ConfigurationAttribute;

import org.xcolab.util.i18n.I18nUtils;

import java.util.Optional;

import static org.xcolab.model.Tables.CONFIGURATION_ATTRIBUTE;

@Repository
public class ConfigurationAttributeDaoImpl implements ConfigurationAttributeDao {

    private static final String DEFAULT_LOCALE = I18nUtils.DEFAULT_LANGUAGE;

    private final DSLContext dslContext;

    @Autowired
    public ConfigurationAttributeDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public IConfigurationAttribute create(IConfigurationAttribute pojo) {
        dslContext.insertInto(CONFIGURATION_ATTRIBUTE)
                .set(CONFIGURATION_ATTRIBUTE.NAME, pojo.getName())
                .set(CONFIGURATION_ATTRIBUTE.ADDITIONAL_ID, pojo.getAdditionalId())
                .set(CONFIGURATION_ATTRIBUTE.LOCALE, pojo.getLocale())
                .set(CONFIGURATION_ATTRIBUTE.STRING_VALUE, pojo.getStringValue())
                .set(CONFIGURATION_ATTRIBUTE.NUMERIC_VALUE, pojo.getNumericValue())
                .set(CONFIGURATION_ATTRIBUTE.REAL_VALUE, pojo.getRealValue())
                .execute();
        return pojo;
    }

    @Override
    public Optional<IConfigurationAttribute> getConfigurationAttribute(String attributeName,
            String locale) {

        final Condition localeCondition;
        if (StringUtils.isEmpty(locale) || DEFAULT_LOCALE.equalsIgnoreCase(locale)) {
            localeCondition = CONFIGURATION_ATTRIBUTE.LOCALE.eq(StringUtils.EMPTY)
                    .or(CONFIGURATION_ATTRIBUTE.LOCALE.equalIgnoreCase(DEFAULT_LOCALE));
        } else {
            localeCondition = CONFIGURATION_ATTRIBUTE.LOCALE.equalIgnoreCase(locale);
        }

        final Record attributeRecord = dslContext.select()
                .from(CONFIGURATION_ATTRIBUTE)
                .where(CONFIGURATION_ATTRIBUTE.NAME.eq(attributeName)
                        .and(localeCondition))
                .fetchOne();
        if (attributeRecord == null) {
            return Optional.empty();
        }
        return Optional.of(attributeRecord.into(ConfigurationAttribute.class));
    }

    @Override
    public boolean update(IConfigurationAttribute pojo) {

        return dslContext.update(CONFIGURATION_ATTRIBUTE)
                .set(CONFIGURATION_ATTRIBUTE.ADDITIONAL_ID, pojo.getAdditionalId())
                .set(CONFIGURATION_ATTRIBUTE.LOCALE, pojo.getLocale())
                .set(CONFIGURATION_ATTRIBUTE.STRING_VALUE, pojo.getStringValue())
                .set(CONFIGURATION_ATTRIBUTE.NUMERIC_VALUE, pojo.getNumericValue())
                .set(CONFIGURATION_ATTRIBUTE.REAL_VALUE, pojo.getRealValue())
                .where(CONFIGURATION_ATTRIBUTE.NAME.eq(pojo.getName())
                        .and(CONFIGURATION_ATTRIBUTE.LOCALE.eq(pojo.getLocale())))
                .execute() > 0;
    }
}
