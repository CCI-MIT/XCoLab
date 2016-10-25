package org.xcolab.portlets.proposals.utils.context;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.CategoryClient;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.ContestTeamMemberClient;
import org.xcolab.client.contest.ContestTeamMemberClientUtil;
import org.xcolab.client.contest.ImpactClient;
import org.xcolab.client.contest.ImpactClientUtil;
import org.xcolab.client.contest.OntologyClient;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.PlanTemplateClient;
import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.MembershipClient;
import org.xcolab.client.proposals.MembershipClientUtil;
import org.xcolab.client.proposals.PointsClient;
import org.xcolab.client.proposals.PointsClientUtil;
import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalJudgeRatingClient;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.ProposalMemberRatingClient;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.ProposalMoveClient;
import org.xcolab.client.proposals.ProposalMoveClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;

public class ClientHelper {

    // Proposal clients
    private final ProposalClient proposalClient;
    private final MembershipClient membershipClient;
    private final PointsClient pointsClient;
    private final ProposalPhaseClient proposalPhaseClient;
    private final ProposalAttributeClient proposalAttributeClient;
    private final ProposalMoveClient proposalMoveClient;
    private final ProposalJudgeRatingClient proposalJudgeRatingClient;
    private final ProposalMemberRatingClient proposalMemberRatingClient;

    // Contest clients
    private final ContestClient contestClient;
    private final ContestTeamMemberClient contestTeamMemberClient;
    private final ImpactClient impactClient;
    private final OntologyClient ontologyClient;
    private final PlanTemplateClient planTemplateClient;

    //Comment clients
    private final CommentClient commentClient;
    private final ThreadClient threadClient;
    private final CategoryClient categoryClient;

    public ClientHelper(Contest contest) {
        if (contest != null && contest.getIsSharedContest()) {
            RestService proposalService = new RefreshingRestService("proposals-service",
                    ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                    ConfigurationAttributeKey.PARTNER_COLAB_PORT);
            proposalClient = ProposalClient.fromService(proposalService);
            membershipClient = MembershipClient.fromService(proposalService);
            pointsClient = PointsClient.fromService(proposalService);
            proposalPhaseClient = ProposalPhaseClient.fromService(proposalService);
            proposalAttributeClient = ProposalAttributeClient.fromService(proposalService);
            proposalMoveClient = ProposalMoveClient.fromService(proposalService);
            proposalJudgeRatingClient = ProposalJudgeRatingClient.fromService(proposalService);
            proposalMemberRatingClient = ProposalMemberRatingClient.fromService(proposalService);

            RestService contestService = new RefreshingRestService("contest-service",
                    ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                    ConfigurationAttributeKey.PARTNER_COLAB_PORT);
            contestClient = ContestClient.fromService(contestService);
            contestTeamMemberClient = ContestTeamMemberClient.fromService(contestService);
            impactClient = ImpactClient.fromService(contestService);
            ontologyClient = OntologyClient.fromService(contestService);
            planTemplateClient = PlanTemplateClient.fromService(contestService);

            RestService commentService = new RefreshingRestService("comment-service",
                    ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                    ConfigurationAttributeKey.PARTNER_COLAB_PORT);
            commentClient = new CommentClient(commentService);
            threadClient = new ThreadClient(commentService);
            categoryClient = new CategoryClient(commentService);
        } else {
            proposalClient = ProposalClientUtil.getClient();
            membershipClient = MembershipClientUtil.getClient();
            pointsClient = PointsClientUtil.getClient();
            proposalPhaseClient = ProposalPhaseClientUtil.getClient();
            proposalAttributeClient = ProposalAttributeClientUtil.getClient();
            proposalMoveClient = ProposalMoveClientUtil.getClient();
            proposalJudgeRatingClient = ProposalJudgeRatingClientUtil.getClient();
            proposalMemberRatingClient = ProposalMemberRatingClientUtil.getClient();

            contestClient = ContestClientUtil.getClient();
            contestTeamMemberClient = ContestTeamMemberClientUtil.getClient();
            impactClient = ImpactClientUtil.getClient();
            ontologyClient = OntologyClientUtil.getClient();
            planTemplateClient = PlanTemplateClientUtil.getClient();

            commentClient = CommentClientUtil.getClient();
            threadClient = ThreadClientUtil.getClient();
            categoryClient = CategoryClientUtil.getClient();
        }
    }

    public ProposalClient getProposalClient() {
        return proposalClient;
    }

    public MembershipClient getMembershipClient() {
        return membershipClient;
    }

    public PointsClient getPointsClient() {
        return pointsClient;
    }

    public ProposalPhaseClient getProposalPhaseClient() {
        return proposalPhaseClient;
    }

    public ProposalAttributeClient getProposalAttributeClient() {
        return proposalAttributeClient;
    }

    public ProposalMoveClient getProposalMoveClient() {
        return proposalMoveClient;
    }

    public ProposalJudgeRatingClient getProposalJudgeRatingClient() {
        return proposalJudgeRatingClient;
    }

    public ProposalMemberRatingClient getProposalMemberRatingClient() {
        return proposalMemberRatingClient;
    }

    public ContestClient getContestClient() {
        return contestClient;
    }

    public ContestTeamMemberClient getContestTeamMemberClient() {
        return contestTeamMemberClient;
    }

    public ImpactClient getImpactClient() {
        return impactClient;
    }

    public OntologyClient getOntologyClient() {
        return ontologyClient;
    }

    public PlanTemplateClient getPlanTemplateClient() {
        return planTemplateClient;
    }

    public CommentClient getCommentClient() {
        return commentClient;
    }

    public ThreadClient getThreadClient() {
        return threadClient;
    }

    public CategoryClient getCategoryClient() {
        return categoryClient;
    }
}
