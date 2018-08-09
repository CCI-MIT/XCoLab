package org.xcolab.service.admin.domain.emailtemplate;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.EmailTemplate;

import java.util.List;

import static org.xcolab.model.Tables.EMAIL_TEMPLATE;

@Repository
public class EmailTemplateDaoImpl implements EmailTemplateDao {
    @Autowired
    private DSLContext dslContext;

    @Override
    public List<EmailTemplate> listAllEmailTemplates() {
        return this.dslContext.select()
                .from(EMAIL_TEMPLATE)
                .fetchInto(EmailTemplate.class);
    }

    @Override
    public EmailTemplate getEmailTemplate(String emailTemplateId) {
        final Record record = this.dslContext.select()
                .from(EMAIL_TEMPLATE)
                .where(EMAIL_TEMPLATE.NAME.equal(emailTemplateId))
                .fetchOne();
        if (record == null) {
            return null;
        }
        return record.into(EmailTemplate.class);
    }
    @Override
    public boolean updateEmailTemplate(EmailTemplate contestEmailTemplate) {
         return dslContext.update(EMAIL_TEMPLATE)
                .set(EMAIL_TEMPLATE.NAME, contestEmailTemplate.getName())
                .set(EMAIL_TEMPLATE.SUBJECT, contestEmailTemplate.getSubject())
                .set(EMAIL_TEMPLATE.HEADER, contestEmailTemplate.getHeader())
                .set(EMAIL_TEMPLATE.FOOTER, contestEmailTemplate.getFooter())
                .where(EMAIL_TEMPLATE.NAME.equal(contestEmailTemplate.getName()))
                .execute() > 0;
    }
    @Override
    public void createEmailTemplate(EmailTemplate contestEmailTemplate) {
        this.dslContext.insertInto(EMAIL_TEMPLATE)
                .set(EMAIL_TEMPLATE.NAME, contestEmailTemplate.getName())
                .set(EMAIL_TEMPLATE.SUBJECT, contestEmailTemplate.getSubject())
                .set(EMAIL_TEMPLATE.HEADER, contestEmailTemplate.getHeader())
                .set(EMAIL_TEMPLATE.FOOTER, contestEmailTemplate.getFooter())
                .execute();
    }

}
