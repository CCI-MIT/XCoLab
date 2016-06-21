package org.xcolab.portlets.randomproposals;

import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.pojo.Proposal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class RandomProposalsController {

    @RequestMapping
    public String showRandomProposals(PortletRequest request, PortletResponse response, Model model)
            throws SystemException, PortalException {

        RandomProposalsPreferences preferences = new RandomProposalsPreferences(request);

    	ProposalsModel proposalsModel = new ProposalsModel(getProposals(preferences), preferences
    			, Helper.getThemeDisplay(request).getPathImage() + "/proposal?img_id=");

    	model.addAttribute("proposalsModel", proposalsModel);

    	return "showProposals";
    }

    private List<ProposalWrapper> getProposals(RandomProposalsPreferences preferences)
            throws SystemException, PortalException {

        List<ProposalWrapper> ret = new ArrayList<>();
		List<Proposal> proposals = getAvailableProposals(preferences);

        if (proposals != null) {
            Collections.shuffle(proposals);
            for (int i = 0; i < proposals.size() && i < preferences.getFeedSize(); ++i) {
                ret.add(new ProposalWrapper(
                        ProposalLocalServiceUtil.getProposal(proposals.get(i).getProposalId())));
            }
        }

		return ret;
	}

	private List<Proposal> getAvailableProposals(RandomProposalsPreferences preferences) {
        List<Proposal> availableProposals = new ArrayList<>();
        Long[] selectedPhases = preferences.getSelectedPhases();
        if (selectedPhases == null) {
            return null;
        }
        Long[] flagFilters = preferences.getFlagFilters();

        for (Long contestPhaseId : selectedPhases) {
            if (flagFilters == null || flagFilters.length == 0) {
                availableProposals
                        .addAll(ProposalsClient.listProposals(0, Integer.MAX_VALUE, null, true,
                        contestPhaseId, null));
            } else {
                for (Long flagFilter : flagFilters) {
                    availableProposals
                            .addAll(ProposalsClient.listProposals(0, Integer.MAX_VALUE, null, true,
                            contestPhaseId, flagFilter.intValue()));
                }
            }
        }
        return availableProposals;
    }
}