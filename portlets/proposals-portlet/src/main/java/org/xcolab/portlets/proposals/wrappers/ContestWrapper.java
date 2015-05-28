package org.xcolab.portlets.proposals.wrappers;

import java.util.*;
import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

public class ContestWrapper {

    private static final long ONTOLOGY_SPACE_ID_WHERE = 104L;
    private static final long ONTOLOGY_SPACE_ID_WHO = 102L;
    private static final long ONTOLOGY_SPACE_ID_WHAT = 103L;
    private static final long ONTOLOGY_SPACE_ID_HOW = 103L;
    private static final long CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS = 3L;

    private static final String WHERE = "where";
    private static final String WHAT = "what";
    private static final String WHO = "who";
    private static final String HOW = "how";
    private static final String EMAIL_TEMPLATE_URL = "/web/guest/generic-advancing-email-template";
    private final static Map<Long, FocusArea> faCache = new HashMap<Long, FocusArea>();
    private Map<String, List<OntologyTerm>> ontologySpaceCache = new HashMap<String, List<OntologyTerm>>();
    private Map<String, String> ontologyJoinedNames = new HashMap<String, String>();
    private List<ContestPhaseWrapper> phases;
    private List<ContestPhaseWrapper> visiblePhases;
    private ContestPhaseWrapper activePhase;

    private List<ContestTeamRoleWrapper> contestTeamMembersByRole;

    private Contest contest;

    public ContestWrapper(Contest contest) {
        this.contest = contest;
    }

