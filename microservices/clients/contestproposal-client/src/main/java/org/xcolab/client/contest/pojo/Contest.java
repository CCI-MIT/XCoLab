package org.xcolab.client.contest.pojo;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.ContestTeamMemberClient;
import org.xcolab.client.contest.ContestTeamMemberClientUtil;
import org.xcolab.client.contest.OntologyClient;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.helper.Tuple;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.contest.pojo.team.ContestTeamMember;
import org.xcolab.client.contest.pojo.team.ContestTeamMemberRole;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalMemberRatingClient;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Contest extends AbstractContest {

    private final ContestClient contestClient;

    private final ContestTeamMemberClient contestTeamMemberClient;

    private final OntologyClient ontologyClient;

    private final static Map<Long, FocusArea> faCache = new HashMap<>();
    private final Map<String, List<OntologyTerm>> ontologySpaceCache = new HashMap<>();

    protected static final String WHERE = "where";
    protected static final String WHAT = "what";
    protected static final String WHO = "who";
    protected static final String HOW = "how";
    private static final long ONTOLOGY_SPACE_ID_WHERE = 104L;
    private static final long ONTOLOGY_SPACE_ID_WHO = 102L;
    private static final long ONTOLOGY_SPACE_ID_WHAT = 103L;
    private static final long ONTOLOGY_SPACE_ID_HOW = 103L;
    private static final long CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS = 3L;
    private static final String EMAIL_TEMPLATE_URL = "/resources/-/wiki/Main/Judging+Mail+Templates";

    private Map<String, String> ontologyJoinedNames = new HashMap<>();
    private List<ContestPhase> visiblePhases;


    protected List<ContestPhase> phases;
    protected ContestType contestType;
    protected List<ContestTeamMemberRole> contestTeamMembersByRole;

    protected ContestPhase activePhase;

    private RestService restService;

    public Contest(Long contestId) {
        contestClient = ContestClientUtil.getClient();
        contestTeamMemberClient = ContestTeamMemberClientUtil.getClient();
        ontologyClient = OntologyClientUtil.getClient();
    }

    public Contest() {
        contestClient = ContestClientUtil.getClient();
        contestTeamMemberClient = ContestTeamMemberClientUtil.getClient();
        ontologyClient = OntologyClientUtil.getClient();
    }

    public Contest(Contest value) {
        super(value);
        if (value.getRestService() != null) {
            contestClient = ContestClient.fromService(restService);
            contestTeamMemberClient = ContestTeamMemberClient.fromService(restService);
            ontologyClient = OntologyClient.fromService(restService);
        } else {
            contestClient = ContestClientUtil.getClient();
            contestTeamMemberClient = ContestTeamMemberClientUtil.getClient();
            ontologyClient = OntologyClientUtil.getClient();
        }
    }

    public Contest(AbstractContest abstractContest, RestService restService) {
        super(abstractContest);
        this.restService = restService;
        contestClient = ContestClient.fromService(this.restService);
        contestTeamMemberClient = ContestTeamMemberClient.fromService(this.restService);
        ontologyClient = OntologyClient.fromService(this.restService);
    }

    public String getContestLinkUrl() {

        String link = "/";

        if(this.getIsSharedContestInForeignColab()){
            link += ContestClientUtil.getClient().getContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get())
                    .getFriendlyUrlStringContests();
        }else{
            link += contestClient.getContestType(this.getContestTypeId())
                    .getFriendlyUrlStringContests();
        }

        link += "/%d/%s";
        return String.format(link, this.getContestYear(), this.getContestUrlName());
    }
    public String getProposalLogoPath() {
        if(this.getIsSharedContestInForeignColab()) {
            return "http://"+ConfigurationAttributeKey.PARTNER_COLAB_LOCATION.get()+"/";
        }else{
            return "/";
        }
    }
    public String getSponsorLogoPath() {
        if(this.getIsSharedContestInForeignColab()) {

            Long i = this.getSponsorLogoId();
            if (i != null) {
                return "http://"+ConfigurationAttributeKey.PARTNER_COLAB_LOCATION.get()+"/image/contest?img_id=" + i;
            }
            return "";
        }else{
            Long i = this.getSponsorLogoId();
            if (i != null) {
                return "/image/contest?img_id=" + i;
            }
            return "";
        }
    }

    public String getLogoPath() {
        if(this.getIsSharedContestInForeignColab()) {

            Long i = this.getContestLogoId();
            if (i != null) {
                return "http://"+ConfigurationAttributeKey.PARTNER_COLAB_LOCATION.get()+"/image/contest?img_id=" + i;
            }
            return "";
        }else{
            Long i = this.getContestLogoId();
            if (i != null) {
                return "/image/contest?img_id=" + i;
            }
            return "";
        }
    }

    public boolean getShowInTileView(){
        return this.getShow_in_tile_view();
    }

    public boolean isShowInTileView(){
        return this.getShow_in_tile_view();
    }

    public void setShowInTileView(boolean showInTileView){
        this.setShow_in_tile_view(showInTileView);
    }

    public boolean getShowInListView(){
        return this.getShow_in_list_view();
    }

    public boolean isShowInListView(){
        return this.getShow_in_list_view();
    }

    public void setShowInListView(boolean showInListView){
        this.setShow_in_list_view(showInListView);
    }

    public boolean getShowInOutlineView(){
        return this.getShow_in_outline_view();
    }

    public boolean isShowInOutlineView(){
        return this.getShow_in_outline_view();
    }

    public void setShowInOutlineView(boolean showInOutlineView){
        this.setShow_in_outline_view(showInOutlineView);
    }

    public String generateContestUrlName() {
        String contestUrlName = this.getContestShortName().toLowerCase();
        return contestUrlName.replaceAll(" ", "-").replaceAll("[^a-z0-9-]", "");
    }

    public void persist() {
        contestClient.updateContest(this);
    }



    public boolean isContestActive() {
        return this.getContestActive();
    }

    public boolean getContestInVotingPhase() {
        ContestPhase phase = contestClient.getActivePhase(this.getContestPK());
        if (phase == null) {
            return false;
        }

        String status = phase.getContestStatusStr();
        return status != null && ContestStatus.valueOf(status).isCanVote();

    }


    public boolean isFeatured() {
        return this.getFeatured_();
    }

    public boolean isPlansOpenByDefault() {
        return this.getPlansOpenByDefault();
    }


    public boolean getSponsorLinkAvailable() {
        return !this.getSponsorLink().equals("");
    }


    public long getProposalsCount() {
        try {
            ContestPhase cp = contestClient.getActivePhase(this.getContestPK());
            if (cp != null) {
                //TODO:REPLACE THIS CALL FOR SYNCD CONTEST REFERENCE
                RestService proposalService =  restService.withServiceName(CoLabService.PROPOSAL.getServiceName());

                return ProposalPhaseClient.fromService(proposalService)
                        .getProposalCountForActiveContestPhase(cp.getContestPhasePK());
            }
        } catch (UncheckedEntityNotFoundException e) {
            //fall through - return 0
        }
        return 0L;
    }

        /*public long getTotalProposalsCount() {
            Set<Proposal> proposalList = new HashSet<>();
            try {
                List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil
                        .getPhasesForContest(contest);
                for (ContestPhase contestPhase : contestPhases) {
                    try {
                        List<Proposal> proposals = ProposalLocalServiceUtil
                                .getActiveProposalsInContestPhase(contestPhase.getContestPhasePK());
                        proposalList.addAll(proposals);
                    } catch (PortalException e) {
                        _log.error("Proposal count: failed to retrieve active proposals in contest phase " + contestPhase
                                .getContestPhasePK());
                    }
                }
            } catch (SystemException e) {
                throw new DatabaseAccessException(e);
            }
            return proposalList.size();
        }*/

    public long getCommentsCount() {
        RestService commentService =  restService.withServiceName(CoLabService.COMMENT.getServiceName());
        Integer contestComments = CommentClient.fromService(commentService).countComments(this.getDiscussionGroupId());
        //TODO: get each proposal comment count.
        return contestComments;
    }

    public List<OntologyTerm> getWho() {
        return getTermFromSpace(WHO);
    }

    public List<OntologyTerm> getWhat() {
        return getTermFromSpace(WHAT);
    }

    public List<OntologyTerm> getWhere() {
        return getTermFromSpace(WHERE);
    }

    public List<OntologyTerm> getHow() {
        return getTermFromSpace(HOW);
    }

    protected List<OntologyTerm> getTermFromSpace(String space) {




        if (!ontologySpaceCache.containsKey(space) && (getFocusAreaId() > 0)) {
            if (!faCache.containsKey(this.getFocusAreaId())) {
                FocusArea fa = ontologyClient.getFocusArea(this
                        .getFocusAreaId());
                if (fa == null) {
                    ontologySpaceCache.put(space, null);
                    return null;
                }
                faCache.put(fa.getId_(), fa);
            }
            List<OntologyTerm> terms = new ArrayList<>();
            for (OntologyTerm t : ontologyClient
                    .getOntologyTermsForFocusArea(faCache.get(this.getFocusAreaId()))) {
                if (ontologyClient.getOntologySpace(t.getOntologySpaceId()).getName()
                        .equalsIgnoreCase(space)) {
                    terms.add(t);
                }
            }
            ontologySpaceCache.put(space, terms.isEmpty() ? null : terms);
        }
        return ontologySpaceCache.get(space);

    }

    public List<ContestPhase> getPhases() {
        if (phases == null) {
            phases = new ArrayList<>();
            for (ContestPhase phase : contestClient.getAllContestPhases(this.getContestPK())) {
                phases.add(phase);
            }
        }
        return phases;
    }

    public List<ContestTeamMemberRole> getContestTeamMembersByRole() {

        if (contestTeamMembersByRole == null) {
            Map<Tuple<String, Integer>, List<Member>> teamRoleUsersMap = new HashMap<>();
            for (ContestTeamMember teamMember : contestTeamMemberClient

                    .getTeamMembers(this.getContestPK())) {
                try {
                    ContestTeamMemberRole role = contestTeamMemberClient
                            .getContestTeamMemberRole(teamMember.getRoleId());
                    List<Member> roleUsers = teamRoleUsersMap
                            .get(new Tuple<>(role.getRole(), role.getSort()));
                    if (roleUsers == null) {
                        roleUsers = new ArrayList<>();
                        teamRoleUsersMap
                                .put(new Tuple<>(role.getRole(), role.getSort()), roleUsers);
                    }
                    roleUsers.add(MembersClient.getMember(teamMember.getUserId()));
                } catch (MemberNotFoundException e) {
                    //_log.warn("Contest team member " + teamMember.getUserId() + " does not have an account");
                }
            }
            contestTeamMembersByRole = new ArrayList<>(teamRoleUsersMap.size());
            for (Map.Entry<Tuple<String, Integer>, List<Member>> entry : teamRoleUsersMap.entrySet()) {
                final Tuple<String, Integer> role = entry.getKey();
                contestTeamMembersByRole.add(new ContestTeamMemberRole(role.getLeft(), entry.getValue(), role.getRight()));
            }
            Collections.sort(contestTeamMembersByRole);

        }
        return contestTeamMembersByRole;
    }

    public boolean getHasUserRoleInContest(long memberId, String role) {

        for (ContestTeamMemberRole c : getContestTeamMembersByRole()) {
            if (c.getRoleName().equalsIgnoreCase(role)) {
                for (Member user : c.getUsers()) {
                    if (user.getUserId() == memberId) {
                        return true;
                    }
                }
            }
        }

        return false;
    }



    public ContestPhase getActivePhase() {
        if (activePhase == null) {
            ContestPhase phase = contestClient.getActivePhase(this.getContestPK());
            if (phase == null) {
                return null;
            }
            activePhase =phase;
        }
        return activePhase;
    }

    public List<Member> getContestJudges() {
        List<Member> judges = null;
        for (ContestTeamMemberRole c : getContestTeamMembersByRole()) {
            if (c.getRoleName().equalsIgnoreCase("Judge")) {
                judges = c.getUsers();
            }
        }
        if (judges == null) {
            return Collections.emptyList();
        }
        return judges;
    }

    public List<Member> getContestFellows() {
        List<Member> fellows = null;
        for (ContestTeamMemberRole c : getContestTeamMembersByRole()) {
            if (c.getRoleName().equalsIgnoreCase("Fellow")) {
                fellows = c.getUsers();
            }
        }
        return fellows;
    }

    public List<Member> getContestAdvisors() {
        List<Member> advisors = null;
        for (ContestTeamMemberRole c : getContestTeamMembersByRole()) {
            if (c.getRoleName().equalsIgnoreCase("Advisor")||c.getRoleName().equalsIgnoreCase("Curator")) {
                advisors = c.getUsers();
            }
        }
        if (advisors == null) {
            return Collections.emptyList();
        }
        return advisors;
    }

    public long getTotalProposalsCount() {
        Set<Proposal> proposalList = new HashSet<>();

        List<ContestPhase> contestPhases = contestClient.getAllContestPhases(this.getContestPK());
        RestService proposalService =  restService.withServiceName(CoLabService.PROPOSAL.getServiceName());
        for (ContestPhase contestPhase : contestPhases) {
            List<Proposal> proposals = ProposalClient.fromService(proposalService)
                    .getActiveProposalsInContestPhase(contestPhase.getContestPhasePK());
            proposalList.addAll(proposals);

        }

        return proposalList.size();
    }

    public ContestType getContestType() {
        if (contestType == null) {
            contestType = contestClient.getContestType(this.getContestTypeId());
        }
        return contestType;
    }

    public Contest getWrapped() {
        return this;
    }

    public String getContestUrl() {
        return this.getContestLinkUrl();
    }


    public String getResourceArticleUrl() {
        if(this.getIsSharedContestInForeignColab()) {
            return "http://"+ConfigurationAttributeKey.PARTNER_COLAB_LOCATION.get()+"/web/guest/wiki/-/wiki/resources/" + this.getContestYear()
                    + "/" + this.getContestUrlName();
        } else{
            return "/web/guest/wiki/-/wiki/resources/" + this.getContestYear()
                    + "/" + this.getContestUrlName();
        }
    }

    @Override
    public String getEmailTemplateUrl() {
        if (super.getEmailTemplateUrl().isEmpty()) {
            return EMAIL_TEMPLATE_URL;
        } else {
            return super.getEmailTemplateUrl();
        }
    }
    public long getTotalCommentsCount() {

        RestService commentService =  restService.withServiceName(CoLabService.COMMENT.getServiceName());


        Integer contestComments = CommentClient.fromService(commentService).countComments(this.getDiscussionGroupId());
        ContestPhase phase = contestClient.getActivePhase(this.getContestPK());
        contestComments += CommentClient.fromService(commentService).countCommentsInContestPhase(
                phase.getContestPhasePK(), phase.getContestPK());

        return contestComments;
    }

    public long getVotesCount() {
        ContestPhase phase = contestClient.getActivePhase(this.getContestPK());
        RestService proposalMemberRatingService =  restService.withServiceName(CoLabService.PROPOSAL.getServiceName());

        return ProposalMemberRatingClient.fromService(proposalMemberRatingService).countProposalVotesInContestPhase(phase.getContestPhasePK());
    }

    public long getCreatedTime(){
        if (this.getCreated() != null) {
            return this.getCreated().getTime();
        }
        else if (this.getUpdated() != null) {
            return this.getUpdated().getTime();
        }
        return 0;
    }

    public ContestPhase getLastPhase() {
        ContestPhase last = null;
        for (ContestPhase ph : getPhases()) {
            if (last == null || (ph.getPhaseReferenceDate() != null
                    && ph.getPhaseReferenceDate().compareTo(last.getPhaseReferenceDate()) > 0)) {
                last = ph;
            }
        }
        return last;
    }


    public RestService getRestService() {
        return restService;
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
        return this.getContestTier() == CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS - 1;
    }

    public List<Contest> getSubContests() {
        List <Contest> subContests = contestClient
                .getSubContestsByOntologySpaceId(this.getContestPK(), ONTOLOGY_SPACE_ID_WHERE);
        Collections.sort(subContests, new Comparator<Contest>() {
            @Override
            public int compare(Contest c1, Contest c2) {
                return c1.getWeight() - c2.getWeight();
            }
        });
        return subContests;
    }

    public Contest getParentContest() {

        List<OntologyTerm> list = ontologyClient.getAllOntologyTermsFromFocusAreaWithOntologySpace(
                ontologyClient.getFocusArea(this.getFocusAreaId()), ontologyClient.getOntologySpace(ONTOLOGY_SPACE_ID_WHERE));
        List<Long> focusAreaOntologyTermIds = new ArrayList<>();
        for(OntologyTerm ot: list){
            focusAreaOntologyTermIds.add(ot.getId_());
        }

        List<Contest> contests = contestClient
                .findContestsTierLevelAndOntologyTermIds(CONTEST_TIER_FOR_SHOWING_SUB_CONTESTS, focusAreaOntologyTermIds);
        return contests.get(0);
    }

    public Long getVotingPhasePK() {
        ContestPhase lastVotingPhase = null;
        for (ContestPhase ph : getPhases()) {
            final ContestPhaseType contestPhaseType = ph.getContestPhaseTypeObject();
            if (contestPhaseType != null && "VOTING".equals(
                    contestPhaseType.getStatus())) {
                lastVotingPhase = ph;
            }
        }
        return lastVotingPhase != null ? lastVotingPhase.getContestPhasePK() : 0;
    }

    public boolean isContestCompleted(ContestPhase contestPhase) {
        ContestPhaseType type;
        ContestPhase activePhase;
        try {
            activePhase = contestClient.getActivePhase(this.getContestPK());
            type = activePhase.getContestPhaseTypeObject();
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
        return !(type == null || activePhase == null
                || contestPhase.getContestPhasePK() != activePhase.getContestPhasePK()
        ) && ("COMPLETED".equals(type.getStatus()));
    }

    public List<ContestPhase> getVisiblePhases() {
        if (visiblePhases == null) {
            visiblePhases = new ArrayList<>();
            for (ContestPhase phase : contestClient
                    .getVisibleContestPhases(this.getContestPK())) {
                visiblePhases.add(phase);
            }
        }
        return visiblePhases;
    }

    public boolean getHasFocusArea() {
        return this.getFocusAreaId() > 0;
    }

    public boolean isUserAmongAdvisors(Member memberInQuestion) {
        for (Member judge : getContestAdvisors()) {
            if (judge.getUserId() == memberInQuestion.getUserId()) {
                return true;
            }
        }
        return false;
    }


    public boolean getIsSharedContestInForeignColab(){
        return this.getIsSharedContest() && !ConfigurationAttributeKey.COLAB_NAME.get().equals(this.getSharedOrigin());
    }
    /**
     * Determine if judges are done with proposal
     *
     * @return 0 if judge action is incomplete, 1 judge actions completed
     */
    public boolean getJudgeStatus() {
        try {

            RestService proposalsService =  restService.withServiceName(CoLabService.PROPOSAL.getServiceName());



            ContestPhase contestPhase = contestClient.getActivePhase(this.getContestPK());
            for (Proposal proposal : ProposalClient.fromService(proposalsService).getProposalsInContestPhase(contestPhase.getContestPhasePK())) {
                Proposal2Phase p2p = ProposalPhaseClient.fromService(proposalsService).getProposal2PhaseByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
                /*
                final Proposal proposalWrapper =
                        new ProposalWrapper(proposal, proposal.getCurrentVersion(), this,
                                contestPhase, p2p);
                if (proposalWrapper.getJudgeStatus() == GenericJudgingStatus.STATUS_ACCEPTED) {
                    return false;
                }
                */
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
            RestService proposalsService =  restService.withServiceName(CoLabService.PROPOSAL.getServiceName());


            ContestPhase contestPhase = contestClient.getActivePhase(this.getContestPK());

            for (Proposal proposal : ProposalClient.fromService(proposalsService).getProposalsInContestPhase(contestPhase.getContestPhasePK())) {
                Proposal2Phase p2p = ProposalPhaseClient.fromService(proposalsService).getProposal2PhaseByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
                /*
                if ((new ProposalWrapper(proposal, proposal.getCurrentVersion(), this, contestPhase, p2p)).getScreeningStatus() == GenericJudgingStatus.STATUS_UNKNOWN) {
                    return false;
                }*/
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getNewProposalLinkUrl() {
        if(getIsSharedContestInForeignColab()){
            final String portletUrl = ContestClientUtil.getClient().getContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get())
                    .getPortletUrl();
            return String.format("%s/%s/%s/createProposal",
                    portletUrl, this.getContestYear(), this.getContestUrlName());
        }else {
            final String portletUrl = getContestType().getPortletUrl();
            return String.format("%s/%s/%s/createProposal",
                    portletUrl, this.getContestYear(), this.getContestUrlName());
        }
    }

}
