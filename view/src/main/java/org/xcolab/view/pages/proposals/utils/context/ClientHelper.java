package org.xcolab.view.pages.proposals.utils.context;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
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
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;

public class ClientHelper {

    // Proposal
    private final RestService proposalService;
    private final ProposalClient proposalClient;
    private final MembershipClient membershipClient;
    private final PointsClient pointsClient;
    private final ProposalPhaseClient proposalPhaseClient;
    private final ProposalAttributeClient proposalAttributeClient;
    private final ProposalMoveClient proposalMoveClient;
    private final ProposalJudgeRatingClient proposalJudgeRatingClient;
    private final ProposalMemberRatingClient proposalMemberRatingClient;

    // Contest
    private final RestService contestService;
    private final ContestClient contestClient;
    private final ContestTeamMemberClient contestTeamMemberClient;
    private final ImpactClient impactClient;
    private final OntologyClient ontologyClient;
    private final PlanTemplateClient planTemplateClient;

    // Comment
    private final RestService commentService;
    private final CommentClient commentClient;
    private final ThreadClient threadClient;
    private final CategoryClient categoryClient;

    // Activity
    private final RestService activitiesService;
    private final ActivitiesClient activitiesClient;

    public ClientHelper(Contest contest) {
        if (contest != null && contest.getIsSharedContestInForeignColab()) {
            proposalService = new RefreshingRestService(CoLabService.CONTEST,
                    ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE
            );
            proposalClient = ProposalClient.fromService(proposalService);
            membershipClient = MembershipClient.fromService(proposalService);
            pointsClient = PointsClient.fromService(proposalService);
            proposalPhaseClient = ProposalPhaseClient.fromService(proposalService);
            proposalAttributeClient = ProposalAttributeClient.fromService(proposalService);
            proposalMoveClient = ProposalMoveClient.fromService(proposalService);
            proposalJudgeRatingClient = ProposalJudgeRatingClient.fromService(proposalService);
            proposalMemberRatingClient = ProposalMemberRatingClient.fromService(proposalService);

            contestService = new RefreshingRestService(CoLabService.CONTEST,
                    ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE
            );
            contestClient = ContestClient.fromService(contestService);
            contestTeamMemberClient = ContestTeamMemberClient.fromService(contestService);
            impactClient = ImpactClient.fromService(contestService);
            ontologyClient = OntologyClient.fromService(contestService);
            planTemplateClient = PlanTemplateClient.fromService(contestService);

            commentService = new RefreshingRestService(CoLabService.COMMENT,
                    ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE
            );
            commentClient = new CommentClient(commentService);
            threadClient = new ThreadClient(commentService);
            categoryClient = new CategoryClient(commentService);

            activitiesService = new RefreshingRestService(CoLabService.ACTIVITY,
                    ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE
            );
            activitiesClient = ActivitiesClient.fromService(activitiesService);
        } else {
            proposalService = new RestService(CoLabService.CONTEST,
                    ServiceRequestUtils.getNamespace());
            proposalClient = ProposalClientUtil.getClient();
            membershipClient = MembershipClientUtil.getClient();
            pointsClient = PointsClientUtil.getClient();
            proposalPhaseClient = ProposalPhaseClientUtil.getClient();
            proposalAttributeClient = ProposalAttributeClientUtil.getClient();
            proposalMoveClient = ProposalMoveClientUtil.getClient();
            proposalJudgeRatingClient = ProposalJudgeRatingClientUtil.getClient();
            proposalMemberRatingClient = ProposalMemberRatingClientUtil.getClient();

            contestService = new RestService(CoLabService.CONTEST,
                    ServiceRequestUtils.getNamespace());
            contestClient = ContestClientUtil.getClient();
            contestTeamMemberClient = ContestTeamMemberClientUtil.getClient();
            impactClient = ImpactClientUtil.getClient();
            ontologyClient = OntologyClientUtil.getClient();
            planTemplateClient = PlanTemplateClientUtil.getClient();

            commentService = new RestService(CoLabService.COMMENT,
                    ServiceRequestUtils.getNamespace());
            commentClient = CommentClientUtil.getClient();
            threadClient = ThreadClientUtil.getClient();
            categoryClient = CategoryClientUtil.getClient();

            activitiesService = new RestService(CoLabService.ACTIVITY,
                    ServiceRequestUtils.getNamespace());
            activitiesClient = ActivitiesClientUtil.getClient();
        }
    }

    public ActivitiesClient getActivitiesClient() {
        return activitiesClient;
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

    public RestService getProposalService() {
        return proposalService;
    }

    public RestService getContestService() {
        return contestService;
    }

    public RestService getCommentService() {
        return commentService;
    }

    public RestService getActivitiesService() {
        return activitiesService;
    }
}
