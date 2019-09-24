package org.xcolab.service.admin;

import org.xcolab.client.admin.pojo.IConfigurationAttribute;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.admin.pojo.tables.pojos.ConfigurationAttribute;
import org.xcolab.client.admin.pojo.tables.pojos.EmailTemplate;

public class AdminTestUtils {

    public static IConfigurationAttribute getConfigurationAttribute(String nameComplement) {
        IConfigurationAttribute ca = new ConfigurationAttribute();
        ca.setName("SUPER" + nameComplement);
        ca.setLocale("");
        ca.setNumericValue(0L);
        ca.setAdditionalId(0L);
        ca.setRealValue(0d);
        ca.setStringValue("DUPER");
        return ca;
    }

    public static IEmailTemplate getContestEmailTemplate() {
        IEmailTemplate contestEmailTemplate = new EmailTemplate();
        contestEmailTemplate.setName("TYPE");
        contestEmailTemplate.setFooter(" ");
        contestEmailTemplate.setHeader(" ");
        contestEmailTemplate.setSubject(" ");
        return contestEmailTemplate;
    }
}