    public void persist() throws SystemException {
        contest.persist();
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

    public long getContestPK() {
        return contest.getContestPK();
    }

    public void setContestPK(long ContestPK) {
        contest.setContestPK(ContestPK);
    }

    public String getContestName() {
        return contest.getContestName();
    }

    public void setContestName(String ContestName) {
        contest.setContestName(ContestName);
    }

    public String getContestShortName() {
        return contest.getContestShortName();
    }

    public void setContestShortName(String ContestShortName) {
        contest.setContestShortName(ContestShortName);
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

    public String getEmailTemplateUrl() {
        if (contest.getEmailTemplateUrl().isEmpty()) {
            return EMAIL_TEMPLATE_URL;
        } else
            return contest.getEmailTemplateUrl();
    }

    public void setEmailTemplateUrl(String emailTemplateUrl){contest.setEmailTemplateUrl(emailTemplateUrl);}

    public boolean getShowInTileView(){
        Boolean contestShowInTileView =  (Boolean) contest.getShow_in_tile_view();

        if (contestShowInTileView != null) {
            return contest.getShow_in_tile_view();
        }
        else {
            contest.setShow_in_tile_view(true);
            return true;
        }
    }

    public boolean isShowInTileView(){
        return contest.isShow_in_tile_view();
    }

    public void setShowInTileView(boolean showInTileView){
        contest.setShow_in_tile_view(showInTileView);
    }

    public boolean getShowInListView(){
        Boolean contestShowInListView =  (Boolean) contest.getShow_in_list_view();

        if (contestShowInListView != null) {
            return contest.getShow_in_list_view();
        }
        else {
            contest.setShow_in_list_view(true);
            return true;
        }
    }

    public boolean isShowInListView(){
        return contest.isShow_in_list_view();
    }

    public void setShowInListView(boolean showInListView){
        contest.setShow_in_list_view(showInListView);
    }

    public boolean getShowInOutlineView(){
        Boolean contestShowInOutlineView =  (Boolean) contest.getShow_in_outline_view();

        if (contestShowInOutlineView != null) {
            return contest.getShow_in_outline_view();
        }
        else {
            contest.setShow_in_outline_view(true);
            return true;
        }

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

    public String getDefaultPlanDescription() {
        return contest.getDefaultPlanDescription();
    }

    public void setDefaultPlanDescription(String defaultPlanDescription) {
        contest.setDefaultPlanDescription(defaultPlanDescription);
    }

    public long getPlanTypeId() {
        return contest.getPlanTypeId();
    }

    public void setPlanTypeId(long PlanTypeId) {
        contest.setPlanTypeId(PlanTypeId);
    }

    public Date getCreated() {
        return contest.getCreated();
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

    public void setCreated(Date created) {
        contest.setCreated(created);
    }

    public Date getUpdated() {
        return contest.getUpdated();
    }

    public void setUpdated(Date updated) {
        contest.setUpdated(updated);
    }

    public long getAuthorId() {
        return contest.getAuthorId();
    }

    public void setAuthorId(long authorId) {
        contest.setAuthorId(authorId);
    }

    public boolean getContestActive() {
        return contest.getContestActive();
    }

    public boolean isContestActive() {
        return contest.isContestActive();
    }

    public void setContestActive(boolean contestActive) {
        contest.setContestActive(contestActive);
    }

    public long getPlanTemplateId() {
        return contest.getPlanTemplateId();
    }

    public void setPlanTemplateId(long planTemplateId) {
        contest.setPlanTemplateId(planTemplateId);
    }

    public long getFocusAreaId() {
        return contest.getFocusAreaId();
    }

    public void setFocusAreaId(long focusAreaId) {
        contest.setFocusAreaId(focusAreaId);
    }

    public long getContestLogoId() {
        return contest.getContestLogoId();
    }

    public void setContestLogoId(long contestLogoId) {
        contest.setContestLogoId(contestLogoId);
    }

    public boolean getFeatured() {
        return contest.getFeatured();
    }

    public boolean isFeatured() {
        return contest.isFeatured();
    }

    public void setFeatured(boolean featured) {
        contest.setFeatured(featured);
    }

    public boolean getPlansOpenByDefault() {
        return contest.getPlansOpenByDefault();
    }

    public boolean isPlansOpenByDefault() {
        return contest.isPlansOpenByDefault();
    }

    public void setPlansOpenByDefault(boolean plansOpenByDefault) {
        contest.setPlansOpenByDefault(plansOpenByDefault);
    }

    public long getSponsorLogoId() {
        return contest.getSponsorLogoId();
    }

    public void setSponsorLogoId(long sponsorLogoId) {
        contest.setSponsorLogoId(sponsorLogoId);
    }

    public String getSponsorText() {
        return contest.getSponsorText();
    }

    public void setSponsorText(String sponsorText) {
        contest.setSponsorText(sponsorText);
    }

    public String getSponsorLink() {
        return contest.getSponsorLink();
    }

    public void setSponsorLink(String sponsorLink) {
        contest.setSponsorLink(sponsorLink);
    }

    public boolean getSponsorLinkAvailable() {
        return !contest.getSponsorLink().equals("");
    }

    public int getFlag() {
        return contest.getFlag();
    }

    public void setFlag(int flag) {
        contest.setFlag(flag);
    }

    public String getFlagText() {
        return contest.getFlagText();
    }

    public void setFlagText(String flagText) {
        contest.setFlagText(flagText);
    }

    public String getFlagTooltip() {
        return contest.getFlagTooltip();
    }

    public void setFlagTooltip(String flagTooltip) {
        contest.setFlagTooltip(flagTooltip);
    }

    public long getGroupId() {
        return contest.getGroupId();
    }

    public void setGroupId(long groupId) {
        contest.setGroupId(groupId);
    }

    public long getDiscussionGroupId() {
        return contest.getDiscussionGroupId();
    }

    public void setDiscussionGroupId(long discussionGroupId) {
        contest.setDiscussionGroupId(discussionGroupId);
    }

    public int getWeight() {
        return contest.getWeight();
    }

    public void setWeight(int weight) {
        contest.setWeight(weight);
    }

    public String getResourcesUrl() {
        return contest.getResourcesUrl();
    }

    public void setResourcesUrl(String resourcesUrl) {
        contest.setResourcesUrl(resourcesUrl);
    }

    public long getProposalsCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getProposalsCount(contest);
    }

    public long getCommentsCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getCommentsCount(contest);
    }

    public long getTotalCommentsCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getTotalCommentsCount(contest);
    }


    public long getVotesCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getVotesCount(contest);
    }

