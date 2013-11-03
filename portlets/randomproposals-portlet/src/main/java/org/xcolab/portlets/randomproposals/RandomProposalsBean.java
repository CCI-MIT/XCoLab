package org.xcolab.portlets.randomproposals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class RandomProposalsBean {
    
    private PreferencesBean preferencesBean;
    private String baseImagePath;
    
    public RandomProposalsBean() {
        setBaseImagePath(Helper.getThemeDisplay().getPathImage() + "/proposal?img_id=");
    }
    
    public List<ProposalWrapper> getProposals() throws PortalException, SystemException {
        
        List<Proposal> availablePlans = new ArrayList<>();
        List<ProposalWrapper> ret = new ArrayList<>();
        
        Long[] selectedPhases = preferencesBean.getSelectedPhases();
        if (selectedPhases == null) return null;
        Long[] flagFilters = preferencesBean.getFlagFilters();

        if (flagFilters == null || flagFilters.length == 0) {
            for (Long contestPhasePk: selectedPhases) {
                    availablePlans.addAll(ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhasePk));
                }
        }
        else {
            for (Long contestPhasePk: selectedPhases) {
                for (Proposal p: ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhasePk)) {
                    if (getRibbonType(p) == null || getRibbonType(p).getRibbon() == 0) continue;
                    int ribbon = getRibbonType(p).getRibbon();
                    for (Long flag: flagFilters) {
                        if (ribbon == flag.intValue()) {
                            availablePlans.add(p);
                            break;
                        }
                    }
                }
            }
        }
        
        Collections.shuffle(availablePlans);
        for (int i=0; i < availablePlans.size() && i < preferencesBean.getFeedSize(); i++) {
            ret.add(new ProposalWrapper(availablePlans.get(i)));
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

    private ContestPhaseRibbonType getRibbonType(Proposal p) throws PortalException, SystemException {
       ContestPhaseRibbonType contestPhaseRibbonType = null;
       List<Long> phasesForProposal =  Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(p.getProposalId());
       ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(phasesForProposal.get(phasesForProposal.size()-1));
            try {
                long typeId = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(p.getProposalId(),
                        contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.RIBBON).getNumericValue();
                contestPhaseRibbonType = ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonType(typeId);
            }
            catch (NoSuchProposalContestPhaseAttributeException e) {
                // ignore
            }

        return contestPhaseRibbonType;
    }

}
