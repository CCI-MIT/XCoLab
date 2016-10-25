package org.xcolab.portlets.proposals.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.portlets.proposals.utils.edit.ProposalUpdateHelper;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Controller
@RequestMapping("view")
public class CreateProposalController extends BaseProposalsController {
    
    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = "pageToDisplay=createProposal")
    public String showContestProposals(RenderRequest request, RenderResponse response, 
            @RequestParam String contestUrlName, @RequestParam(required = false) Long baseProposalId,
            @RequestParam(required = false, defaultValue = "-1") int baseProposalVersion,
            @RequestParam(required = false) Long baseContestId, Model model)
            throws PortalException, SystemException, ProposalsAuthorizationException {

        if (!proposalsContext.getPermissions(request).getCanCreate()) {
            throw new ProposalsAuthorizationException("creation not allowed");
        }

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();
        
        Proposal proposal = new Proposal();
        proposal.setProposalId(0L);
        proposal.setCurrentVersion(0);
        proposal.setVisible(true);
        proposal.setAuthorId(themeDisplay.getUserId());

        final Contest contest = proposalsContext.getContest(request);
        final ContestPhase contestPhase = proposalsContext.getContestPhase(request);

        ProposalWrapper proposalWrapped = new ProposalWrapper(proposal, 0, contest, contestPhase, null);
        if (baseProposalId != null && baseProposalId > 0) {
            try {
                Contest baseContest = ContestClientUtil.getContest(baseContestId);
                ProposalWrapper baseProposalWrapper = new ProposalWrapper(
                        ProposalsContextUtil.getClients(request).getProposalClient().getProposal(baseProposalId),
                        baseProposalVersion, baseContest, ContestClientUtil.getActivePhase(baseContest.getContestPK()), null);


                model.addAttribute("baseProposal", baseProposalWrapper);

                model.addAttribute("baseContest", new ContestWrapper(baseContest));

                model.addAttribute("updateProposalSectionsBean", new UpdateProposalDetailsBean(proposalWrapped, baseProposalWrapper));
            }catch (ContestNotFoundException | ProposalNotFoundException ignored){
                
            }
        }
        else {
        	model.addAttribute("updateProposalSectionsBean", new UpdateProposalDetailsBean(proposalWrapped));
        }
        model.addAttribute("mustFilterContent",ConfigurationAttributeKey.FILTER_PROFANITY.get());
        model.addAttribute("proposal", proposalWrapped);

        model.addAttribute("isEditingProposal", true);
        ContestType contestType = ContestClientUtil.getContestType(contest.getContestTypeId());
        final String seoText = "Create " + contestType.getProposalName() + " in " + contest.getContestShortName();
        setSeoTexts(request, seoText, null, null);

        AnalyticsUtil.publishEvent(request, userId, ProposalUpdateHelper.PROPOSAL_ANALYTICS_KEY + 1,
                ProposalUpdateHelper.PROPOSAL_ANALYTICS_CATEGORY,
                ProposalUpdateHelper.PROPOSAL_ANALYTICS_ACTION ,
                ProposalUpdateHelper.PROPOSAL_ANALYTICS_LABEL,
    			1);

        request.setAttribute("imageUploadServiceUrl",
                ConfigurationAttributeKey.IMAGE_UPLOAD_EXTERNAL_SERVICE_URL.get());
        request.setAttribute("imageUploadHelpText", ConfigurationAttributeKey.IMAGE_UPLOAD_HELP_TEXT.get());
        return "proposalDetails_edit";
    }
}