    public ContestPhaseWrapper getLastPhase() throws PortalException, SystemException {
        ContestPhaseWrapper last = null;
        for (ContestPhaseWrapper ph : getPhases()) {
            if (last == null || (ph.getPhaseReferenceDate() != null && ph.getPhaseReferenceDate().compareTo(last.getPhaseReferenceDate()) > 0))
                last = ph;
        }
        return last;
    }

    public ContestPhaseWrapper getActivePhase() throws PortalException, SystemException {
        if (activePhase == null) {
            ContestPhase phase = ContestLocalServiceUtil.getActivePhase(contest);
            if (phase == null) return null;
            activePhase = new ContestPhaseWrapper(phase);
        }
        return activePhase;
    }

    public List<OntologyTerm> getWho() throws PortalException, SystemException {
        return getTermFromSpace(WHO);
    }

    public String getWhoName() throws PortalException, SystemException {
        return getTermNameFromSpace(WHO);
    }

    public List<OntologyTerm> getWhat() throws PortalException, SystemException {
        return getTermFromSpace(WHAT);
    }

    public String getWhatName() throws PortalException, SystemException {
        return getTermNameFromSpace(WHAT);
    }

    public List<OntologyTerm> getWhere() throws PortalException,
            SystemException {
        return getTermFromSpace(WHERE);
    }

    public String getWhereName() throws PortalException, SystemException {
        return getTermNameFromSpace(WHERE);
    }

    public List<OntologyTerm> getHow() throws PortalException,
            SystemException {
        return getTermFromSpace(HOW);
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
        return contest.getContestTier() == CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS;
    }
    public boolean getShowParentContest(){
        return contest.getContestTier() == CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS - 1;
    }

    public List<Contest> getSubContests() throws Exception{
        return ContestLocalServiceUtil.getSubContestsByOntologySpaceId(contest, ONTOLOGY_SPACE_ID_WHERE);
    }

    public Contest getParentContest() throws Exception{
        List<Long> focusAreaOntologyTermIds =
                FocusAreaOntologyTermLocalServiceUtil.getFocusAreaOntologyTermIdsByFocusAreaAndSpaceId(contest.getFocusAreaId(), ONTOLOGY_SPACE_ID_WHERE);
        List<Contest> contests = ContestLocalServiceUtil.getContestsByTierLevelAndOntologyTermIds(CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS, focusAreaOntologyTermIds);
        return contests.get(0);
    }

