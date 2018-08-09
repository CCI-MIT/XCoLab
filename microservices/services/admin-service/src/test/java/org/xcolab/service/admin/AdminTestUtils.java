package org.xcolab.service.admin;

import org.xcolab.model.tables.pojos.ConfigurationAttribute;
import org.xcolab.model.tables.pojos.EmailTemplate;

public class AdminTestUtils {
    public static ConfigurationAttribute getConfigurationAttribute(String nameComplement){
        ConfigurationAttribute ca = new ConfigurationAttribute();
        ca.setName("SUPER" + nameComplement);
        ca.setLocale("");
        ca.setNumericValue(0L);
        ca.setAdditionalId(0L);
        ca.setRealValue(0d);
        ca.setStringValue("DUPER");
        return ca;
    }

    public static EmailTemplate getContestEmailTemplate(){
        EmailTemplate contestEmailTemplate  = new EmailTemplate();
        contestEmailTemplate.setName("TYPE");
        contestEmailTemplate.setFooter(" ");
        contestEmailTemplate.setHeader(" ");
        contestEmailTemplate.setSubject(" ");
        return contestEmailTemplate;
    }
}
