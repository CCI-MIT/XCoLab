package org.xcolab.view.pages.proposals.view.proposal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.PlatformTeamsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.enums.proposal.PlanSectionTypeKeys;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.utils.edit.ProposalUpdateHelper;
import org.xcolab.view.util.entity.analytics.AnalyticsUtil;
import org.xcolab.commons.servlet.flash.AlertMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class CreateProposalController extends BaseProposalsController {

    @GetMapping("createProposal/basedOn/{baseProposalId}/{baseProposalVersion}/{baseContestId}")
    public String createProposalsBasedOn(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, Member loggedInMember,
            @PathVariable Long baseProposalId,
            @PathVariable Integer baseProposalVersion, @PathVariable Long baseContestId) {

        return showCreateProposal(request, response, model, proposalContext, loggedInMember, baseProposalId,
                baseProposalVersion, baseContestId);
    }

    @GetMapping("createProposal")
    public String showCreateProposal(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, Member loggedInMember) {

        return showCreateProposal(request, response, model, proposalContext, loggedInMember, null, -1, null);
    }

    private String showCreateProposal(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, Member loggedInMember, Long baseProposalId,
            int baseProposalVersion, Long baseContestId) {

        if (!proposalContext.getPermissions().getCanCreate()) {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }

        long memberId = loggedInMember.getId_();

        final ClientHelper clients = proposalContext.getClients();
        final ContestClient contestClient = clients.getContestClient();
        final ProposalClient proposalClient = clients.getProposalClient();

        final Contest contest = proposalContext.getContest();
        Proposal proposal;

        if (contest.getIsSharedContestInForeignColab()) {
            ServiceNamespace proposalService = ServiceNamespace.instance(
                    ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE
            );
            proposal = new Proposal(proposalService);
        } else {
            proposal = new Proposal();
        }

        proposal.setProposalId(0L);
        proposal.setVisible(true);
        proposal.setAuthorId(memberId);

        final ContestPhase contestPhase = proposalContext.getContestPhase();

        proposal = new Proposal(proposal, 0, contest, contestPhase, null);
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
                            new UpdateProposalDetailsBean(proposal, baseProposalWrapper);
                    model.addAttribute("updateProposalDetailsBean", updateProposalDetailsBean);
                }
            } catch (ContestNotFoundException | ProposalNotFoundException ignored) {
                
            }
        } else if (!model.containsAttribute("updateProposalDetailsBean")) {
            UpdateProposalDetailsBean updateProposalDetailsBean =
                    new UpdateProposalDetailsBean(proposal);
            model.addAttribute("updateProposalDetailsBean", updateProposalDetailsBean);
        }

        model.addAttribute("proposal", proposal);

        model.addAttribute("isEditingProposal", true);
        model.addAttribute("showProposalEditHelpText",
                ConfigurationAttributeKey.CONTESTS_SHOW_PROPOSAL_EDIT_HELP_TEXT.get());
        model.addAttribute("hasProposalPicker", hasProposalPicker(proposal));

        model.addAttribute("showImageUpload",
                ConfigurationAttributeKey.PROPOSALS_SHOW_IMAGE_UPLOAD.get());
        model.addAttribute("saveButtonText",
                ConfigurationAttributeKey.PROPOSALS_SAVE_BUTTON_TEXT.get());
        model.addAttribute("saveHelpText",
                ConfigurationAttributeKey.PROPOSALS_SAVE_HELP_TEXT.get());
        model.addAttribute("proposalPickerDefaultTabIsContests",
                ConfigurationAttributeKey.PROPOSALS_PICKER_DEFAULT_TAB_CONTESTS.get());
        model.addAttribute("saveUrl", contest.getNewProposalLinkUrl());
        model.addAttribute("userTeams", PlatformTeamsClient.getTeams(loggedInMember));

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

    private boolean hasProposalPicker(Proposal proposal) {
        return proposal.getSections().stream()
                .anyMatch(p -> PlanSectionTypeKeys.PROPOSAL_PICKER_SECTION_TYPES.contains(p.getType()));
    }

    @PostMapping("createProposal")
    public String createProposal(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext,
            @PathVariable String contestYear, @PathVariable String contestUrlName,
            @Valid UpdateProposalDetailsBean updateProposalDetailsBean, BindingResult result) {

        Proposal proposal = proposalContext.getProposal();
        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanCreate()) {
            return new AccessDeniedPage(permissions.getMember()).toViewName(response);
        }

        if (result.hasErrors()) {
            AlertMessage.danger(
                    "Proposal NOT created. Please fix the errors before saving.")
                    .flash(request);
            final Member memberOrNull = MemberAuthUtil.getMemberOrNull(request);
            return showCreateProposal(request, response, model, proposalContext, memberOrNull);
        }

        return AddUpdateProposalControllerUtil
                .createOrUpdateProposal(request, updateProposalDetailsBean, proposal, proposalContext);
    }
}