    public Long getVotingPhasePK() throws PortalException, SystemException {
        ContestPhaseWrapper lastVotingPhase = null;
        for (ContestPhaseWrapper ph : getPhases()) {
            if (ph.getContestPhaseTypeObject() != null && "VOTING".equals(ph.getContestPhaseTypeObject().getStatus()))
                lastVotingPhase = ph;
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
        if (type == null || activePhase == null || contestPhase.getContestPhasePK() != activePhase.getContestPhasePK()) {
            return false;
        }
        return  ("COMPLETED".equals(type.getStatus()));
    }


    private List<OntologyTerm> getTermFromSpace(String space)
            throws PortalException, SystemException {

        if (!ontologySpaceCache.containsKey(space) && getFocusAreaId() > 0) {
            if (!faCache.containsKey(contest.getFocusAreaId())) {
                FocusArea fa = FocusAreaLocalServiceUtil.getFocusArea(contest
                        .getFocusAreaId());
                if (fa == null) {
                    ontologySpaceCache.put(space, null);
                    ontologyJoinedNames.put(space, "");
                    return null;
                }
                faCache.put(fa.getId(), fa);
            }
            List<OntologyTerm> terms = new ArrayList<OntologyTerm>();
            StringBuilder joinedTerms = new StringBuilder();
            for (OntologyTerm t : FocusAreaLocalServiceUtil.getTerms(faCache.get(contest.getFocusAreaId()))) {
                if (OntologyTermLocalServiceUtil.getSpace(t).getName()
                        .equalsIgnoreCase(space)) {
                    terms.add(t);
                    joinedTerms.append(t.getName());
                }
            }
            ontologySpaceCache.put(space, terms.isEmpty() ? null : terms);
            ontologyJoinedNames.put(space, joinedTerms.toString());

        }
        return ontologySpaceCache.get(space);
    }

    public List<ContestPhaseWrapper> getPhases() throws SystemException, PortalException {
        if (phases == null) {
            phases = new ArrayList<ContestPhaseWrapper>();
            for (ContestPhase phase : ContestLocalServiceUtil.getAllPhases(contest)) {
                phases.add(new ContestPhaseWrapper(phase));
            }
        }
        return phases;
    }

    public List<ContestPhaseWrapper> getVisiblePhases() throws SystemException, PortalException {
        if (visiblePhases == null) {
            visiblePhases = new ArrayList<ContestPhaseWrapper>();
            for (ContestPhase phase : ContestLocalServiceUtil.getVisiblePhases(contest)) {
                visiblePhases.add(new ContestPhaseWrapper(phase));
            }
        }
        return visiblePhases;
    }

    public boolean getHasFocusArea() {
        return contest.getFocusAreaId() > 0;
    }

    public List<ContestTeamRoleWrapper> getContestTeamMembersByRole() throws PortalException, SystemException {
        if (contestTeamMembersByRole == null) {
            contestTeamMembersByRole = new ArrayList<ContestTeamRoleWrapper>();
            Map<String, List<User>> teamRoleUsersMap = new TreeMap<String, List<User>>();
            for (ContestTeamMember ctm : ContestLocalServiceUtil.getTeamMembers(contest)) {
                List<User> roleUsers = teamRoleUsersMap.get(ctm.getRole());
                if (roleUsers == null) {
                    roleUsers = new ArrayList<User>();
                    teamRoleUsersMap.put(ctm.getRole(), roleUsers);
                }
                roleUsers.add(ContestTeamMemberLocalServiceUtil.getUser(ctm));
            }

            for (String role : teamRoleUsersMap.keySet()) {
                contestTeamMembersByRole.add(new ContestTeamRoleWrapper(role, teamRoleUsersMap.get(role)));
            }
        }
        return contestTeamMembersByRole;
    }

    public List<User> getContestAdvisors() throws PortalException, SystemException {
        List<User> advisors = null;
        for (ContestTeamRoleWrapper c : getContestTeamMembersByRole()) {
            if (c.getRoleName().equalsIgnoreCase("Advisor")) {
                advisors = c.getUsers();
            }
        }
        if(advisors == null) return new LinkedList<>(); //return empty list if null
        return advisors;
    }

    public boolean isUserAmongAdvisors(User userInQuestion) throws SystemException, PortalException {
        for (User judge : getContestAdvisors()) {
            if (judge.getUserId() == userInQuestion.getUserId()) {
                return true;
            }
        }
        return false;
    }

    public List<User> getContestJudges() throws PortalException, SystemException {
        List<User> judges = null;
        for (ContestTeamRoleWrapper c : getContestTeamMembersByRole()) {
            if (c.getRoleName().equalsIgnoreCase("Judge")) {
                judges = c.getUsers();
            }
        }
        if(judges == null) return new LinkedList<>(); //return empty list if null
        return judges;
    }

    public List<User> getContestFellows() throws PortalException, SystemException {
        List<User> fellows = null;
        for (ContestTeamRoleWrapper c : getContestTeamMembersByRole()) {
            if (c.getRoleName().equalsIgnoreCase("Fellow")) {
                fellows = c.getUsers();
            }
        }
        return fellows;
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
                if ((new ProposalWrapper(proposal, proposal.getCurrentVersion(), contest, contestPhase, p2p)).getJudgeStatus() == ProposalWrapper.GenericJudgingStatus.STATUS_ACCEPTED)
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Determine if fellow are done with proposal
     *
     * @return 0 if fellow action is incomplete, 1 fellow action completed
     */
    public boolean getScreeningStatus() {
        try {
            ContestPhase contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(contest);
            for (Proposal proposal : ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhase.getPrimaryKey())) {
                Proposal2Phase p2p = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
                if ((new ProposalWrapper(proposal, proposal.getCurrentVersion(), contest, contestPhase, p2p)).getScreeningStatus() == ProposalWrapper.GenericJudgingStatus.STATUS_UNKNOWN)
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
