package org.xcolab.client.proposals.pojo;

import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.ContestTeamMemberClient;
import org.xcolab.client.contest.ContestTeamMemberClientUtil;
import org.xcolab.client.contest.PlanTemplateClient;
import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.UsersGroupsClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.UsersGroups;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.client.proposals.MembershipClient;
import org.xcolab.client.proposals.MembershipClientUtil;
import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalJudgeRatingClient;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.ProposalMemberRatingClient;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.helpers.ProposalAttributeHelper;
import org.xcolab.client.proposals.helpers.ProposalContestPhaseAttributeHelper;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.client.proposals.pojo.proposals.ProposalRatings;
import org.xcolab.client.proposals.pojo.proposals.ProposalRibbon;
import org.xcolab.client.proposals.pojo.team.MembershipRequest;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.membershiprequest.MembershipRequestStatus;
import org.xcolab.util.enums.modeling.ModelRegions;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.util.enums.promotion.GenericJudgingStatus;
import org.xcolab.util.enums.promotion.JudgingSystemActions;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestService;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Proposal extends AbstractProposal {

    private static final Long LONG_DEFAULT_VAL = -1L;
    private static final String STRING_DEFAULT_VAL = "";

    private final ContestClient contestClient;
    private final ProposalClient proposalClient;
    private final CommentClient commentClient;

    private final ProposalMemberRatingClient proposalMemberRatingClient;
    private final ProposalJudgeRatingClient proposalJudgeRatingClient;

    private final MembershipClient membershipClient;

    private final ProposalAttributeClient proposalAttributeClient;
    private final ProposalPhaseClient proposalPhaseClient;
    private final ContestTeamMemberClient contestTeamMemberClient;
    private final PlanTemplateClient planTemplateClient;


    private RestService restService;

    protected final Contest contest;

    protected final ContestPhase contestPhase;
    protected final Proposal2Phase proposal2Phase;
    protected final ProposalContestPhaseAttributeHelper proposalContestPhaseAttributeHelper;
    protected final ProposalAttributeHelper proposalAttributeHelper;

    protected List<ProposalTeamMember> members;
    private List<PlanSectionDefinition> sections;


    protected ProposalRatings proposalRatings;
    private ProposalRibbon ribbonWrapper;
    private List<MembershipRequest> membershipRequests;
    private List<Member> supporters;

    public Proposal(Proposal proposal, ContestPhase contestPhase) {
        this(proposal, proposal.getCurrentVersion(), null, contestPhase, null);
    }

    public Proposal(Proposal proposal, int version) {
        this(proposal, version, null, null, null);
    }


    public Proposal() {
        contestClient = ContestClientUtil.getClient();
        proposalClient = ProposalClientUtil.getClient();
        proposalAttributeClient = ProposalAttributeClientUtil.getClient();
        proposalPhaseClient = ProposalPhaseClientUtil.getClient();
        contestTeamMemberClient = ContestTeamMemberClientUtil.getClient();
        commentClient = CommentClientUtil.getClient();
        proposalMemberRatingClient = ProposalMemberRatingClientUtil.getClient();
        membershipClient = MembershipClientUtil.getClient();
        planTemplateClient = PlanTemplateClientUtil.getClient();
        proposalJudgeRatingClient = ProposalJudgeRatingClientUtil.getClient();

        this.setProposalId(0L);
        this.setCurrentVersion(0);
        this.contestPhase =  fetchContestPhase();
        this.contest =  fetchContest(contestPhase);
        this.proposal2Phase = fetchProposal2Phase();
        proposalContestPhaseAttributeHelper =
                new ProposalContestPhaseAttributeHelper(this, contestPhase);
        proposalAttributeHelper = new ProposalAttributeHelper(this, this.getVersion(),proposalAttributeClient);
    }

    public Proposal(Proposal value) {
        super(value);
        if (value.getRestService() != null) {
            this.restService = value.getRestService();
            RestService contestService =  restService.withServiceName(CoLabService.CONTEST.getServiceName());
            contestClient = ContestClient.fromService(contestService);
            planTemplateClient = PlanTemplateClient.fromService(contestService);
            proposalClient = ProposalClient.fromService(restService);
            proposalAttributeClient = ProposalAttributeClient.fromService(restService);
            proposalPhaseClient = ProposalPhaseClient.fromService(restService);

            contestTeamMemberClient =  ContestTeamMemberClient.fromService(contestService);

            RestService commentService =  restService.withServiceName(CoLabService.COMMENT.getServiceName());
            commentClient = CommentClient.fromService(commentService);
            proposalMemberRatingClient = ProposalMemberRatingClient.fromService(restService);
            proposalJudgeRatingClient = ProposalJudgeRatingClient.fromService(restService);
            membershipClient = MembershipClient.fromService(restService);
        } else {
            contestClient = ContestClientUtil.getClient();
            proposalClient = ProposalClientUtil.getClient();
            proposalAttributeClient = ProposalAttributeClientUtil.getClient();
            proposalPhaseClient = ProposalPhaseClientUtil.getClient();
            contestTeamMemberClient = ContestTeamMemberClientUtil.getClient();
            planTemplateClient = PlanTemplateClientUtil.getClient();
            commentClient = CommentClientUtil.getClient();
            proposalMemberRatingClient = ProposalMemberRatingClientUtil.getClient();
            membershipClient = MembershipClientUtil.getClient();
            proposalJudgeRatingClient = ProposalJudgeRatingClientUtil.getClient();
        }

        this.contestPhase =  fetchContestPhase();
        this.contest =  fetchContest(contestPhase);
        this.proposal2Phase = fetchProposal2Phase();

        proposalContestPhaseAttributeHelper =
                new ProposalContestPhaseAttributeHelper(this, contestPhase);

        proposalAttributeHelper = new ProposalAttributeHelper(this, this.getVersion(),proposalAttributeClient);

    }


    public Proposal(Proposal proposal, ContestPhase contestPhase, Proposal2Phase proposal2Phase) {
        this(proposal, proposal.getCurrentVersion(), null, contestPhase, proposal2Phase);
    }

    public Proposal(Proposal proposal, int version, Contest contest,
            ContestPhase contestPhase, Proposal2Phase proposal2Phase) {
        super(proposal);

        if (proposal.getRestService() != null) {
            this.restService = proposal.getRestService();
            RestService contestservice =  restService.withServiceName("contest-service");
            planTemplateClient = PlanTemplateClient.fromService(contestservice);
            contestClient = ContestClient.fromService(contestservice);
            proposalClient = ProposalClient.fromService(restService);;
            proposalAttributeClient = ProposalAttributeClient.fromService(restService);
            proposalPhaseClient = ProposalPhaseClient.fromService(restService);
            contestTeamMemberClient =  ContestTeamMemberClient.fromService(contestservice);

            RestService commentService =  restService.withServiceName(CoLabService.COMMENT.getServiceName());
            commentClient = CommentClient.fromService(commentService);
            proposalMemberRatingClient = ProposalMemberRatingClient.fromService(restService);
            membershipClient = MembershipClient.fromService(restService);
            proposalJudgeRatingClient = ProposalJudgeRatingClient.fromService(restService);
        } else {
            contestClient = ContestClientUtil.getClient();
            proposalClient = ProposalClientUtil.getClient();
            proposalAttributeClient = ProposalAttributeClientUtil.getClient();
            proposalPhaseClient = ProposalPhaseClientUtil.getClient();
            contestTeamMemberClient = ContestTeamMemberClientUtil.getClient();
            planTemplateClient = PlanTemplateClientUtil.getClient();
            commentClient = CommentClientUtil.getClient();
            proposalMemberRatingClient = ProposalMemberRatingClientUtil.getClient();
            proposalJudgeRatingClient = ProposalJudgeRatingClientUtil.getClient();
            membershipClient = MembershipClientUtil.getClient();
        }
        this.contest = contest == null ? fetchContest(contestPhase) : contest;
        this.contestPhase = contestPhase == null ? fetchContestPhase() : contestPhase;
        this.proposal2Phase = proposal2Phase == null ? fetchProposal2Phase() : proposal2Phase;

        proposalContestPhaseAttributeHelper =
                new ProposalContestPhaseAttributeHelper(this, this.contestPhase);
        proposalAttributeHelper = new ProposalAttributeHelper(this, this.getVersion(), proposalAttributeClient);
    }


    public Proposal(Long proposalid, Timestamp createdate, Timestamp updateddate,
            Integer currentversion, Long authorid, Boolean visible, Long discussionid,
            Long resultsdiscussionid, Long judgediscussionid, Long fellowdiscussionid,
            Long advisordiscussionid, Long groupid) {
        super(proposalid, createdate, updateddate, currentversion, authorid, visible, discussionid,
                resultsdiscussionid, judgediscussionid, fellowdiscussionid,
                advisordiscussionid, groupid);
        contestClient = ContestClientUtil.getClient();
        proposalClient = ProposalClientUtil.getClient();
        proposalAttributeClient = ProposalAttributeClientUtil.getClient();
        proposalPhaseClient = ProposalPhaseClientUtil.getClient();
        contestTeamMemberClient = ContestTeamMemberClientUtil.getClient();
        commentClient = CommentClientUtil.getClient();
        proposalMemberRatingClient = ProposalMemberRatingClientUtil.getClient();
        proposalJudgeRatingClient = ProposalJudgeRatingClientUtil.getClient();
        membershipClient = MembershipClientUtil.getClient();
        planTemplateClient = PlanTemplateClientUtil.getClient();
        this.contestPhase =  fetchContestPhase();
        this.contest =  fetchContest(contestPhase);
        this.proposal2Phase = fetchProposal2Phase();


        proposalContestPhaseAttributeHelper =
                new ProposalContestPhaseAttributeHelper(this, contestPhase);
        proposalAttributeHelper = new ProposalAttributeHelper(this, this.getVersion(), proposalAttributeClient);
    }



    public Proposal(RestService restService) {
        this();
        this.restService = restService;
    }

    public Proposal(AbstractProposal abstractProposal, RestService restService) {
        super(abstractProposal);
        this.restService = restService;

        RestService contest =  restService.withServiceName(CoLabService.CONTEST.getServiceName());
        contestClient = ContestClient.fromService(contest);
        planTemplateClient = PlanTemplateClient.fromService(contest);
        proposalClient = ProposalClient.fromService(restService);
        proposalAttributeClient = ProposalAttributeClient.fromService(restService);
        proposalPhaseClient = ProposalPhaseClient.fromService(restService);
        contestTeamMemberClient =  ContestTeamMemberClient.fromService(contest);

        RestService commentService =  restService.withServiceName(CoLabService.COMMENT.getServiceName());
        commentClient = CommentClient.fromService(commentService);

        proposalMemberRatingClient = ProposalMemberRatingClient.fromService(restService);
        proposalJudgeRatingClient = ProposalJudgeRatingClient.fromService(restService);
        membershipClient = MembershipClient.fromService(restService);


        this.contestPhase =  fetchContestPhase();
        this.contest =  fetchContest(contestPhase);
        this.proposal2Phase = fetchProposal2Phase();

        proposalContestPhaseAttributeHelper =
                new ProposalContestPhaseAttributeHelper(this, contestPhase);
        proposalAttributeHelper = new ProposalAttributeHelper(this, this.getVersion(), proposalAttributeClient);
    }

    private Contest fetchContest(ContestPhase contestPhase) {
        try {
            if (contestPhase != null) {
                return contestClient.getContest(contestPhase.getContestPK());
            }
            if (this.getProposalId() != null && this.getProposalId() != 0L) {
                return proposalClient.getCurrentContestForProposal(this.getProposalId());
            } else {
                return null;
            }
        } catch (ContestNotFoundException e) {
            throw new IllegalStateException("Could not find a contest for proposal "
                    + this.getProposalId(), e);
        }
    }

    private ContestPhase fetchContestPhase() {
        if (proposal2Phase != null) {
            return contestClient.getContestPhase(proposal2Phase.getContestPhaseId());
        }
        if (contest != null) {
            return contestClient.getActivePhase(contest.getContestPK());
        }
        //_log.error(String.format("Could not get contest phase for proposal %d", this.getProposalId()));
        return null;
    }

    private Proposal2Phase fetchProposal2Phase() {
        if (this.getProposalId() == null || this.getProposalId() == 0 || contestPhase == null || contestPhase.getContestPhasePK() == 0) {
            return null;
        }
        try {
            return proposalPhaseClient.getProposal2PhaseByProposalIdContestPhaseId(this.getProposalId(), contestPhase.getContestPhasePK());
        } catch (Proposal2PhaseNotFoundException e) {
            //_log.warn(String.format("Could not fetch p2p for proposal %d, contest phase %d",

        }
        return null;
    }

    public String getProposalLinkUrl(Contest contest) {
        return getProposalLinkUrl(contest, 0L);
    }

    public String getProposalLinkUrl(Contest contest, long contestPhaseId) {
        Long proposalId = this.getProposalId();
        ContestType contestType;
        if (contest.getIsSharedContestInForeignColab()) {
            contestType = ContestClientUtil.getClient()
                    .getContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
        } else {
            contestType = contestClient.getContestType(contest.getContestTypeId());
        }
        String link = "/";
        link += contestType.getFriendlyUrlStringContests();

        String friendlyUrlStringProposal = contestType.getFriendlyUrlStringProposal();

        if (contestPhaseId > 0) {
            long activePhaseId =
                    contestClient.getActivePhase(contest.getContestPK()).getContestPhasePK();
            if (activePhaseId == contestPhaseId) {
                link += "/%d/%s/c/" + friendlyUrlStringProposal + "/%d";
                return String.format(link, contest.getContestYear(), contest.getContestUrlName(),
                        proposalId);
            }

            link += "/%d/%s/phase/%d/" + friendlyUrlStringProposal + "/%d";
            return String.format(link, contest.getContestYear(), contest.getContestUrlName(),
                    contestPhaseId, proposalId);
        }

        link += "/%d/%s/c/" + friendlyUrlStringProposal + "/%d";
        return String
                .format(link, contest.getContestYear(), contest.getContestUrlName(), proposalId);
    }

    public boolean isDeleted() {
        if (this.getProposalId() == 0) {
            return false;
        }
        final ContestPhase contestPhase = contestClient.getContestPhase(
                proposalClient.getLatestContestPhaseIdInProposal(this.getProposalId()));
        long visibleAttributeValue = 1;
        if (contestPhase != null) {
            ProposalContestPhaseAttribute pcpa = proposalPhaseClient
                    .getProposalContestPhaseAttribute(this.getProposalId(),
                            contestPhase.getContestPhasePK(),
                            ProposalContestPhaseAttributeKeys.VISIBLE);
            if (pcpa != null) {
                visibleAttributeValue = pcpa.getNumericValue();
            }
        }
        return !this.getVisible() || visibleAttributeValue == 0;

    }

    @Override
    public Long getFellowDiscussionId() {
        long fellowDiscussionId = super.getFellowDiscussionId();
        if (fellowDiscussionId == 0) {
            CommentThread commentThread = new CommentThread();
            commentThread.setAuthorId(this.getAuthorId());
            commentThread.setIsQuiet(true);
            commentThread.setTitle(this.getProposalId() + "_fellowReview");
            commentThread = ThreadClientUtil.createThread(commentThread);
            fellowDiscussionId =  commentThread.getThreadId();
            this.setFellowDiscussionId(fellowDiscussionId);
            proposalClient.updateProposal(this);

        }
        return fellowDiscussionId;
    }


    public String getPitch() {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.PITCH, "");
    }

    public String getName() {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.NAME, "");
    }

    public String getDescription() {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.DESCRIPTION, "");
    }

    public boolean isUserAmongFellows(long memberId) {
        for (Long fellowId : contestTeamMemberClient.getFellowsForContest(contest.getContestPK())) {
            if (fellowId == memberId) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserAmongJudges(long memberId) {
        for (Long judge : contestTeamMemberClient.getJudgesForContest(contest.getContestPK())) {
            if (judge == memberId) {
                return true;
            }
        }
        return false;
    }

    public boolean isJudgingContestPhase() {
        return ContestPhasePromoteType.getPromoteType(contestPhase.getContestPhaseAutopromote())
                == ContestPhasePromoteType.PROMOTE_JUDGED;
    }

    public String getTeam() {
        return proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.TEAM, "");
    }

    public Member getAuthor() {
        try {
            return MembersClient.getMember(this.getAuthorId());
        } catch (MemberNotFoundException e) {
            throw new IllegalStateException("Author " + this.getAuthorId()
                    + " of proposal " + this.getProposalId() + " does not exist");
        }
    }

    public long getSupportersCount() {
        return proposalMemberRatingClient.getProposalSupportersCount(this.getProposalId());
    }

    public long getCommentsCount() {
        if (this.getProposalId() > 0) {
            return commentClient.countComments(this.getDiscussionId());
        }
        return 0;
    }

    public long getFellowReviewCommentsCount() {
        if (this.getProposalId() > 0) {
            return commentClient.countComments(this.getFellowDiscussionId());
        }
        return 0;
    }

    public long getEvaluationCommentsCount() {
        return 0;
    }

    public Date getLastModifiedDate() {
        return this.getUpdatedDate();
    }

    public Date getLastModifiedDateForContestPhase() {
        if (proposal2Phase.getVersionTo() == -1) {
            return getLastModifiedDate();
        }
        return proposalClient
                .getProposalVersionByProposalIdVersion(this.getProposalId(), this.getVersion()).getCreateDate();

    }


    public boolean isOpen() {
        if (this.getProposalId() > 0) {
            ProposalAttribute attribute = proposalAttributeClient
                    .getProposalAttribute(this.getProposalId(), ProposalAttributeKeys.OPEN, 0L);
            return attribute != null && attribute.getNumericValue() > 0;
        } else {
            return false;
        }
    }

    public long getImageId() {
        return proposalAttributeHelper.getAttributeValueLong(ProposalAttributeKeys.IMAGE_ID, 0L, 0);
    }

    public String getProposalUrl() {
        return this.getProposalLinkUrl(contest);
    }

    public String getProposalUrl(ContestPhase inPhase) {
        return this.getProposalLinkUrl(contest, inPhase.getContestPhasePK());
    }

    public List<Member> getSupporters() {
        if (supporters == null) {
            supporters = proposalMemberRatingClient.getProposalSupporters(getProposalId()).stream()
                    .map(supporter -> MembersClient.getMemberOrNull(supporter.getUserId()))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return supporters;
    }


    public String getAuthorName() {
        String authorName = getTeam();
        if (StringUtils.isBlank(authorName)) {
            authorName = getAuthor().getScreenName();
        }
        return authorName;
    }

    public Contest getContest() {
        return contest;
    }

    public boolean getIsLatestVersion() {
        return getCurrentVersion() == this.getVersion();
    }

    public Contest getWasMovedToContest() {
        try {
            Contest c = proposalClient.getCurrentContestForProposal(this.getProposalId());
            if (c.getContestPK() != contest.getContestPK().longValue()) {
                try {
                    return contestClient.getContest(c.getContestPK());
                } catch (ContestNotFoundException ignored) {
                }

            }
            return null;
        } catch (ContestNotFoundException e) {
            return null;
        }
    }

    public ProposalVersion getSelectedVersion() {
        for (ProposalVersion pv : proposalClient.getAllProposalVersions(this.getProposalId())) {
            if (pv.getVersion() == this.getVersion()) {
                return pv;
            }
        }
        return null;
    }

    public ProposalAttributeHelper getProposalAttributeHelper() {
        return proposalAttributeHelper;
    }

    public int getVersion() {
        return this.getCurrentVersion();
    }

    public long getContestPK() {
        return contest.getContestPK();
    }

    public Proposal getWrapped() {
        return this;
    }

    public boolean isVisible() {
        return !this.isDeleted();
    }

    public boolean isVisibleInContest(long contestId) {
        try {
            if (this.getProposalId() == 0) {
                return true;
            }
            final Contest currentContest =
                    proposalClient.getCurrentContestForProposal(this.getProposalId());
            return !isDeleted() && currentContest.getContestPK() == contestId;
        } catch (ContestNotFoundException ignored) {
            return false;
        }
    }

    public ContestPhase getContestPhase() {

        return contestPhase;
    }

    public List<ProposalTeamMember> getMembers() {
        if (members == null) {
            members = new ArrayList<>();
            boolean hasOwner = false;
            RestService membersService = restService.withServiceName(CoLabService.MEMBER.getServiceName());
            for (UsersGroups user : UsersGroupsClient.fromService(membersService).getUserGroupsByUserIdGroupId(null, this.getGroupId())) {

                try {
                    Member member = MembersClient.getMember(user.getUserId());

                    final ProposalTeamMember teamMemberWrapper = new ProposalTeamMember(
                            this, member);
                    members.add(teamMemberWrapper);
                    if (teamMemberWrapper.getMemberType().equalsIgnoreCase("owner")) {
                        hasOwner = true;
                    }
                } catch (MemberNotFoundException ignored) {

                }
            }
            if (!hasOwner) {
                UsersGroups usersGroups = new UsersGroups();
                usersGroups.setGroupId(this.getGroupId());
                usersGroups.setUserId(this.getAuthorId());
            }

        }

        return members;
    }

    public boolean isFeatured() {
        return getRibbonWrapper().getRibbon() > 0;
    }

    public JudgingSystemActions.AdvanceDecision getJudgeDecision() {
        long judgingDecision = proposalContestPhaseAttributeHelper.getAttributeLongValue(ProposalContestPhaseAttributeKeys.JUDGE_DECISION, 0, LONG_DEFAULT_VAL);
        return JudgingSystemActions.AdvanceDecision.fromInt((int) judgingDecision);
    }

    public JudgingSystemActions.FellowAction getFellowAction() {
        Long action = proposalContestPhaseAttributeHelper.getAttributeLongValue(ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0, LONG_DEFAULT_VAL);
        return JudgingSystemActions.FellowAction.fromInt(action.intValue());
    }

    public String getFellowActionComment() {
        return proposalContestPhaseAttributeHelper.getAttributeStringValue(ProposalContestPhaseAttributeKeys.FELLOW_ACTION_COMMENT, 0, STRING_DEFAULT_VAL);
    }

    public String getProposalReview() {
        return proposalContestPhaseAttributeHelper.getAttributeStringValue(ProposalContestPhaseAttributeKeys.PROPOSAL_REVIEW, 0, STRING_DEFAULT_VAL);
    }

    public List<Long> getSelectedJudges() {
        List<Long> selectedJudges = new ArrayList<>();


        // All judges are selected when screening is disabled
        if (!contestPhase.getFellowScreeningActive()) {
            for (Long judge : contestTeamMemberClient.getJudgesForContest(contest.getContestPK())) {

                selectedJudges.add(judge);
            }
        } else {
            String s = proposalContestPhaseAttributeHelper.getAttributeStringValue(ProposalContestPhaseAttributeKeys.SELECTED_JUDGES, 0, STRING_DEFAULT_VAL);
            if (s.isEmpty()) {
                return selectedJudges;
            }
            for (String element : s.split(";")) {
                selectedJudges.add(Long.parseLong(element));
            }
        }

        return selectedJudges;
    }

    public List<Member> getSelectedJudgeUsers() {
        List<Member> selectedJudges = new ArrayList<>();

        // All judges are selected when screening is disabled
        if (!contestPhase.getFellowScreeningActive()) {
            for (Long judgeId : contestTeamMemberClient.getJudgesForContest(contest.getContestPK())) {

                try {
                    Member judge = MembersClient.getMember(judgeId);
                    selectedJudges.add(judge);
                } catch (MemberNotFoundException ignored) {

                }
            }
        } else {
            String s = proposalContestPhaseAttributeHelper.getAttributeStringValue(ProposalContestPhaseAttributeKeys.SELECTED_JUDGES, 0, STRING_DEFAULT_VAL);
            if (s.isEmpty()) {
                return selectedJudges;
            }
            for (String element : s.split(";")) {
                try {
                    Member judge = MembersClient.getMember(Long.parseLong(element));
                    selectedJudges.add(judge);
                } catch (MemberNotFoundException ignored) {

                }
            }
        }
        return selectedJudges;
    }

    public boolean isUserAmongSelectedJudge(Member user) {
        if (!getFellowScreeningNecessary()) {
            return isUserAmongJudges(user.getUserId());
        }

        for (Long userId : getSelectedJudges()) {
            if (userId == user.getUserId()) {
                return true;
            }
        }
        return false;
    }

    public long getVotesCount() {
        return getVotesCountInternal(CacheName.NONE);
    }

    public long getVotesCountFromCache() {
        return getVotesCountInternal(CacheName.MEMBER_RATING);
    }

    private long getVotesCountInternal(CacheName cacheName) {
        if (this.getProposalId() > 0) {

            long votingPhasePK = contest.getVotingPhasePK();
            return proposalMemberRatingClient.countProposalVotesInContestPhaseProposalId(
                    votingPhasePK, this.getProposalId(), cacheName);
        }
        return 0;
    }

    public List<PlanSectionDefinition> getSections() {
        if (sections == null) {
            sections = new ArrayList<>();
            if (contest != null) {
                PlanTemplate planTemplate = planTemplateClient.getPlanTemplate(contest.getPlanTemplateId());
                if (planTemplate != null) {
                    for (PlanSectionDefinition psd : planTemplateClient
                            .getPlanSectionDefinitionByPlanTemplateId(planTemplate.getId_(),true)) {

                        sections.add(new PlanSectionDefinition(psd, this));
                    }
                }
            }
        }
        return sections;
    }

    protected boolean getFellowScreeningNecessary() {
        return contestPhase.getFellowScreeningActive();
    }

    public List<MembershipRequest> getMembershipRequests() {
        if (this.membershipRequests == null) {
            membershipRequests = new ArrayList<>();
            for (MembershipRequest m : membershipClient.getMembershipRequests(this.getProposalId())) {
                if (m.getStatusId() == MembershipRequestStatus.STATUS_PENDING_REQUESTED) {
                    membershipRequests.add((m));
                }
            }
        }
        return this.membershipRequests;
    }

    public List<String[]> getAllModelRegions() {
        List<String[]> modelRegions = new ArrayList<>();
        for (ModelRegions modelRegion : ModelRegions.values()) {
            modelRegions.add(new String[]{modelRegion.getModelRegionName(), modelRegion.getModelRegionTitle()});
        }
        return modelRegions;
    }

    public String getModelRegion() {
        ProposalAttribute attr = proposalAttributeHelper.getAttributeOrNull(ProposalAttributeKeys.REGION);
        if (attr == null) {
            return "";
        }
        return attr.getStringValue();
    }

    public void setModelRegion(String region, Long userId) {
        ProposalAttributeClientUtil.setProposalAttribute(createProposalAttribute(this.getProposalId(), ProposalAttributeKeys.REGION, region),userId);
    }
    private static ProposalAttribute createProposalAttribute(Long proposalId, String name, String stringValue){
        ProposalAttribute proposalAttribute = new ProposalAttribute();
        proposalAttribute.setProposalId(proposalId);
        proposalAttribute.setName(name);
        proposalAttribute.setStringValue(stringValue);
        return proposalAttribute;
    }

    public Long getModelId() {
        return contest.getDefaultModelId();
    }

    public void setScenarioId(Long scenarioId, Long isConsolidatedScenario, Long userId) {
        ProposalAttribute proposalAttribute = new ProposalAttribute();
        proposalAttribute.setProposalId(this.getProposalId());
        proposalAttribute.setName(ProposalAttributeKeys.SCENARIO_ID);
        proposalAttribute.setNumericValue(isConsolidatedScenario);
        proposalAttribute.setAdditionalId(scenarioId);
        ProposalAttributeClientUtil.setProposalAttribute(proposalAttribute,userId);
    }

    public Long getScenarioId() {
        ProposalAttribute attr = proposalAttributeHelper.getAttributeOrNull(ProposalAttributeKeys.SCENARIO_ID);
        if (attr == null) {
            return 0L;
        }
        return attr.getNumericValue();
    }

    public Boolean isConsolidatedScenario(Long scenarioId) {
        ProposalAttribute attr = proposalAttributeHelper.getAttributeOrNull(ProposalAttributeKeys.SCENARIO_ID);
        return attr != null && attr.getAdditionalId() == 1;
    }

    public Map<Long, List<Proposal>> getSubProposalPerModel() {
        Map<Long, List<Proposal>> subProposalPerModel = new HashMap<>();
        List<Proposal> subProposals = ProposalClientUtil.getContestIntegrationRelevantSubproposals(this.getProposalId());

        for (Proposal subProposal : subProposals) {
            Long modelId = subProposal.getModelIdForStoredScenario();
            if (!subProposalPerModel.containsKey(modelId)) {
                subProposalPerModel.put(modelId, Collections.singletonList(subProposal));
            } else {
                subProposalPerModel.get(modelId).add(subProposal);
            }
        }
        return subProposalPerModel;
    }

    public Scenario getScenario() throws IOException {
        return getScenarioByProposalId(this.getProposalId());
    }

    public Scenario getScenarioByProposalId(Long proposalId) throws IOException {
        return RomaClientUtil.client().getScenario(proposalId);
    }

    private static Long getModelIdForScenarioId(Long scenarioId) {
        Long modelId;

        try {
            Scenario scenario = RomaClientUtil.client().getScenario(scenarioId);
            Simulation simulation = scenario.getSimulation();
            modelId = simulation.getId();
        } catch (IOException e) {
            modelId = 0L;
        }

        return modelId;
    }

    public List<Scenario> getSubProposalScenarios() throws IOException {
        List<Scenario> subProposalScenarios = new ArrayList<>();
        List<Proposal> subProposals = ProposalClientUtil.getContestIntegrationRelevantSubproposals(this.getProposalId());
        for (Proposal subProposal : subProposals) {
            Scenario scenarioForSubProposal = getScenarioByProposalId(subProposal.getProposalId());
            subProposalScenarios.add(scenarioForSubProposal);
        }
        return subProposalScenarios;
    }

    private Long getModelIdForStoredScenario() {
        return getModelIdForScenarioId(this.getProposalId());
    }

    public List<Long> getSubProposalScenarioIds() {
        List<Long> subProposalScenarioIds = new ArrayList<>();
        Map<Long, List<Proposal>> subProposalPerModel = getSubProposalPerModel();

        if (!subProposalPerModel.isEmpty()) {
            for (Map.Entry<Long, List<Proposal>> entry : subProposalPerModel.entrySet()) {
                List<Proposal> proposalWrappers = entry.getValue();
                for (Proposal proposalWrapper : proposalWrappers) {
                    subProposalScenarioIds.add(proposalWrapper.getScenarioId());
                }
            }
        }

        return subProposalScenarioIds;
    }

    /**
     * Determine if fellows are done with proposal
     */
    public GenericJudgingStatus getScreeningStatus() {
        switch (getFellowAction()) {
            case INCOMPLETE:
            case OFFTOPIC:
            case NOT_ADVANCE_OTHER:
                return GenericJudgingStatus.STATUS_REJECTED;
            case PASS_TO_JUDGES:
                return GenericJudgingStatus.STATUS_ACCEPTED;
            default:
                return GenericJudgingStatus.STATUS_UNKNOWN;
        }
    }

    /**
     * Determine if judges are done with proposal
     */
    public GenericJudgingStatus getJudgeStatus() {
        if (getFellowAction() == JudgingSystemActions.FellowAction.INCOMPLETE
                || getFellowAction() == JudgingSystemActions.FellowAction.OFFTOPIC
                || getFellowAction() == JudgingSystemActions.FellowAction.NOT_ADVANCE_OTHER) {
            return GenericJudgingStatus.STATUS_REJECTED;
        }
        if (!getSelectedJudges().isEmpty() && getAllJudgesReviewFinished()) {
            return GenericJudgingStatus.STATUS_ACCEPTED;
        }
        return GenericJudgingStatus.STATUS_UNKNOWN;
    }

    /**
     * Determines whether the screening/advance decision of the proposal is done
     */
    public GenericJudgingStatus getOverallStatus() {
        if (getJudgeDecision() == JudgingSystemActions.AdvanceDecision.MOVE_ON
                && (getProposalReview()!=null)) {
            return GenericJudgingStatus.STATUS_ACCEPTED;
        }
        if (getJudgeDecision() == JudgingSystemActions.AdvanceDecision.DONT_MOVE_ON
                && (getProposalReview()!=null)
                || getScreeningStatus() == GenericJudgingStatus.STATUS_REJECTED) {
            return GenericJudgingStatus.STATUS_REJECTED;
        }

        return GenericJudgingStatus.STATUS_UNKNOWN;
    }

    public Member getUserForSelectedVersion() {
        try {
            return MembersClient.getMember(getSelectedVersion().getAuthorId());
        } catch (MemberNotFoundException e) {
            return null;
        }
    }

    public boolean getAllJudgesReviewFinished() {

        if (!getSelectedJudges().isEmpty()) {
            for (long userId : getSelectedJudges()) {
                List<ProposalRating> proposalRatings = ProposalJudgeRatingClientUtil
                        .getProposalRatingsByProposalUserContestPhase(this.getProposalId(),
                                contestPhase.getContestPhasePK(),userId);
                ProposalRatings wrapper = new ProposalRatings(userId,
                        proposalRatings);
                if (!wrapper.isReviewComplete()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean getJudgeReviewFinishedStatusUserId(long userId) {

        List<ProposalRating> proposalRatings = ProposalJudgeRatingClientUtil
                .getProposalRatingsByProposalUserContestPhase(
                        this.getProposalId(), contestPhase.getContestPhasePK(), userId);
        ProposalRatings wrapper = new ProposalRatings(userId, proposalRatings);
        return wrapper.isReviewComplete();

    }

    public Proposal getBaseProposal() {
        long baseProposalId = proposalAttributeHelper
                .getAttributeValueLong(ProposalAttributeKeys.BASE_PROPOSAL_ID, 0);
        if (baseProposalId > 0) {
            long baseProposalContestId = proposalAttributeHelper
                    .getAttributeValueLong(ProposalAttributeKeys.BASE_PROPOSAL_CONTEST_ID, 0);
            if (baseProposalContestId > 0) {
                return proposalClient.getProposal(baseProposalId);
            }
        }
        return null;
    }

    public List<ProposalRating> getRatings() {
        if (this.proposalRatings == null) {
            return new ArrayList<>();
        }
        return this.proposalRatings.getRatings();
    }

    public String getRatingComment() {
        if (this.proposalRatings == null) {
            return "";
        }
        return this.proposalRatings.getComment();
    }

    public Boolean getRatingShouldAdvance() {
        if (this.proposalRatings == null) {
            return null;
        }
        return this.proposalRatings.getShouldAdvance();
    }

    public RestService getRestService() {
        return restService;
    }

    public ProposalRibbon getRibbonWrapper() {
        if (ribbonWrapper == null) {
            ribbonWrapper = new ProposalRibbon(this, restService);
        }
        return ribbonWrapper;
    }
    public Long getImage() {
        return proposalAttributeHelper.getAttributeValueLong(ProposalAttributeKeys.IMAGE_ID, 0);
    }
}
