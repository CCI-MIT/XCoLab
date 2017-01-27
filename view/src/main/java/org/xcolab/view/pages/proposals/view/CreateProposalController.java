package org.xcolab.view.pages.proposals.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.entity.utils.analytics.AnalyticsUtil;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.view.pages.proposals.utils.edit.ProposalUpdateHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CreateProposalController extends BaseProposalsController {
    
    private final ProposalsContext proposalsContext;

    @Autowired
    public CreateProposalController(ProposalsContext proposalsContext) {
        this.proposalsContext = proposalsContext;
    }

    @GetMapping("/contests/{contestYear}/{contestUrlName}/createProposal/basedOn/{baseProposalId}/{baseProposalVersion}/{baseContestId}")
    public String createProposalsBasedOn(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String contestUrlName, @PathVariable(required = false) Long baseProposalId,
            @PathVariable(required = false) Integer
                    baseProposalVersion,
            @PathVariable(required = false) Long baseContestId, Model model)
            throws ProposalsAuthorizationException {

        return showContestProposals( request,  response,
                contestUrlName,   baseProposalId,
                (baseProposalVersion!=null)?(baseProposalVersion): (-1),
                baseContestId, model);
    }

    @GetMapping("/contests/{contestYear}/{contestUrlName}/createProposal")
    public String createProposals(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String contestUrlName, @PathVariable(required = false) Long baseProposalId,
            @PathVariable(required = false) Integer baseProposalVersion,
            @PathVariable(required = false) Long baseContestId, Model model)
            throws ProposalsAuthorizationException {

        if(baseProposalVersion == null){
            baseProposalVersion = -1;
        }
        return showContestProposals( request,  response,
                 contestUrlName,   baseProposalId,
         baseProposalVersion,
         baseContestId, model);
    }

    public String showContestProposals(HttpServletRequest request, HttpServletResponse response,
                String contestUrlName,  Long baseProposalId,
         int baseProposalVersion,
         Long baseContestId, Model model)
        throws ProposalsAuthorizationException {
        if (!proposalsContext.getPermissions(request).getCanCreate()) {
            throw new ProposalsAuthorizationException("creation not allowed");
        }

        long memberId = MemberAuthUtil.getMemberId(request);

        final Contest contest = proposalsContext.getContest(request);
        Proposal proposal;

        if(contest.getIsSharedContestInForeignColab()){
            RestService proposalService = new RefreshingRestService(CoLabService.PROPOSAL,
                    ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                    ConfigurationAttributeKey.PARTNER_COLAB_PORT);
            proposal = new Proposal(proposalService);
        }else{
            proposal = new Proposal();
        }


        proposal.setProposalId(0L);
        proposal.setCurrentVersion(0);
        proposal.setVisible(true);
        proposal.setAuthorId(memberId);


        final ContestPhase contestPhase = proposalsContext.getContestPhase(request);

        Proposal proposalWrapped = new Proposal(proposal, 0, contest, contestPhase, null);
        if (baseProposalId != null && baseProposalId > 0) {
            try {
                Contest baseContest = ProposalsContextUtil.getClients(request).getContestClient().getContest(baseContestId);
                Proposal baseProposalWrapper = new Proposal(
                        ProposalsContextUtil.getClients(request).getProposalClient().getProposal(baseProposalId),
                        baseProposalVersion, baseContest, ContestClientUtil.getActivePhase(baseContest.getContestPK()), null);


                model.addAttribute("baseProposal", baseProposalWrapper);

                model.addAttribute("baseContest", baseContest);

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

        AnalyticsUtil.publishEvent(request, memberId, ProposalUpdateHelper.PROPOSAL_ANALYTICS_KEY + 1,
                ProposalUpdateHelper.PROPOSAL_ANALYTICS_CATEGORY,
                ProposalUpdateHelper.PROPOSAL_ANALYTICS_ACTION ,
                ProposalUpdateHelper.PROPOSAL_ANALYTICS_LABEL,
    			1);

        request.setAttribute("imageUploadServiceUrl",
                ConfigurationAttributeKey.IMAGE_UPLOAD_EXTERNAL_SERVICE_URL.get());
        request.setAttribute("imageUploadHelpText", ConfigurationAttributeKey.IMAGE_UPLOAD_HELP_TEXT.get());
        return "/proposals/proposalDetails_edit";
    }
}
