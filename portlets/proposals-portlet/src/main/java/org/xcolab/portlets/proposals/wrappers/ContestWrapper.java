package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.portlets.proposals.utils.GenericJudgingStatus;
import org.xcolab.portlets.proposals.utils.context.ClientHelper;
import org.xcolab.wrappers.BaseContestPhaseWrapper;
import org.xcolab.wrappers.BaseContestWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContestWrapper extends BaseContestWrapper {

    private static final long ONTOLOGY_SPACE_ID_WHERE = 104L;
    private static final long ONTOLOGY_SPACE_ID_WHO = 102L;
    private static final long ONTOLOGY_SPACE_ID_WHAT = 103L;
    private static final long ONTOLOGY_SPACE_ID_HOW = 103L;
    private static final long CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS = 3L;

    private static final String EMAIL_TEMPLATE_URL = "/resources/-/wiki/Main/Judging+Mail+Templates";

    private Map<String, String> ontologyJoinedNames = new HashMap<>();
    private List<BaseContestPhaseWrapper> visiblePhases;

    private final ClientHelper clientHelper;

    public ContestWrapper(long contestId) throws ContestNotFoundException {
        this(ContestClientUtil.getContest(contestId));
    }

    public ContestWrapper(Contest contest) {
        super(contest);
        clientHelper = new ClientHelper(contest);
    }

    public boolean getIsSharedContest(){
        return contest.getIsSharedContest();
    }

    public String getContestDescription() {
        return contest.getContestDescription();
    }

    public void setContestDescription(String ContestDescription) {
        contest.setContestDescription(ContestDescription);
    }

    public String getContestModelDescription() {
        return contest.getContestModelDescription();
    }

    public void setContestModelDescription(String ContestModelDescription) {
        contest.setContestModelDescription(ContestModelDescription);
    }

    public String getDefaultModelSettings(){
        return contest.getDefaultModelSettings();
    }

    public void setDefaultModelSettings(String defaultModelSettings) {
        contest.setDefaultModelSettings(defaultModelSettings);
    }

    public String getEmailTemplateUrl() {
        if (contest.getEmailTemplateUrl().isEmpty()) {
            return EMAIL_TEMPLATE_URL;
        } else {
            return contest.getEmailTemplateUrl();
        }
    }

    public void setEmailTemplateUrl(String emailTemplateUrl) {
        contest.setEmailTemplateUrl(emailTemplateUrl);
    }

    public boolean getShowInTileView(){
        return contest.getShow_in_tile_view();
    }

    public boolean isShowInTileView(){
        return contest.getShow_in_tile_view();
    }

    public void setShowInTileView(boolean showInTileView){
        contest.setShow_in_tile_view(showInTileView);
    }

    public boolean getShowInListView(){
        return contest.getShow_in_list_view();
    }

    public boolean isShowInListView(){
        return contest.getShow_in_list_view();
    }

    public void setShowInListView(boolean showInListView){
        contest.setShow_in_list_view(showInListView);
    }

    public boolean getShowInOutlineView(){
        return contest.getShow_in_outline_view();
    }

    public boolean isShowInOutlineView(){
        return contest.getShow_in_outline_view();
    }

    public void setShowInOutlineView(boolean showInOutlineView){
        contest.setShow_in_outline_view(showInOutlineView);
    }



    public String getContestPositionsDescription() {
        return contest.getContestPositionsDescription();
    }

    public void setContestPositionsDescription(String ContestPositionsDescription) {
        contest.setContestPositionsDescription(ContestPositionsDescription);
    }

    public long getCreatedTime() {
    	if (contest.getCreated() != null) {
    		return contest.getCreated().getTime();
    	}
        if (contest.getUpdated() != null) {
            return contest.getUpdated().getTime();
        }
        return 0;
    }

    public long getTotalCommentsCount() {
        final CommentClient commentClient = clientHelper.getCommentClient();
        if (getContestType().getHasDiscussion()) {
           return commentClient.countComments(contest.getDiscussionGroupId());
        }
        Integer contestComments = commentClient.countComments(contest.getDiscussionGroupId());
        ContestPhase phase = clientHelper.getContestClient().getActivePhase(contest.getContestPK());
        contestComments += commentClient.countCommentsInContestPhase(
                phase.getContestPhasePK(), phase.getContestPK());
        return contestComments;
    }

    public long getVotesCount() {
        ContestPhase phase = clientHelper.getContestClient().getActivePhase(contest.getContestPK());
        return clientHelper.getProposalMemberRatingClient().countProposalVotesInContestPhase(phase.getContestPhasePK());
    }

    public BaseContestPhaseWrapper getLastPhase() {
        BaseContestPhaseWrapper last = null;
        for (BaseContestPhaseWrapper ph : getPhases()) {
            if (last == null || (ph.getPhaseReferenceDate() != null
                    && ph.getPhaseReferenceDate().compareTo(last.getPhaseReferenceDate()) > 0)) {
                last = ph;
            }
        }
        return last;
    }

    public String getWhoName() {
        return getTermNameFromSpace(WHO);
    }

    public String getWhatName() {
        return getTermNameFromSpace(WHAT);
    }

    public String getWhereName() {
        return getTermNameFromSpace(WHERE);
    }

    public String getHowName() {
        return getTermNameFromSpace(HOW);
    }

    private String getTermNameFromSpace(String space) {
        String ontologyJoinedName = ontologyJoinedNames.get(space);
        if (ontologyJoinedName == null) {
            getTermFromSpace(space);
            ontologyJoinedName = ontologyJoinedNames.get(space);
        }

        if (ontologyJoinedName == null) {
        	return "";
        }
        return ontologyJoinedName;
    }

    public boolean getShowSubContests(){
        // Removed due to COLAB-518; keep the functionality in the code base for the case that we need it again.
//        return contest.getContestTier() == CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS;
        return false;
    }

    public boolean getShowParentContest(){
        return contest.getContestTier() == CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS - 1;
    }

    public List<Contest> getSubContests() {
        List <Contest> subContests = clientHelper.getContestClient()
                .getSubContestsByOntologySpaceId(contest.getContestPK(), ONTOLOGY_SPACE_ID_WHERE);
        Collections.sort(subContests, new Comparator<Contest>() {
            @Override
            public int compare(Contest c1, Contest c2) {
                return c1.getWeight() - c2.getWeight();
            }
        });
        return subContests;
    }

    public ContestWrapper getParentContest() throws SystemException, PortalException {
        List<Long> focusAreaOntologyTermIds =
                FocusAreaOntologyTermLocalServiceUtil.getFocusAreaOntologyTermIdsByFocusAreaAndSpaceId(contest.getFocusAreaId(), ONTOLOGY_SPACE_ID_WHERE);
        List<Contest> contests = clientHelper.getContestClient()
                .findContestsTierLevelAndOntologyTermIds(CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS, focusAreaOntologyTermIds);
        return new ContestWrapper(contests.get(0));
    }

    public Long getVotingPhasePK() {
        BaseContestPhaseWrapper lastVotingPhase = null;
        for (BaseContestPhaseWrapper ph : getPhases()) {
            final ContestPhaseType contestPhaseType = ph.getContestPhaseTypeObject();
            if (contestPhaseType != null && "VOTING".equals(
                    contestPhaseType.getStatus())) {
                lastVotingPhase = ph;
            }
        }
        return lastVotingPhase != null ? lastVotingPhase.getContestPhasePK() : 0;
    }

    public boolean isContestCompleted(BaseContestPhaseWrapper contestPhase) {
        ContestPhaseType type;
        ContestPhase activePhase;
        try {
            activePhase = clientHelper.getContestClient().getActivePhase(this.contest.getContestPK());
            type = new BaseContestPhaseWrapper(activePhase).getContestPhaseTypeObject();
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
        return !(type == null || activePhase == null
                    || contestPhase.getContestPhasePK() != activePhase.getContestPhasePK()
                ) && ("COMPLETED".equals(type.getStatus()));
    }

    public List<BaseContestPhaseWrapper> getVisiblePhases() {
        if (visiblePhases == null) {
            visiblePhases = new ArrayList<>();
            for (ContestPhase phase : clientHelper.getContestClient()
                    .getVisibleContestPhases(contest.getContestPK())) {
                visiblePhases.add(new BaseContestPhaseWrapper(phase));
            }
        }
        return visiblePhases;
    }

    public boolean getHasFocusArea() {
        return contest.getFocusAreaId() > 0;
    }

    public boolean isUserAmongAdvisors(Member memberInQuestion) {
        for (Member judge : getContestAdvisors()) {
            if (judge.getUserId() == memberInQuestion.getUserId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if judges are done with proposal
     *
     * @return 0 if judge action is incomplete, 1 judge actions completed
     */
    public boolean getJudgeStatus() {
        try {

            ContestPhase contestPhase = clientHelper.getContestClient().getActivePhase(contest.getContestPK());
            for (Proposal proposal : clientHelper.getProposalClient().getProposalsInContestPhase(contestPhase.getContestPhasePK())) {
                Proposal2Phase p2p = clientHelper.getProposalPhaseClient().getProposal2PhaseByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());

                final ProposalWrapper proposalWrapper =
                        new ProposalWrapper(proposal, proposal.getCurrentVersion(), contest,
                                contestPhase, p2p);
                if (proposalWrapper.getJudgeStatus() == GenericJudgingStatus.STATUS_ACCEPTED) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Determine if fellows are done with proposal
     *
     * @return 0 if fellow action is incomplete, 1 fellow action completed
     */
    public boolean getScreeningStatus() {
        try {
            Contest contest = clientHelper.getContestClient().getContest(this.contest.getContestPK());
            ContestPhase contestPhase = clientHelper.getContestClient().getActivePhase(contest.getContestPK());

            for (Proposal proposal : clientHelper.getProposalClient().getProposalsInContestPhase(contestPhase.getContestPhasePK())) {
                Proposal2Phase p2p = clientHelper.getProposalPhaseClient().getProposal2PhaseByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
                if ((new ProposalWrapper(proposal, proposal.getCurrentVersion(), contest, contestPhase, p2p)).getScreeningStatus() == GenericJudgingStatus.STATUS_UNKNOWN) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getNewProposalLinkUrl() {
        final String portletUrl = getContestType().getPortletUrl();
        return String.format("%s/%s/%s/createProposal",
                portletUrl, contest.getContestYear(), contest.getContestUrlName());
    }
}
