package org.climatecollaboratorium.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

//import org.apache.log4j.Logger;

import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

public class PlansTool {
    // private static Logger _log = Logger.getLogger(PlansTool.class);
    
    public String updatePlanAttributes(ActionEvent ev) throws SystemException {
        int errorsCount = 0;

        for (PlanItem basePlan : PlanItemLocalServiceUtil.getPlans()) {
            try {
                PlanItemLocalServiceUtil.updateAllAttributes(basePlan);
            }
            catch (Exception e) {
                errorsCount ++;
                //_log.error("Error when updating attributes for plan " + PlanItemLocalServiceUtil.getName(basePlan), e);
            }
        }
        FacesMessage fm = new FacesMessage();
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        fm.setSummary("Update finished with " + errorsCount + " errors");

        FacesContext.getCurrentInstance().addMessage(null, fm);

        return null;
    }

}
