package org.xcolab.service.admin.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.model.tables.pojos.ConfigurationAttribute;
import org.xcolab.model.tables.pojos.ContestEmailTemplate;
import org.xcolab.service.admin.AdminTestUtils;
import org.xcolab.service.admin.domain.configurationattribute.ConfigurationAttributeDao;
import org.xcolab.service.admin.domain.emailtemplate.EmailTemplateDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(
    properties = {
        "cache.active=false",
        "eureka.client.enabled=false"
    }
)
@ComponentScan("org.xcolab.service.admin")
public class EmailTemplateDaoTest {

    @Autowired
    EmailTemplateDao emailTemplateDao;

    @Test
    public void shouldSaveNewContestEmailTemplate(){

        ContestEmailTemplate cet = AdminTestUtils.getContestEmailTemplate();
        emailTemplateDao.createEmailTemplate(cet);

        assertNotNull(emailTemplateDao.getEmailTemplate(cet.getType_()));

    }

    @Test
    public void shouldBeEmptyOnNotFoundEmailTemplate(){

        assertNull(emailTemplateDao.getEmailTemplate("b"));
    }
    @Test
    public void shouldUpdateEmailTemplate(){
        String newStr = "DUPER2";

        ContestEmailTemplate cet = AdminTestUtils.getContestEmailTemplate();
        cet.setType_("NEWTYPEEMAILUPDATE");
        emailTemplateDao.createEmailTemplate(cet);

        cet.setSubject(newStr);
        assertTrue(emailTemplateDao.updateEmailTemplate(cet));

        cet = emailTemplateDao.getEmailTemplate(cet.getType_());

        assertNotNull(cet);
        assertEquals(cet.getSubject(),newStr);

    }

}
