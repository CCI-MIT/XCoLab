package org.xcolab.service.email.domain;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.client.email.pojo.IOutgoingEmail;
import org.xcolab.model.tables.pojos.OutgoingEmail;
import org.xcolab.model.tables.records.OutgoingEmailRecord;
import org.xcolab.service.email.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.OUTGOING_EMAIL;

@Repository
public class OutgoingEmailDaoImpl implements OutgoingEmailDao {

    private final DSLContext dslContext;

    public OutgoingEmailDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public OutgoingEmail create(OutgoingEmail outgoingEmail) {

        OutgoingEmailRecord ret = this.dslContext.insertInto(OUTGOING_EMAIL)
                .set(OUTGOING_EMAIL.ID, outgoingEmail.getId())
                .set(OUTGOING_EMAIL.EMAIL_SUBJECT, outgoingEmail.getEmailSubject())
                .set(OUTGOING_EMAIL.EMAIL_BODY, outgoingEmail.getEmailBody())
                .set(OUTGOING_EMAIL.EMAIL_TO, outgoingEmail.getEmailTo())
                .set(OUTGOING_EMAIL.EMAIL_FROM, outgoingEmail.getEmailFrom())
                .set(OUTGOING_EMAIL.SENT_AT, outgoingEmail.getSentAt())
                .set(OUTGOING_EMAIL.REFERENCE_ID, outgoingEmail.getReferenceId())
                .set(OUTGOING_EMAIL.EMAIL_BODY_HASH, outgoingEmail.getEmailBodyHash())
                .set(OUTGOING_EMAIL.WAS_SENT, outgoingEmail.isWasSent())
                .returning(OUTGOING_EMAIL.ID)
                .fetchOne();
        if (ret != null) {
            outgoingEmail.setId(ret.getValue(OUTGOING_EMAIL.ID));
            return outgoingEmail;
        } else {
            return null;
        }

    }

    @Override
    public OutgoingEmail get(Long colabEmailId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(OUTGOING_EMAIL)
                .where(OUTGOING_EMAIL.ID.eq(colabEmailId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("OutgoingEmail with id " + colabEmailId + " does not exist");
        }
        return record.into(OutgoingEmail.class);

    }

    @Override
    public boolean update(OutgoingEmail outgoingEmail) {
        return dslContext.update(OUTGOING_EMAIL)
                .set(OUTGOING_EMAIL.ID, outgoingEmail.getId())
                .set(OUTGOING_EMAIL.EMAIL_SUBJECT, outgoingEmail.getEmailSubject())
                .set(OUTGOING_EMAIL.EMAIL_BODY, outgoingEmail.getEmailBody())
                .set(OUTGOING_EMAIL.EMAIL_TO, outgoingEmail.getEmailTo())
                .set(OUTGOING_EMAIL.EMAIL_FROM, outgoingEmail.getEmailFrom())
                .set(OUTGOING_EMAIL.SENT_AT, outgoingEmail.getSentAt())
                .set(OUTGOING_EMAIL.REFERENCE_ID, outgoingEmail.getReferenceId())
                .set(OUTGOING_EMAIL.EMAIL_BODY_HASH, outgoingEmail.getEmailBodyHash())
                .set(OUTGOING_EMAIL.WAS_SENT, outgoingEmail.isWasSent())
                .where(OUTGOING_EMAIL.ID.eq(outgoingEmail.getId()))
                .execute() > 0;
    }

    @Override
    public List<OutgoingEmail> findByGiven(String emailSubject, String emailTo, Long referenceId, String emailBodyHash) {
        final SelectQuery<Record> query = dslContext.select()
                .from(OUTGOING_EMAIL).getQuery();

        if (emailSubject != null) {
            query.addConditions(OUTGOING_EMAIL.EMAIL_SUBJECT.eq(emailSubject));
        }
        if (emailTo != null) {
            query.addConditions(OUTGOING_EMAIL.EMAIL_TO.eq(emailTo));
        }
        if (referenceId != null) {
            query.addConditions(OUTGOING_EMAIL.REFERENCE_ID.eq(referenceId));
        }
        if (emailBodyHash != null) {
            query.addConditions(OUTGOING_EMAIL.EMAIL_BODY_HASH.eq(emailBodyHash));
        }
        return query.fetchInto(OutgoingEmail.class);
    }


    @Override
    public List<IOutgoingEmail> getSentEmails(int numOfEmails) {
        final SelectQuery<Record> query = dslContext.select()
                .from(OUTGOING_EMAIL).getQuery();

        query.addOrderBy(OUTGOING_EMAIL.SENT_AT.desc());
        query.addLimit(numOfEmails);
        return query.fetchInto(IOutgoingEmail.class);
    }
}
