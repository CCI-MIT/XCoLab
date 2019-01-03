package org.xcolab.service.admin.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.model.tables.pojos.EmailTemplate;
import org.xcolab.service.admin.AdminTestUtils;
import org.xcolab.service.admin.domain.emailtemplate.EmailTemplateDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@OverrideAutoConfiguration(enabled = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("org.xcolab.service.admin")
@ActiveProfiles("test")
public class EmailTemplateDaoTest {

    @Autowired
    EmailTemplateDao emailTemplateDao;

    @Test
    public void shouldSaveNewContestEmailTemplate(){

        EmailTemplate cet = AdminTestUtils.getContestEmailTemplate();
        emailTemplateDao.createEmailTemplate(cet);

        assertNotNull(emailTemplateDao.getEmailTemplate(cet.getName()));

    }

    @Test
    public void shouldBeEmptyOnNotFoundEmailTemplate(){

        assertNull(emailTemplateDao.getEmailTemplate("b"));
    }
    @Test
    public void shouldUpdateEmailTemplate(){
        String newStr = "DUPER2";

        EmailTemplate cet = AdminTestUtils.getContestEmailTemplate();
        cet.setName("NEWTYPEEMAILUPDATE");
        emailTemplateDao.createEmailTemplate(cet);

        cet.setSubject(newStr);
        assertTrue(emailTemplateDao.updateEmailTemplate(cet));

        cet = emailTemplateDao.getEmailTemplate(cet.getName());

        assertNotNull(cet);
        assertEquals(cet.getSubject(),newStr);

    }

}
