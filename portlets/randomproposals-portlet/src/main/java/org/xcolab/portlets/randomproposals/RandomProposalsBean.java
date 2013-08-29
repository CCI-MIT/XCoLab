package org.xcolab.portlets.randomproposals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class RandomProposalsBean {
    
    private static final int MAX_PLANS = 4;
    private PreferencesBean preferencesBean;
    private String baseImagePath;
    
    public RandomProposalsBean() {
        setBaseImagePath(Helper.getThemeDisplay().getPathImage() + "/proposal?img_id=");
    }
    
    public List<PlanItemWrapper> getProposals() throws PortalException, SystemException {
        
        List<PlanItem> availablePlans = new ArrayList<>();
        List<PlanItemWrapper> ret = new ArrayList<>();
        
        Long[] selectedPhases = preferencesBean.getSelectedPhases();
        if (selectedPhases == null) return null;
        
        for (Long contestPhasePk: selectedPhases) {
            availablePlans.addAll(ContestPhaseLocalServiceUtil.getPlans(ContestPhaseLocalServiceUtil.getContestPhase(contestPhasePk)));
        }
        
        Collections.shuffle(availablePlans);
        for (int i=0; i < availablePlans.size() && i < MAX_PLANS; i++) {
            ret.add(new PlanItemWrapper(availablePlans.get(i)));
        }
        
        return ret;
    }

    public PreferencesBean getPreferencesBean() {
        return preferencesBean;
    }

    public void setPreferencesBean(PreferencesBean preferencesBean) {
        this.preferencesBean = preferencesBean;
    }

    public String getBaseImagePath() {
        return baseImagePath;
    }

    public void setBaseImagePath(String baseImagePath) {
        this.baseImagePath = baseImagePath;
    }

}
