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

import org.xcolab.client.admin.pojo.IConfigurationAttribute;
import org.xcolab.service.admin.AdminTestUtils;
import org.xcolab.service.admin.domain.configurationattribute.ConfigurationAttributeDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@OverrideAutoConfiguration(enabled = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan("org.xcolab.service.admin")
@ActiveProfiles("test")
public class ConfigurationAttributeDaoTest {

    @Autowired
    private ConfigurationAttributeDao configurationAttributeDao;


    @Test
    public void shouldSaveNewConfigurationAttribute(){

        IConfigurationAttribute ca = configurationAttributeDao.create(AdminTestUtils.getConfigurationAttribute("a"));

        assertNotNull(configurationAttributeDao.getConfigurationAttribute(ca.getName(), null).get());

    }

    @Test
    public void shouldBeEmptyOnNotFoundConfigurationAttribute(){

        assertFalse(configurationAttributeDao.getConfigurationAttribute("b", null).isPresent());
    }
    @Test
    public void shouldUpdateNewConfigurationAttribute(){
        String newStr = "DUPER2";

        IConfigurationAttribute ca = configurationAttributeDao.create(AdminTestUtils.getConfigurationAttribute("b"));

        ca.setStringValue(newStr);
        assertTrue(configurationAttributeDao.update(ca));

        ca = configurationAttributeDao.getConfigurationAttribute(
            ca.getName(), null).get();

        assertNotNull(ca);
        assertEquals(ca.getStringValue(),newStr);

    }
}
