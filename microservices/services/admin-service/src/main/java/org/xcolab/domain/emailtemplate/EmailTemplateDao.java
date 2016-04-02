package org.xcolab.domain.emailtemplate;


import org.xcolab.model.tables.pojos.ContestEmailTemplate;

import java.util.List;

public interface EmailTemplateDao {

    List<ContestEmailTemplate> listAllEmailTemplates();

    ContestEmailTemplate getEmailTemplate(String emailTemplateId);
}
