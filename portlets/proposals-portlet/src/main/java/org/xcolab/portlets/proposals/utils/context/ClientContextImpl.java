package org.xcolab.portlets.proposals.utils.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
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
import org.xcolab.client.members.MembersClient;
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

import javax.portlet.PortletRequest;

@Component
public class ClientContextImpl {
    private static final String ATTRIBUTE_PREFIX = "_clientContext_";
    private static final String CONTEXT_INITIALIZED_ATTRIBUTE = ATTRIBUTE_PREFIX + "contextInitialized";

    private static final String PROPOSAL_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "proposalClient";
    private static final String MEMBERSHIP_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "membershipClient";
    private static final String POINTS_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "pointsClient";
    private static final String PROPOSAL_PHASE_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "phaseClient";
    private static final String PROPOSAL_ATTRIBUTE_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "attributeClient";
    private static final String PROPOSAL_MOVE_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "moveClient";
    private static final String PROPOSAL_JUDGE_RATING_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "judgeRatingClient";
    private static final String PROPOSAL_MEMBER_RATING_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "memberRatingClient";

    private static final String CONTEST_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "contestClient";
    private static final String CONTEST_TEAM_MEMBER_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "contestTeamMemberClient";
    private static final String IMPACT_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "impactClient";
    private static final String ONTOLOGY_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "ontologyClient";
    private static final String PLAN_TEMPLATE_CLIENT_ATTRIBUTE = ATTRIBUTE_PREFIX + "planTemplateClient";

    private final ProposalsContext proposalContext;

    @Autowired
    public ClientContextImpl(ProposalsContext proposalContext) {
        this.proposalContext = proposalContext;
    }

    private void init(PortletRequest request) {
        Contest contest = proposalContext.getContest(request);
        // Proposal clients
        ProposalClient proposalClient;
        MembershipClient membershipClient;
        PointsClient pointsClient;
        ProposalPhaseClient proposalPhaseClient;
        ProposalAttributeClient proposalAttributeClient;
        ProposalMoveClient proposalMoveClient;
        ProposalJudgeRatingClient proposalJudgeRatingClient;
        ProposalMemberRatingClient proposalMemberRatingClient;

        // Contest clients
        ContestClient contestClient;
        ContestTeamMemberClient contestTeamMemberClient;
        ImpactClient impactClient;
        OntologyClient ontologyClient;
        PlanTemplateClient planTemplateClient;

        if (contest.getIsSharedContest()) {
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
        }

        request.setAttribute(PROPOSAL_CLIENT_ATTRIBUTE, proposalClient);
        request.setAttribute(MEMBERSHIP_CLIENT_ATTRIBUTE, membershipClient);
        request.setAttribute(POINTS_CLIENT_ATTRIBUTE, pointsClient);
        request.setAttribute(PROPOSAL_PHASE_CLIENT_ATTRIBUTE, proposalPhaseClient);
        request.setAttribute(PROPOSAL_ATTRIBUTE_CLIENT_ATTRIBUTE, proposalAttributeClient);
        request.setAttribute(PROPOSAL_MOVE_CLIENT_ATTRIBUTE, proposalMoveClient);
        request.setAttribute(PROPOSAL_JUDGE_RATING_CLIENT_ATTRIBUTE, proposalJudgeRatingClient);
        request.setAttribute(PROPOSAL_MEMBER_RATING_CLIENT_ATTRIBUTE, proposalMemberRatingClient);

        request.setAttribute(CONTEST_CLIENT_ATTRIBUTE, contestClient);
        request.setAttribute(CONTEST_TEAM_MEMBER_CLIENT_ATTRIBUTE, contestTeamMemberClient);
        request.setAttribute(IMPACT_CLIENT_ATTRIBUTE, impactClient);
        request.setAttribute(ONTOLOGY_CLIENT_ATTRIBUTE, ontologyClient);
        request.setAttribute(PLAN_TEMPLATE_CLIENT_ATTRIBUTE, planTemplateClient);
    }

    private <T> T getAttribute(PortletRequest request, String attributeName) {
        Object contextInitialized =  request.getAttribute(CONTEXT_INITIALIZED_ATTRIBUTE);
        if (contextInitialized == null) {
            init(request);
        }
        //noinspection unchecked
        return (T) request.getAttribute(attributeName);
    }

    public MembersClient getMembershipClient(PortletRequest request) {
        return getAttribute(request, MEMBERSHIP_CLIENT_ATTRIBUTE);
    }

    public PointsClient getPointsClient(PortletRequest request) {
        return getAttribute(request, POINTS_CLIENT_ATTRIBUTE);
    }

    public ProposalAttributeClient getProposalAttributeClient(PortletRequest request) {
        return getAttribute(request, PROPOSAL_ATTRIBUTE_CLIENT_ATTRIBUTE);
    }

    public ProposalClient getProposalClient(PortletRequest request) {
        return getAttribute(request, PROPOSAL_CLIENT_ATTRIBUTE);
    }

    public ProposalJudgeRatingClient getProposalJudgeRatingClient(PortletRequest request) {
        return getAttribute(request, PROPOSAL_JUDGE_RATING_CLIENT_ATTRIBUTE);
    }

    public ProposalMemberRatingClient getProposalMemberRatingClient(PortletRequest request) {
        return getAttribute(request, PROPOSAL_MEMBER_RATING_CLIENT_ATTRIBUTE);
    }

    public ProposalMoveClient getProposalMoveClient(PortletRequest request) {
        return getAttribute(request, PROPOSAL_MOVE_CLIENT_ATTRIBUTE);
    }

    public ProposalPhaseClient getProposalPhaseClient(PortletRequest request) {
        return getAttribute(request, PROPOSAL_PHASE_CLIENT_ATTRIBUTE);
    }

    public ContestClient getContestClient(PortletRequest request) {
        return getAttribute(request, CONTEST_CLIENT_ATTRIBUTE);
    }

    public ContestTeamMemberClient getContestTeamMemberClient(PortletRequest request) {
        return getAttribute(request, CONTEST_TEAM_MEMBER_CLIENT_ATTRIBUTE);
    }

    public ImpactClient getImpactClient(PortletRequest request) {
        return getAttribute(request, IMPACT_CLIENT_ATTRIBUTE);
    }

    public OntologyClient getOntologyClient(PortletRequest request) {
        return getAttribute(request, ONTOLOGY_CLIENT_ATTRIBUTE);
    }

    public PlanTemplateClient getPlanTemplateClient(PortletRequest request) {
        return getAttribute(request, PLAN_TEMPLATE_CLIENT_ATTRIBUTE);
    }
}
