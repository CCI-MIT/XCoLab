package org.xcolab.domain.emailtemplate;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ContestEmailTemplate;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST_EMAIL_TEMPLATE;

@Repository
public class EmailTemplateDaoImpl implements EmailTemplateDao {
    @Autowired
    private DSLContext dslContext;

    @Override
    public List<ContestEmailTemplate> listAllEmailTemplates() {
        return this.dslContext.select()
                .from(CONTEST_EMAIL_TEMPLATE)
                .fetchInto(ContestEmailTemplate.class);
    }

    @Override
    public ContestEmailTemplate getEmailTemplate(String emailTemplateId) {
        return this.dslContext.select()
                .from(CONTEST_EMAIL_TEMPLATE)
                .where(CONTEST_EMAIL_TEMPLATE.TYPE_.equal(emailTemplateId))
                .fetchOneInto(ContestEmailTemplate.class);
    }
    @Override
    public void updateEmailTemplate(ContestEmailTemplate contestEmailTemplate) {
         this.dslContext.update(CONTEST_EMAIL_TEMPLATE)
                .set(CONTEST_EMAIL_TEMPLATE.TYPE_, contestEmailTemplate.getType_())
                .set(CONTEST_EMAIL_TEMPLATE.SUBJECT, contestEmailTemplate.getSubject())
                .set(CONTEST_EMAIL_TEMPLATE.HEADER, contestEmailTemplate.getHeader())
                .set(CONTEST_EMAIL_TEMPLATE.FOOTER, contestEmailTemplate.getFooter())
                .where(CONTEST_EMAIL_TEMPLATE.TYPE_.equal(contestEmailTemplate.getType_()))
                .execute();
    }
    @Override
    public void createEmailTemplate(ContestEmailTemplate contestEmailTemplate) {
        this.dslContext.insertInto(CONTEST_EMAIL_TEMPLATE)
                .set(CONTEST_EMAIL_TEMPLATE.TYPE_, contestEmailTemplate.getType_())
                .set(CONTEST_EMAIL_TEMPLATE.SUBJECT, contestEmailTemplate.getSubject())
                .set(CONTEST_EMAIL_TEMPLATE.HEADER, contestEmailTemplate.getHeader())
                .set(CONTEST_EMAIL_TEMPLATE.FOOTER, contestEmailTemplate.getFooter())
                .execute();
    }

}
