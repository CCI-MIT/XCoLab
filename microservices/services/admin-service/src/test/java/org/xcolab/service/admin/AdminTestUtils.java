package org.xcolab.service.admin;

import org.xcolab.model.tables.pojos.ConfigurationAttribute;
import org.xcolab.model.tables.pojos.ContestEmailTemplate;

public class AdminTestUtils {
    public static ConfigurationAttribute getConfigurationAttribute(String nameComplement){
        ConfigurationAttribute ca = new ConfigurationAttribute();
        ca.setName("SUPER" + nameComplement);
        ca.setNumericValue(0l);
        ca.setAdditionalId(0l);
        ca.setRealValue(0d);
        ca.setStringValue("DUPER");
        return ca;
    }

    public static ContestEmailTemplate getContestEmailTemplate(){
        ContestEmailTemplate contestEmailTemplate  = new ContestEmailTemplate();
        contestEmailTemplate.setType_("TYPE");
        contestEmailTemplate.setFooter(" ");
        contestEmailTemplate.setHeader(" ");
        contestEmailTemplate.setSubject(" ");
        return contestEmailTemplate;
    }
}
