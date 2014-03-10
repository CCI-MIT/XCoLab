package org.xcolab.portlets.contests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
    private static final int NUM_CONTESTS = 4;
	
	public List<ContestWrapper> getContests() throws SystemException, PortalException {
        List<ContestWrapper> ret = new ArrayList<>();

        List<Contest> contests = ContestLocalServiceUtil.findByActiveFlagText(true, "");
        Collections.shuffle(contests);

        for (Contest contest: contests) {
            if(ret.size() < NUM_CONTESTS)
                ret.add(new ContestWrapper(contest));
            else break;
        }
        return ret;
    }
    
    public ThemeDisplay getThemeDisplay() {
        return Helper.getThemeDisplay();
    }
    
}
