package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.xcolab.portlets.proposals.utils.GenericJudgingStatus;
import org.xcolab.wrappers.BaseContestPhaseWrapper;
import org.xcolab.wrappers.BaseContestTeamRoleWrapper;
import org.xcolab.wrappers.BaseContestWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ContestWrapper extends BaseContestWrapper {

    private static final long ONTOLOGY_SPACE_ID_WHERE = 104L;
    private static final long ONTOLOGY_SPACE_ID_WHO = 102L;
    private static final long ONTOLOGY_SPACE_ID_WHAT = 103L;
    private static final long ONTOLOGY_SPACE_ID_HOW = 103L;
    private static final long CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS = 3L;

    private static final String EMAIL_TEMPLATE_URL = "/web/guest/generic-advancing-email-template";

    private Map<String, String> ontologyJoinedNames = new HashMap<>();
    private List<ContestPhaseWrapper> visiblePhases;
    private ContestPhaseWrapper activePhase;

    public ContestWrapper(Contest contest) {
        super(contest);
    }

    public Class<?> getModelClass() {
        return contest.getModelClass();
    }

    public String getModelClassName() {
        return contest.getModelClassName();
    }

    public long getPrimaryKey() {
        return contest.getPrimaryKey();
    }

    public void setPrimaryKey(long primaryKey) {
        contest.setPrimaryKey(primaryKey);
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
        return contest.isShow_in_tile_view();
    }

    public void setShowInTileView(boolean showInTileView){
        contest.setShow_in_tile_view(showInTileView);
    }

    public boolean getShowInListView(){
        return contest.getShow_in_list_view();
    }

    public boolean isShowInListView(){
        return contest.isShow_in_list_view();
    }

    public void setShowInListView(boolean showInListView){
        contest.setShow_in_list_view(showInListView);
    }

    public boolean getShowInOutlineView(){
        return contest.getShow_in_outline_view();
    }

    public boolean isShowInOutlineView(){
        return contest.isShow_in_outline_view();
    }

    public void setShowInOutlineView(boolean showInOutlineView){
        contest.setShow_in_outline_view(showInOutlineView);
    }

    public void resetOriginalValues() {
        contest.resetOriginalValues();
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
    	else if (contest.getUpdated() != null) {
    		return contest.getUpdated().getTime();
    	}
    	return 0;
    }

    public long getFellowDiscussionGroupId() {
        return contest.getFellowDiscussionGroupId();
    }

    public void setFellowDiscussionGroupId(long fellowDiscussionGroupId) {
        contest.setFellowDiscussionGroupId(fellowDiscussionGroupId);
    }

    public long getTotalCommentsCount() throws PortalException, SystemException {
        if (getContestType().getHasDiscussion()) {
            return ContestLocalServiceUtil.getTotalCommentsCount(contest);
        }
        return ContestLocalServiceUtil.getProposalsCommentsCount(contest);
    }

    public long getVotesCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getVotesCount(contest);
    }

    public BaseContestPhaseWrapper getLastPhase() throws PortalException, SystemException {
        BaseContestPhaseWrapper last = null;
        for (BaseContestPhaseWrapper ph : getPhases()) {
            if (last == null || (ph.getPhaseReferenceDate() != null && ph.getPhaseReferenceDate().compareTo(last.getPhaseReferenceDate()) > 0)) {
                last = ph;
            }
        }
        return last;
    }

    public String getWhoName() throws PortalException, SystemException {
        return getTermNameFromSpace(WHO);
    }

    public String getWhatName() throws PortalException, SystemException {
        return getTermNameFromSpace(WHAT);
    }

    public String getWhereName() throws PortalException, SystemException {
        return getTermNameFromSpace(WHERE);
    }

    public String getHowName() throws PortalException, SystemException {
        return getTermNameFromSpace(HOW);
    }

    public String getTermNameFromSpace(String space) throws PortalException, SystemException {
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

    public List<Contest> getSubContests() throws SystemException, PortalException {
        List <Contest> subContests = ContestLocalServiceUtil.getSubContestsByOntologySpaceId(contest, ONTOLOGY_SPACE_ID_WHERE);
        Collections.sort(subContests, new Comparator<Contest>() {
            @Override
            public int compare(Contest c1, Contest c2) {
                return c1.getWeight() - c2.getWeight();
            }
        });
        return subContests;
    }

    public Contest getParentContest() throws SystemException, PortalException {
        List<Long> focusAreaOntologyTermIds =
                FocusAreaOntologyTermLocalServiceUtil.getFocusAreaOntologyTermIdsByFocusAreaAndSpaceId(contest.getFocusAreaId(), ONTOLOGY_SPACE_ID_WHERE);
        List<Contest> contests = ContestLocalServiceUtil.getContestsByTierLevelAndOntologyTermIds(CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS, focusAreaOntologyTermIds);
        return contests.get(0);
    }

    public Long getVotingPhasePK() throws PortalException, SystemException {
        BaseContestPhaseWrapper lastVotingPhase = null;
        for (BaseContestPhaseWrapper ph : getPhases()) {
            if (ph.getContestPhaseTypeObject() != null && "VOTING".equals(ph.getContestPhaseTypeObject().getStatus())) {
                lastVotingPhase = ph;
            }
        }
        return lastVotingPhase != null ? lastVotingPhase.getContestPhasePK() : 0;
    }

    public boolean isContestCompleted(ContestPhaseWrapper contestPhase) {
        ContestPhaseType type;
        ContestPhase activePhase;
        try {
            activePhase = ContestLocalServiceUtil.getActivePhase(this.contest);
            type = new ContestPhaseWrapper(activePhase).getContestPhaseTypeObject();
        } catch (IllegalArgumentException | NullPointerException | SystemException | PortalException e) {
            return false;
        }
        return !(type == null || activePhase == null
                    || contestPhase.getContestPhasePK() != activePhase.getContestPhasePK()
                ) && ("COMPLETED".equals(type.getStatus()));
    }

    public List<ContestPhaseWrapper> getVisiblePhases() throws SystemException, PortalException {
        if (visiblePhases == null) {
            visiblePhases = new ArrayList<>();
            for (ContestPhase phase : ContestLocalServiceUtil.getVisiblePhases(contest)) {
                visiblePhases.add(new ContestPhaseWrapper(phase));
            }
        }
        return visiblePhases;
    }

    public boolean getHasFocusArea() {
        return contest.getFocusAreaId() > 0;
    }

    public boolean isUserAmongAdvisors(User userInQuestion) throws SystemException, PortalException {
        for (User judge : getContestAdvisors()) {
            if (judge.getUserId() == userInQuestion.getUserId()) {
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
            ContestPhase contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(contest);
            for (Proposal proposal : ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhase.getPrimaryKey())) {
                Proposal2Phase p2p = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
                if ((new ProposalWrapper(proposal, proposal.getCurrentVersion(), contest, contestPhase, p2p)).getJudgeStatus() == GenericJudgingStatus.STATUS_ACCEPTED) {
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
            ContestPhase contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(contest);
            for (Proposal proposal : ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhase.getPrimaryKey())) {
                Proposal2Phase p2p = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
                if ((new ProposalWrapper(proposal, proposal.getCurrentVersion(), contest, contestPhase, p2p)).getScreeningStatus() == GenericJudgingStatus.STATUS_UNKNOWN) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
