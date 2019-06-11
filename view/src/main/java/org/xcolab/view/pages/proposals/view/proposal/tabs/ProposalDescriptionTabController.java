package org.xcolab.view.pages.proposals.view.proposal.tabs;

import io.micrometer.core.instrument.Metrics;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.IProposalMoveHistory;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestTypeProposal;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.IProposalMoveClient;
import org.xcolab.client.moderation.IModerationClient;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.util.enums.contest.ContestPhaseTypeValue;
import org.xcolab.util.enums.moderation.TargetType;
import org.xcolab.util.enums.proposal.MoveType;
import org.xcolab.util.enums.proposal.ProposalTemplateSectionType;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.discussion.ProposalDiscussionPermissions;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.JudgeProposalFeedbackBean;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.view.proposal.AddUpdateProposalControllerUtil;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.util.entity.EntityGroupingUtil;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalDescriptionTabController extends BaseProposalTabController {

    @Autowired
    private IModerationClient moderationClient;

    @Autowired
    private IContestTypeClient contestTypeClient;

    @GetMapping("c/{proposalUrlString}/{proposalId}")
    public String showProposalDetails(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper currentMember,
            @PathVariable Long contestYear,
            @PathVariable String contestUrlName, @PathVariable Long proposalId,
            @RequestParam(defaultValue = "false") boolean edit,
            @RequestParam(required = false) Integer version,
            @RequestParam(required = false) Long moveFromContestPhaseId,
            @RequestParam(required = false) String moveType,
            @Valid JudgeProposalFeedbackBean judgeProposalFeedbackBean,
            BindingResult bindingResult) {
        long startTime = System.nanoTime();
        Metrics.counter("xcolab-view","endpoint","/contests/"+  contestYear +"/" + contestUrlName + "/c/proposal" +proposalId, "function", "/contests").increment();
        String proposalView = showProposalDetails(request, response, model, proposalContext, currentMember, false,
                edit, moveFromContestPhaseId, moveType);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        Metrics.timer("xcolab-view_timer","endpoint","/contests/"+  contestYear +"/" + contestUrlName + "/c/proposal" +proposalId, "function", "/contests").record(duration, NANOSECONDS);
        return proposalView;

    }

    public String showProposalDetails(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper currentMember,
            boolean voted, boolean edit, Long moveFromContestPhaseId, String moveType) {

        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanView()) {
            return new AccessDeniedPage(currentMember).toViewName(response);
        }

        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.DESCRIPTION);

        boolean editValidated = false;
        final ProposalsPermissions proposalsPermissions = proposalContext.getPermissions();
        if (edit && proposalsPermissions.getCanEdit()) {
            editValidated = true;
        }
        model.addAttribute("edit", editValidated);
        model.addAttribute("voted", voted);
        model.addAttribute("reportTargets", moderationClient.listReportTargets(TargetType.PROPOSAL));
        model.addAttribute("showOpennessStatus",
            ConfigurationAttributeKey.CONTESTS_ALLOW_OPEN_PROPOSALS.get());

        // make sure it's in the right contest,
        // which might not be the proposal's contests (e.g. when moving)
        model.addAttribute("saveUrl", proposalContext.getProposal().getProposalLinkUrl(proposalContext.getContest()));

        final ProposalWrapper proposal = proposalContext.getProposal();

        final ClientHelper clients = proposalContext.getClients();
        final IProposalClient proposalClient = clients.getProposalClient();
        final ContestWrapper baseContest = proposalClient
                .getCurrentContestForProposal(proposal.getId());

        if (voted) {
            setVotingDeadline(model, proposalContext, baseContest);
        }

        final boolean isMove = moveFromContestPhaseId != null && moveType != null;
        if (isMove) {
            // get base proposal from base contest
            ContestPhaseWrapper baseContestPhase =
                    contestClient.getActivePhase(baseContest.getId());

            ProposalWrapper baseProposalWrapped = new ProposalWrapper(proposal, proposal.getCurrentVersion(),
                    baseContest, baseContestPhase, null);

            UpdateProposalDetailsBean updateProposalDetailsBean = new UpdateProposalDetailsBean(
                    proposal, baseProposalWrapped, true,
                    MoveType.valueOf(moveType, true));
            updateProposalDetailsBean.setMoveFromContestPhaseId(moveFromContestPhaseId);
            updateProposalDetailsBean.setMoveToContestId(proposalContext.getContest().getId());

            model.addAttribute("hasUnmappedSections",
                    hasUnmappedSections(proposalContext.getContest(), baseProposalWrapped));
            model.addAttribute("baseProposal", baseProposalWrapped);
            model.addAttribute("baseContest", baseContest);
            model.addAttribute("isMove", true);

            if (!model.containsAttribute("updateProposalDetailsBean")) {
                model.addAttribute("updateProposalDetailsBean", updateProposalDetailsBean);
            }
        } else if (!model.containsAttribute("updateProposalDetailsBean")) {
            model.addAttribute("updateProposalDetailsBean", new UpdateProposalDetailsBean(
                    proposal));
        }

        if (editValidated || isMove) {
            request.setAttribute("imageUploadServiceUrl",
                    ConfigurationAttributeKey.IMAGE_UPLOAD_EXTERNAL_SERVICE_URL.get());
            request.setAttribute("imageUploadHelpText",
                    ConfigurationAttributeKey.IMAGE_UPLOAD_HELP_TEXT.get());

            model.addAttribute("hasProposalPicker", hasProposalPicker(proposal));

            model.addAttribute("showProposalEditHelpText",
                    ConfigurationAttributeKey.CONTESTS_SHOW_PROPOSAL_EDIT_HELP_TEXT.get());
            model.addAttribute("showImageUpload",
                    ConfigurationAttributeKey.PROPOSALS_SHOW_IMAGE_UPLOAD.get());
            model.addAttribute("saveButtonText",
                    ConfigurationAttributeKey.PROPOSALS_SAVE_BUTTON_TEXT.get());
            model.addAttribute("saveHelpText",
                    ConfigurationAttributeKey.PROPOSALS_SAVE_HELP_TEXT.get());
            model.addAttribute("proposalPickerDefaultTabIsContests",
                    ConfigurationAttributeKey.PROPOSALS_PICKER_DEFAULT_TAB_CONTESTS.get());
            model.addAttribute("userTeams", StaticUserContext.getPlatformTeamClient()
                    .listPlatformTeams(currentMember.getId()));

            model.addAttribute("proposalPitchCharLimit", ConfigurationAttributeKey.PROPOSALS_PITCH_CHAR_LIMIT.get());

            return "proposals/proposalDetails_edit";
        }

        if (proposalsPermissions.getCanJudgeActions()) {
            setJudgeProposalBean(model, proposalContext, currentMember);
        }

        ProposalDiscussionPermissions pdp = new ProposalDiscussionPermissions(request,
                proposalContext.getProposal());
        request.setAttribute(DiscussionPermissions.REQUEST_ATTRIBUTE_NAME, pdp);

        setLinkedProposals(model, proposalContext, proposal);
        final ContestWrapper contest = proposalContext.getContest();
        populateMoveHistory(model, proposalContext, proposal, contest);

        return "proposals/proposalDetails";
    }

    private boolean hasUnmappedSections(ContestWrapper moveToContest, ProposalWrapper baseProposalWrapped) {
        Set<Long> newContestSections = moveToContest.getSections().stream()
                .map(ProposalTemplateSectionDefinitionWrapper::getSectionDefinitionId)
                .collect(Collectors.toSet());

        return baseProposalWrapped.getSections().stream()
                .filter(section -> StringUtils.isNotBlank(section.getContent()))
                .anyMatch(section -> !newContestSections.contains(section.getSectionDefinitionId()));
    }

    private boolean hasProposalPicker(ProposalWrapper proposal) {
        return proposal.getSections().stream()
                .map(ProposalTemplateSectionDefinitionWrapper::getTypeEnum)
                .anyMatch(ProposalTemplateSectionType.PROPOSAL_PICKER_SECTION_TYPES::contains);
    }

    private void populateMoveHistory(Model model, ProposalContext proposalContext,
            ProposalWrapper proposal, ContestWrapper contest) {

        final ClientHelper clients = proposalContext.getClients();
        final IProposalMoveClient proposalMoveClient = clients.getProposalMoveClient();
        List<IProposalMoveHistory> sourceMoveHistories = proposalMoveClient
                        .getBySourceProposalIdContestId(proposal.getId(),
                                contest.getId());
        model.addAttribute("sourceMoveHistories", sourceMoveHistories);

        IProposalMoveHistory targetMoveHistory = proposalMoveClient
                        .getByTargetProposalIdContestId(proposal.getId(),
                                contest.getId());
        if (targetMoveHistory != null) {
            model.addAttribute("targetMoveHistory", targetMoveHistory);
        }
    }

    private void setLinkedProposals(Model model, ProposalContext proposalContext, ProposalWrapper proposal) {
        final ClientHelper clients = proposalContext.getClients();
        final IProposalClient proposalClient = clients.getProposalClient();
        List<ProposalWrapper> linkedProposals = proposalClient
                        .getSubproposals(proposal.getId(), true);
        Map<ContestType, Set<ProposalWrapper>> proposalsByContestType =
                EntityGroupingUtil.groupByContestType(linkedProposals);
        Map<Long, ContestTypeProposal> contestTypeProposalWrappersByContestTypeId = new HashMap<>();

        for (ContestType contestType : contestTypeClient.getActiveContestTypes()) {
            contestTypeProposalWrappersByContestTypeId.put(contestType.getId(),
                    new ContestTypeProposal(contestType));
            final Set<ProposalWrapper> proposalsInContestType = proposalsByContestType.get(contestType);
            if (proposalsInContestType != null) {
                for (ProposalWrapper p : proposalsInContestType) {
                    contestTypeProposalWrappersByContestTypeId.get(contestType.getId())
                            .getProposals().add((p));
                }
            }
        }
        model.addAttribute("linkedProposalContestTypeProposalWrappersByContestTypeId",
                contestTypeProposalWrappersByContestTypeId);
    }

    private void setJudgeProposalBean(Model model, ProposalContext proposalContext, UserWrapper member) {
        final JudgeProposalFeedbackBean judgeProposalFeedbackBean =
                (JudgeProposalFeedbackBean) model.asMap().get("judgeProposalFeedbackBean");
        if (judgeProposalFeedbackBean == null
                || judgeProposalFeedbackBean.getContestPhaseId() == null) {

            final ProposalWrapper proposal = proposalContext.getProposal();
            final ContestPhaseWrapper contestPhase = proposalContext.getContestPhase();

            ProposalJudgeWrapper proposalJudgeWrapper = new ProposalJudgeWrapper(proposal, member);
            JudgeProposalFeedbackBean judgeProposalBean =
                    new JudgeProposalFeedbackBean(proposalJudgeWrapper);
            judgeProposalBean.setContestPhaseId(contestPhase.getId());

            model.addAttribute("judgeProposalFeedbackBean", judgeProposalBean);
        }
    }

    private void setVotingDeadline(Model model, ProposalContext proposalContext, ContestWrapper baseContest) {
        Date votingDeadline = getVotingDeadline(proposalContext, baseContest);
        if (votingDeadline != null) {
            final DateFormat customDateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
            model.addAttribute("votingDeadline", customDateFormat.format(votingDeadline));
        } else {
            model.addAttribute("votingDeadline", "");
        }
    }

    private Date getVotingDeadline(ProposalContext proposalContext, ContestWrapper contest) {
        List<ContestPhaseWrapper> contestPhases = proposalContext.getClients().getContestClient()
                .getAllContestPhases(contest.getId());
        final ContestPhaseWrapper activeVotingPhase = getActiveVotingPhase(contestPhases);
        if (activeVotingPhase != null) {
            return activeVotingPhase.getPhaseEndDate();
        }
        return null;
    }

    private ContestPhaseWrapper getActiveVotingPhase(List<ContestPhaseWrapper> contestPhases) {
        for (ContestPhaseWrapper phase : contestPhases) {
            if (phase.getContestPhaseTypeId() == ContestPhaseTypeValue.SELECTION_OF_WINNERS.getTypeId() ||
                    phase.getContestPhaseTypeId() == ContestPhaseTypeValue.SELECTION_OF_WINNERS_NEW.getTypeId() ||
                    phase.getContestPhaseTypeId() == ContestPhaseTypeValue.WINNERS_SELECTION.getTypeId() ||
                    phase.getContestPhaseTypeId() == ContestPhaseTypeValue.VOTING_PHASE_SOLVE.getTypeId()
                    ) {
                if (phase.getPhaseActive()) {
                    return phase;
                }
            }
        }

        return null;
    }

    @PostMapping("c/{proposalUrlString}/{proposalId}")
    public String updateProposal(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper currentMember,
            @PathVariable String contestYear, @PathVariable String contestUrlName,
            @PathVariable String proposalUrlString, @PathVariable String proposalId,
            @Valid UpdateProposalDetailsBean updateProposalDetailsBean, BindingResult result)
            throws ProposalsAuthorizationException, IOException {

        ProposalWrapper proposal = proposalContext.getProposal();
        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (proposal != null && !permissions.getCanEdit()) {
            return new AccessDeniedPage(currentMember).toViewName(response);
        }

        if (result.hasErrors()) {
            AlertMessage.danger(
                    "Changes NOT saved. Please fix the errors before saving.")
                    .flash(request);
            return showProposalDetails(request, response, model, proposalContext, currentMember,
                    false, true, null, null);
        }

        return AddUpdateProposalControllerUtil
                .createOrUpdateProposal(request, updateProposalDetailsBean, proposal, proposalContext);
    }

}
