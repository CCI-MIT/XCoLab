package org.xcolab.portlets.randomproposals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class RandomProposalsBean {
    
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
        Long[] flagFilters = preferencesBean.getFlagFilters();

        if (flagFilters == null || flagFilters.length == 0) {
            for (Long contestPhasePk: selectedPhases) {
                    availablePlans.addAll(ContestPhaseLocalServiceUtil.getPlans(ContestPhaseLocalServiceUtil.getContestPhase(contestPhasePk)));
                }
        }
        else {
            for (Long contestPhasePk: selectedPhases) {
                for (PlanItem plan: ContestPhaseLocalServiceUtil.getPlans(ContestPhaseLocalServiceUtil.getContestPhase(contestPhasePk))) {
                    Integer ribbon = PlanItemLocalServiceUtil.getRibbon(plan);
                    if (ribbon == null) continue;
                    for (Long flag: flagFilters) {
                        if (ribbon == flag.intValue()) {
                            availablePlans.add(plan);
                            break;
                        }
                    }
                }
            }
        }
        
        Collections.shuffle(availablePlans);
        for (int i=0; i < availablePlans.size() && i < preferencesBean.getFeedSize(); i++) {
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
