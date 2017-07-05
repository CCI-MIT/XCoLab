package org.xcolab.service.emails.domain;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.ColabEmail;
import org.xcolab.model.tables.records.ColabEmailRecord;
import org.xcolab.service.emails.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.COLAB_EMAIL;

@Repository
public class ColabEmailDaoImpl implements ColabEmailDao {

    private final DSLContext dslContext;

    public ColabEmailDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public ColabEmail create(ColabEmail colabEmail) {

        ColabEmailRecord ret = this.dslContext.insertInto(COLAB_EMAIL)
                .set(COLAB_EMAIL.COLAB_EMAIL_ID, colabEmail.getColabEmailId())
                .set(COLAB_EMAIL.EMAIL_SUBJECT, colabEmail.getEmailSubject())
                .set(COLAB_EMAIL.EMAIL_BODY, colabEmail.getEmailBody())
                .set(COLAB_EMAIL.EMAIL_TO, colabEmail.getEmailTo())
                .set(COLAB_EMAIL.EMAIL_FROM, colabEmail.getEmailFrom())
                .set(COLAB_EMAIL.DATE_SENT, colabEmail.getDateSent())
                .set(COLAB_EMAIL.REFERENCE_ID, colabEmail.getReferenceId())
                .set(COLAB_EMAIL.EMAIL_BODY_HASH, colabEmail.getEmailBodyHash())
                .set(COLAB_EMAIL.SENT, colabEmail.getSent())
                .returning(COLAB_EMAIL.COLAB_EMAIL_ID)
                .fetchOne();
        if (ret != null) {
            colabEmail.setColabEmailId(ret.getValue(COLAB_EMAIL.COLAB_EMAIL_ID));
            return colabEmail;
        } else {
            return null;
        }

    }

    @Override
    public ColabEmail get(Long colabEmailId) throws NotFoundException{

        final Record record =  this.dslContext.selectFrom(COLAB_EMAIL)
                .where(COLAB_EMAIL.COLAB_EMAIL_ID.eq(colabEmailId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ColabEmail with id " + colabEmailId + " does not exist");
        }
        return record.into(ColabEmail.class);

    }

    @Override
    public boolean update(ColabEmail colabEmail) {
        return dslContext.update(COLAB_EMAIL)
                .set(COLAB_EMAIL.COLAB_EMAIL_ID, colabEmail.getColabEmailId())
                .set(COLAB_EMAIL.EMAIL_SUBJECT, colabEmail.getEmailSubject())
                .set(COLAB_EMAIL.EMAIL_BODY, colabEmail.getEmailBody())
                .set(COLAB_EMAIL.EMAIL_TO, colabEmail.getEmailTo())
                .set(COLAB_EMAIL.EMAIL_FROM, colabEmail.getEmailFrom())
                .set(COLAB_EMAIL.DATE_SENT, colabEmail.getDateSent())
                .set(COLAB_EMAIL.REFERENCE_ID, colabEmail.getReferenceId())
                .set(COLAB_EMAIL.EMAIL_BODY_HASH, colabEmail.getEmailBodyHash())
                .set(COLAB_EMAIL.SENT, colabEmail.getSent())
                .where(COLAB_EMAIL.COLAB_EMAIL_ID.eq(colabEmail.getColabEmailId()))
                .execute() > 0;
    }

    @Override
    public List<ColabEmail> findByGiven(String emailSubject, String emailTo, Long referenceId, String emailBodyHash) {
        final SelectQuery<Record> query = dslContext.select()
                .from(COLAB_EMAIL).getQuery();

        if (emailSubject != null) {
            query.addConditions(COLAB_EMAIL.EMAIL_SUBJECT.eq(emailSubject));
        }
        if (emailTo != null) {
            query.addConditions(COLAB_EMAIL.EMAIL_TO.eq(emailTo));
        }
        if (referenceId != null) {
            query.addConditions(COLAB_EMAIL.REFERENCE_ID.eq(referenceId));
        }
        if (emailBodyHash != null) {
            query.addConditions(COLAB_EMAIL.EMAIL_BODY_HASH.eq(emailBodyHash));
        }
        return query.fetchInto(ColabEmail.class);
    }





}
