package org.xcolab.view.pages.proposals.view.proposal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.util.enums.proposal.ProposalTemplateSectionType;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.utils.edit.ProposalUpdateHelper;
import org.xcolab.view.util.entity.analytics.AnalyticsUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class CreateProposalController extends BaseProposalsController {

    @GetMapping("createProposal/basedOn/{baseProposalId}/{baseProposalVersion}/{baseContestId}")
    public String createProposalsBasedOn(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper loggedInMember,
            @PathVariable Long baseProposalId,
            @PathVariable Integer baseProposalVersion, @PathVariable Long baseContestId) {

        return showCreateProposal(request, response, model, proposalContext, loggedInMember, baseProposalId,
                baseProposalVersion, baseContestId);
    }

    @GetMapping("createProposal")
    public String showCreateProposal(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper loggedInMember) {


        Long proposalCreationMaxPerAuthor = ConfigurationAttributeKey.PROPOSALS_MAX_PER_AUTHOR_IN_CONTEST.get();
        if(proposalCreationMaxPerAuthor != 0 ) {
            int totalProposalsByAuthor = 0;
            List<ProposalWrapper> proposals = proposalContext.getClients().getProposalClient().getActiveProposalsInContestPhase(
                    proposalContext.getContestPhase().getId());
            for(ProposalWrapper p: proposals){
                if(p.getAuthor().getId() == loggedInMember.getId()) {
                    totalProposalsByAuthor = totalProposalsByAuthor + 1;
                }
            }
            if((totalProposalsByAuthor>= proposalCreationMaxPerAuthor)) {
                return "redirect:" + proposalContext.getContest().getContestUrl();
            }

        }
        return showCreateProposal(request, response, model, proposalContext, loggedInMember, null, -1, null);
    }

    private String showCreateProposal(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper loggedInMember, Long baseProposalId,
            int baseProposalVersion, Long baseContestId) {

        if (!proposalContext.getPermissions().getCanCreate()) {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }

        long userId = loggedInMember.getId();

        final ClientHelper clients = proposalContext.getClients();
        final IContestClient contestClient = clients.getContestClient();
        final IProposalClient proposalClient = clients.getProposalClient();

        final ContestWrapper contest = proposalContext.getContest();
        ProposalWrapper proposal = new ProposalWrapper();

        proposal.setId(0L);
        proposal.setVisible(true);
        proposal.setAuthorUserId(userId);

        final ContestPhaseWrapper contestPhase = proposalContext.getContestPhase();

        proposal = new ProposalWrapper(proposal, 0, contest, contestPhase, null);
        if (baseProposalId != null && baseProposalId > 0) {
            try {
                ContestWrapper baseContest = contestClient.getContest(baseContestId);
                ProposalWrapper baseProposal = new ProposalWrapper(proposalClient.getProposal(baseProposalId),
                        baseProposalVersion, baseContest, contestClient.getActivePhase(baseContest.getId()), null);

                model.addAttribute("baseProposal", baseProposal);

                model.addAttribute("baseContest", baseContest);

                if (!model.containsAttribute("updateProposalDetailsBean")) {
                    UpdateProposalDetailsBean updateProposalDetailsBean =
                            new UpdateProposalDetailsBean(proposal, baseProposal);
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
        model.addAttribute("userTeams", StaticUserContext.getPlatformTeamClient().listPlatformTeams(loggedInMember.getId()));
        model.addAttribute("contestTosAccepted", contest.getMemberAgreedToTos(loggedInMember));

        AnalyticsUtil.publishEvent(request, userId, ProposalUpdateHelper.PROPOSAL_ANALYTICS_KEY + 1,
                ProposalUpdateHelper.PROPOSAL_ANALYTICS_CATEGORY,
                ProposalUpdateHelper.PROPOSAL_ANALYTICS_ACTION,
                ProposalUpdateHelper.PROPOSAL_ANALYTICS_LABEL,
    			1);

        request.setAttribute("imageUploadServiceUrl",
                ConfigurationAttributeKey.IMAGE_UPLOAD_EXTERNAL_SERVICE_URL.get());
        request.setAttribute("imageUploadHelpText", ConfigurationAttributeKey.IMAGE_UPLOAD_HELP_TEXT.get());

        model.addAttribute("proposalPitchCharLimit", ConfigurationAttributeKey.PROPOSALS_PITCH_CHAR_LIMIT.get());

        return "proposals/proposalDetails_edit";
    }

    private boolean hasProposalPicker(ProposalWrapper proposal) {
        return proposal.getSections().stream()
                .map(ProposalTemplateSectionDefinitionWrapper::getTypeEnum)
                .anyMatch(ProposalTemplateSectionType.PROPOSAL_PICKER_SECTION_TYPES::contains);
    }

    @PostMapping("createProposal")
    public String createProposal(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext,
            @PathVariable String contestYear, @PathVariable String contestUrlName,
            @Valid UpdateProposalDetailsBean updateProposalDetailsBean, BindingResult result) {

        ProposalWrapper proposal = proposalContext.getProposal();
        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanCreate()) {
            return new AccessDeniedPage(permissions.getMember()).toViewName(response);
        }

        if (result.hasErrors()) {
            AlertMessage.danger(
                    "Proposal NOT created. Please fix the errors before saving.")
                    .flash(request);
            final UserWrapper memberOrNull = MemberAuthUtil.getMemberOrNull();
            return showCreateProposal(request, response, model, proposalContext, memberOrNull);
        }

        // if no error occurred it can be assumed that the user agreed to the ToS
        final UserWrapper member = MemberAuthUtil.getMemberOrNull();
        if (member != null && !proposalContext.getContest().getMemberAgreedToTos(member)) {
            proposalContext.getContest().setMemberAgreedToTos(member, true);
        }

        return AddUpdateProposalControllerUtil
                .createOrUpdateProposal(request, updateProposalDetailsBean, proposal, proposalContext);
    }
}
