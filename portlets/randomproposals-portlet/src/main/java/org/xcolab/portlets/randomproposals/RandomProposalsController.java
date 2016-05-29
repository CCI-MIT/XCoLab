package org.xcolab.portlets.randomproposals;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("view")
public class RandomProposalsController {
    
	private RandomProposalsPreferences _preferences;

	 private static final Object mutex = new Object();
	 private static List<Proposal> availableProposals;
	
    public RandomProposalsController() {
    }

    @RequestMapping
    public String showRandomProposals(PortletRequest request, PortletResponse response, Model model) throws SystemException, PortalException {
    	
    	_preferences = new RandomProposalsPreferences(request);
    	
    	ProposalsModel proposalsModel = new ProposalsModel(getProposals(request), _preferences
    			, Helper.getThemeDisplay(request).getPathImage() + "/proposal?img_id=");
    	
    	model.addAttribute("proposalsModel", proposalsModel);
              	
    	return "showProposals";
    }
  
    private List<ProposalWrapper> getProposals(PortletRequest request) throws PortalException,
	SystemException {

    	reset();
    	
		List<ProposalWrapper> ret = new ArrayList<>();
		List<Proposal> proposals = getAvailableProposals(_preferences);
		
		Collections.shuffle(proposals);
		for (int i = 0; i < proposals.size()
				&& i < _preferences.getFeedSize(); i++) {
			ret.add(new ProposalWrapper(proposals.get(i)));
		}
		
		return ret;
	}

	private static ContestPhaseRibbonType getRibbonType(Proposal p, Long contestPhasePK)
			throws PortalException, SystemException {
		ContestPhaseRibbonType contestPhaseRibbonType = null;
		try {
			long typeId = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(p.getProposalId(),contestPhasePK,ProposalContestPhaseAttributeKeys.RIBBON).getNumericValue();
			contestPhaseRibbonType = ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonType(typeId);
		} catch (NoSuchProposalContestPhaseAttributeException e) {
			// ignore
		}

		return contestPhaseRibbonType;
	}

	private static List<Proposal> getAvailableProposals(RandomProposalsPreferences preferences)
			throws PortalException, SystemException {
		synchronized (mutex) {
			if (availableProposals == null) {
				availableProposals = new ArrayList<>();
				Long[] selectedPhases = preferences.getSelectedPhases();
				if (selectedPhases == null) {
					return null;
				}
				Long[] flagFilters = preferences.getFlagFilters();

				if (flagFilters == null || flagFilters.length == 0) {
					for (Long contestPhasePk : selectedPhases) {
						availableProposals.addAll(ProposalLocalServiceUtil
								.getProposalsInContestPhase(contestPhasePk));
					}
				} else {
					for (Long contestPhasePk : selectedPhases) {
						for (Proposal p : ProposalLocalServiceUtil
								.getProposalsInContestPhase(contestPhasePk)) {
							if (getRibbonType(p, contestPhasePk) == null
									|| getRibbonType(p, contestPhasePk).getRibbon() == 0) {
								continue;
							}
							int ribbon = getRibbonType(p, contestPhasePk).getRibbon();
							for (Long flag : flagFilters) {
								if (ribbon == flag.intValue()) {
									availableProposals.add(p);
									break;
								}
							}
						}
					}
				}
			}
			return new ArrayList<>(availableProposals);
		}
	}	

	public static void reset() {
		synchronized (mutex) {
			availableProposals = null;
		}

	}
    

}