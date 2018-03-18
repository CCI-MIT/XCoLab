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
import org.xcolab.commons.http.client.enums.ServiceNamespace;

public class ClientHelper {

    private final ProposalClient proposalClient;
    private final MembershipClient membershipClient;
    private final PointsClient pointsClient;
    private final ProposalPhaseClient proposalPhaseClient;
    private final ProposalAttributeClient proposalAttributeClient;
    private final ProposalMoveClient proposalMoveClient;
    private final ProposalJudgeRatingClient proposalJudgeRatingClient;
    private final ProposalMemberRatingClient proposalMemberRatingClient;

    // Contest
    private final ContestClient contestClient;
    private final ContestTeamMemberClient contestTeamMemberClient;
    private final ImpactClient impactClient;
    private final OntologyClient ontologyClient;
    private final PlanTemplateClient planTemplateClient;

    // Comment
    private final CommentClient commentClient;
    private final ThreadClient threadClient;
    private final CategoryClient categoryClient;

    // Activity
    private final ActivitiesClient activitiesClient;

    public ClientHelper(Contest contest) {
        if (contest != null && contest.getIsSharedContestInForeignColab()) {
            ServiceNamespace serviceNamespace = ServiceNamespace.instance(
                    ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE);
            proposalClient = ProposalClient.fromNamespace(serviceNamespace);
            membershipClient = MembershipClient.fromNamespace(serviceNamespace);
            pointsClient = PointsClient.fromNamespace(serviceNamespace);
            proposalPhaseClient = ProposalPhaseClient.fromNamespace(serviceNamespace);
            proposalAttributeClient = ProposalAttributeClient.fromNamespace(serviceNamespace);
            proposalMoveClient = ProposalMoveClient.fromNamespace(serviceNamespace);
            proposalJudgeRatingClient = ProposalJudgeRatingClient.fromNamespace(serviceNamespace);
            proposalMemberRatingClient = ProposalMemberRatingClient.fromNamespace(serviceNamespace);

            contestClient = ContestClient.fromNamespace(serviceNamespace);
            contestTeamMemberClient = ContestTeamMemberClient.fromService(serviceNamespace);
            impactClient = ImpactClient.fromService(serviceNamespace);
            ontologyClient = OntologyClient.fromService(serviceNamespace);
            planTemplateClient = PlanTemplateClient.fromNamespace(serviceNamespace);

            commentClient = new CommentClient(serviceNamespace);
            threadClient = new ThreadClient(serviceNamespace);
            categoryClient = new CategoryClient(serviceNamespace);

            activitiesClient = ActivitiesClient.fromNamespace(serviceNamespace);
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
}
