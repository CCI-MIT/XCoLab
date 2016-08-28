package org.xcolab.portlets.proposals.view;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.proposals.exceptions.ProposalIdOrContestIdInvalidException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsSortFilterBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Controller
@RequestMapping("view")
public class ContestProposalsController extends BaseProposalsController {

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = "pageToDisplay=contestProposals")
    public String showContestProposals(RenderRequest request, RenderResponse response,
                                       final SortFilterPage sortFilterPage, Model model)
            throws PortalException, SystemException {

        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        Contest contest = proposalsContext.getContest(request);

        if (Validator.isNull(contest) || Validator.isNull(contestPhase)) {
            throw new ProposalIdOrContestIdInvalidException();
        }

        Member u = request.getRemoteUser() != null ? MembersClient.getMemberUnchecked(Long.parseLong(request.getRemoteUser())) : null;
        List<ProposalWrapper> proposals = new ArrayList<>();

        for (Proposal proposal : ProposalLocalServiceUtil.getActiveProposalsInContestPhase(contestPhase.getContestPhasePK())) {
            Proposal2Phase p2p = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
            ProposalWrapper proposalWrapper;

            if (u != null && UserLocalServiceUtil.hasRoleUser(MemberRole.JUDGE.getRoleId(), u.getUserId())) {
                proposalWrapper = new ProposalJudgeWrapper(proposal, p2p.getVersionTo() == -1 ? proposal.getCurrentVersion() : p2p.getVersionTo(), contest, contestPhase, p2p, u);

            } else {
                proposalWrapper = new ProposalWrapper(proposal, p2p.getVersionTo() == -1 ? proposal.getCurrentVersion() : p2p.getVersionTo(), contest, contestPhase, p2p);
            }

            proposals.add(proposalWrapper);
        }

        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("showCountdown",
                ConfigurationAttributeKey.SHOW_CONTEST_COUNTDOWN.getBooleanValue());
        model.addAttribute("proposals", new ProposalsSortFilterBean(proposals, sortFilterPage));
        model.addAttribute("timeZone", ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID.getStringValue());
        model.addAttribute("contestCompleted", proposalsContext.getContestWrapped(request).isContestCompleted(proposalsContext.getContestPhaseWrapped(request)));

        setSeoTexts(request, contest.getContestShortName(), null, contest.getContestDescription());

        return "contestProposals";
    }

    @ActionMapping(params = "action=redirectOldContestDiscussionUrl")
    public void redirectOldContestDiscussionUrl(ActionRequest request, ActionResponse response, Model model,
            @RequestParam Long contestId) throws SystemException, PortalException, IOException {

        String contestUrl = ContestLocalServiceUtil.getContestLinkUrl(proposalsContext.getContest(request));
        response.sendRedirect(contestUrl + "/discussion");
    }

    @ActionMapping(params = "action=redirectOldContestProposalsUrl")
    public void redirectOldContestProposalsUrl(ActionRequest request, ActionResponse response, Model model,
                                       @RequestParam Long contestId, @RequestParam(required = false) Long phaseId) throws SystemException, PortalException, IOException {

        String redirectUrl;
        if (phaseId != null && phaseId > 0) {
            redirectUrl = ContestPhaseLocalServiceUtil.getContestPhaseLinkUrl(proposalsContext.getContestPhase(request));
        } else {
            redirectUrl = ContestLocalServiceUtil.getContestLinkUrl(proposalsContext.getContest(request));
        }
        response.sendRedirect(redirectUrl);
    }

    @ActionMapping(params = "action=redirectOldProposalUrl")
    public void redirectOldProposalUrl(
            @RequestParam(value="planId") Long proposalId,
            @RequestParam Long contestId,
            @RequestParam(required = false) Long phaseId,
            @RequestParam(required = false) Long version,
            @RequestParam(required = false) String tab,
            Model model, ActionRequest request, ActionResponse response)
            throws PortalException, SystemException, IOException {

        Proposal proposal = ProposalLocalServiceUtil.getProposal(proposalId);
        Contest contest = ContestLocalServiceUtil.getContest(contestId);

        String redirectUrl;
        if (phaseId != null && phaseId > 0) {
            ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(phaseId);
            redirectUrl = ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposal, contestPhase);
        } else {
            redirectUrl = ProposalLocalServiceUtil.getProposalLinkUrl(contest, proposal);
        }
        if (version != null && version > 0) {
            redirectUrl += "/version/" + version;
        }
        if (StringUtils.isNotBlank(tab)) {
            redirectUrl += "/tab/" + tab;
        }
        response.sendRedirect(redirectUrl);
    }

}


