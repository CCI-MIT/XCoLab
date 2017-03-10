package org.xcolab.view.pages.proposals.view.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.util.entity.analytics.AnalyticsUtil;
import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.view.pages.proposals.utils.edit.ProposalUpdateHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class CreateProposalController extends BaseProposalsController {
    
    private final ProposalsContext proposalsContext;

    @Autowired
    public CreateProposalController(ProposalsContext proposalsContext) {
        this.proposalsContext = proposalsContext;
    }

    @GetMapping("createProposal/basedOn/{baseProposalId}/{baseProposalVersion}/{baseContestId}")
    public String createProposalsBasedOn(HttpServletRequest request, HttpServletResponse response,
            Model model, Member loggedInMember, @PathVariable Long baseProposalId,
            @PathVariable Integer baseProposalVersion, @PathVariable Long baseContestId) {

        return showCreateProposal(request, response, model, loggedInMember, baseProposalId,
                baseProposalVersion, baseContestId);
    }

    @GetMapping("createProposal")
    public String showCreateProposal(HttpServletRequest request, HttpServletResponse response,
            Model model, Member loggedInMember) {

        return showCreateProposal(request, response, model, loggedInMember, null, -1, null);
    }

    private String showCreateProposal(HttpServletRequest request, HttpServletResponse response,
            Model model, Member loggedInMember, Long baseProposalId,
            int baseProposalVersion, Long baseContestId) {

        if (!proposalsContext.getPermissions(request).getCanCreate()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        long memberId = loggedInMember.getId_();

        final ClientHelper clients = ProposalsContextUtil.getClients(request);
        final ContestClient contestClient = clients.getContestClient();
        final ProposalClient proposalClient = clients.getProposalClient();

        final Contest contest = proposalsContext.getContest(request);
        Proposal proposal;

        if (contest.getIsSharedContestInForeignColab()) {
            RestService proposalService = new RefreshingRestService(CoLabService.PROPOSAL,
                    ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                    ConfigurationAttributeKey.PARTNER_COLAB_PORT);
            proposal = new Proposal(proposalService);
        } else {
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
                Contest baseContest = contestClient.getContest(baseContestId);
                Proposal baseProposalWrapper = new Proposal(
                        proposalClient.getProposal(baseProposalId),
                        baseProposalVersion, baseContest, contestClient.getActivePhase(baseContest.getContestPK()), null);

                model.addAttribute("baseProposal", baseProposalWrapper);

                model.addAttribute("baseContest", baseContest);

                if (!model.containsAttribute("updateProposalDetailsBean")) {
                    UpdateProposalDetailsBean updateProposalDetailsBean =
                            new UpdateProposalDetailsBean(proposalWrapped, baseProposalWrapper);
                    model.addAttribute("updateProposalDetailsBean", updateProposalDetailsBean);
                }
            } catch (ContestNotFoundException | ProposalNotFoundException ignored) {
                
            }
        } else if (!model.containsAttribute("updateProposalDetailsBean")) {
            UpdateProposalDetailsBean updateProposalDetailsBean =
                    new UpdateProposalDetailsBean(proposalWrapped);
            model.addAttribute("updateProposalDetailsBean", updateProposalDetailsBean);
        }

        model.addAttribute("mustFilterContent",ConfigurationAttributeKey.FILTER_PROFANITY.get());
        model.addAttribute("proposal", proposalWrapped);

        model.addAttribute("isEditingProposal", true);
        ContestType contestType = ProposalsContextUtil.getContestType(request);
        final String seoText = "Create " + contestType.getProposalName() + " in " + contest.getContestShortName();
        setSeoTexts(request, seoText, null, null);

        AnalyticsUtil.publishEvent(request, memberId, ProposalUpdateHelper.PROPOSAL_ANALYTICS_KEY + 1,
                ProposalUpdateHelper.PROPOSAL_ANALYTICS_CATEGORY,
                ProposalUpdateHelper.PROPOSAL_ANALYTICS_ACTION,
                ProposalUpdateHelper.PROPOSAL_ANALYTICS_LABEL,
    			1);

        request.setAttribute("imageUploadServiceUrl",
                ConfigurationAttributeKey.IMAGE_UPLOAD_EXTERNAL_SERVICE_URL.get());
        request.setAttribute("imageUploadHelpText", ConfigurationAttributeKey.IMAGE_UPLOAD_HELP_TEXT.get());
        return "proposals/proposalDetails_edit";
    }

    @PostMapping("createProposal")
    public String createProposal(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable String contestYear, @PathVariable String contestUrlName,
            @Valid UpdateProposalDetailsBean updateProposalDetailsBean, BindingResult result) {

        Proposal proposal = proposalsContext.getProposal(request);
        final ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        if (!permissions.getCanCreate()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        if (result.hasErrors()) {
            AlertMessage.danger(
                    "Proposal NOT created. Please fix the errors before saving.")
                    .flash(request);
            final Member memberOrNull = MemberAuthUtil.getMemberOrNull(request);
            return showCreateProposal(request, response, model, memberOrNull);
        }

        return AddUpdateProposalControllerUtil
                .createOrUpdateProposal(request, updateProposalDetailsBean, proposal, proposalsContext);
    }
}
