package org.xcolab.service.admin.domain.contesttypeattribute;

import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.admin.pojo.IContestTypeAttribute;
import org.xcolab.client.admin.pojo.tables.pojos.ContestTypeAttribute;

import org.xcolab.util.i18n.I18nUtils;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CONTEST_TYPE_ATTRIBUTE;

@Repository
public class ContestTypeAttributeDaoImpl implements ContestTypeAttributeDao {

    private static final String DEFAULT_LOCALE = I18nUtils.DEFAULT_LANGUAGE;

    private final DSLContext dslContext;

    @Autowired
    public ContestTypeAttributeDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public IContestTypeAttribute create(IContestTypeAttribute pojo) {
        dslContext.insertInto(CONTEST_TYPE_ATTRIBUTE)
                .set(CONTEST_TYPE_ATTRIBUTE.NAME, pojo.getName())
                .set(CONTEST_TYPE_ATTRIBUTE.ADDITIONAL_ID, pojo.getAdditionalId())
                .set(CONTEST_TYPE_ATTRIBUTE.LOCALE, pojo.getLocale())
                .set(CONTEST_TYPE_ATTRIBUTE.STRING_VALUE, pojo.getStringValue())
                .set(CONTEST_TYPE_ATTRIBUTE.NUMERIC_VALUE, pojo.getNumericValue())
                .set(CONTEST_TYPE_ATTRIBUTE.REAL_VALUE, pojo.getRealValue())
                .execute();
        return pojo;
    }

    @Override
    public Optional<IContestTypeAttribute> get(String attributeName, long additionalId,
            String locale) {
        final Condition localeCondition;
        if (StringUtils.isEmpty(locale) || DEFAULT_LOCALE.equalsIgnoreCase(locale)) {
            localeCondition = CONTEST_TYPE_ATTRIBUTE.LOCALE.eq(StringUtils.EMPTY)
                    .or(CONTEST_TYPE_ATTRIBUTE.LOCALE.equalIgnoreCase(DEFAULT_LOCALE));
        } else {
            localeCondition = CONTEST_TYPE_ATTRIBUTE.LOCALE.equalIgnoreCase(locale);
        }

        final Record attributeRecord = dslContext.select()
                .from(CONTEST_TYPE_ATTRIBUTE)
                .where(CONTEST_TYPE_ATTRIBUTE.NAME.eq(attributeName)
                        .and(CONTEST_TYPE_ATTRIBUTE.ADDITIONAL_ID.eq(additionalId))
                        .and(localeCondition))
                .fetchOne();
        if (attributeRecord == null) {
            return Optional.empty();
        }
        return Optional.of(attributeRecord.into(ContestTypeAttribute.class));
    }

    @Override
    public boolean update(IContestTypeAttribute pojo) {
        return dslContext.update(CONTEST_TYPE_ATTRIBUTE)
                .set(CONTEST_TYPE_ATTRIBUTE.ADDITIONAL_ID, pojo.getAdditionalId())
                .set(CONTEST_TYPE_ATTRIBUTE.LOCALE, pojo.getLocale())
                .set(CONTEST_TYPE_ATTRIBUTE.STRING_VALUE, pojo.getStringValue())
                .set(CONTEST_TYPE_ATTRIBUTE.NUMERIC_VALUE, pojo.getNumericValue())
                .set(CONTEST_TYPE_ATTRIBUTE.REAL_VALUE, pojo.getRealValue())
                .where(CONTEST_TYPE_ATTRIBUTE.NAME.eq(pojo.getName())
                        .and(CONTEST_TYPE_ATTRIBUTE.ADDITIONAL_ID.eq(pojo.getAdditionalId()))
                        .and(CONTEST_TYPE_ATTRIBUTE.LOCALE.eq(pojo.getLocale())))
                .execute() > 0;
    }

    @Override
    public List<IContestTypeAttribute> list() {
        return dslContext.select()
                .from(CONTEST_TYPE_ATTRIBUTE)
                .fetchInto(ContestTypeAttribute.class);
    }
}
