package org.xcolab.portlets.contests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.theme.ThemeDisplay;

public class ContestsBean implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<ContestWrapper> getContests() throws SystemException, PortalException {
        List<ContestWrapper> ret = new ArrayList<>();
        
        for (Contest contest: ContestLocalServiceUtil.findByActiveFlagText(true, "Open")) {
            ret.add(new ContestWrapper(contest));
        }
        return ret;
    }
    
    public ThemeDisplay getThemeDisplay() {
        return Helper.getThemeDisplay();
    }
    
}
