package org.climatecollaboratorium.plans.wrappers;

import java.io.Serializable;

import com.ext.portlet.model.PlanFan;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;

public class PlanFanWrapper implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlanFan wrapped;
    
    public PlanFanWrapper(PlanFan wrapped) {
        this.wrapped = wrapped;
    }
    
    public long getUserId() {
        return wrapped.getUserId();
    }
    public String getScreenName() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(wrapped.getUserId()).getScreenName();
    }
    
    
}
