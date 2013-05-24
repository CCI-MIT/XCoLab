package org.climatecollaboratorium.plans;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.ext.portlet.model.PlansUserSettings;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.service.PlansUserSettingsLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class ColumnsBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Columns wrapped;
    private boolean visible;
    private PlansIndexBean plansIndexBean;

    public ColumnsBean(Columns wrapped, PlansIndexBean plansIndex) throws PortalException, SystemException {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        //PlansUserSettings plansUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(ectx.getSessionMap(), ectx.getRequestMap(), plansIndex.getContestPhase().getPlanType());
        //visible = wrapped.getUserSetting(plansUserSettings);
        this.wrapped = wrapped;
        this.plansIndexBean = plansIndex;

    }

    public String getName() {
        return wrapped.getName();
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Columns getWrapped() {
        return wrapped;
    }
}