package org.xcolab.domain.emailtemplate;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.xcolab.model.tables.pojos.ContestEmailTemplate;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST_EMAIL_TEMPLATE;


public class EmailTemplateDaoImpl implements EmailTemplateDao {
    @Autowired
    private DSLContext dslContext;

    @Override
    public List<ContestEmailTemplate> listAllEmailTemplates() {
        return this.dslContext.select()
                .from(CONTEST_EMAIL_TEMPLATE)
                .fetchInto(ContestEmailTemplate.class);
    }

    public ContestEmailTemplate getEmailTemplate(String emailTemplateId) {
        return this.dslContext.select()
                .from(CONTEST_EMAIL_TEMPLATE)
                .where(CONTEST_EMAIL_TEMPLATE.TYPE_.equal(emailTemplateId))
                .fetchOne().into(ContestEmailTemplate.class);
    }

}
