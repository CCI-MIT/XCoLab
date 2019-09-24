package org.xcolab.client.contest;

import org.springframework.util.Assert;

public final class StaticContestContext {

    private static IContestClient contestClient;
    private static IContestTeamMemberClient contestTeamMemberClient;
    private static IImpactClient impactClient;
    private static IOntologyClient ontologyClient;
    private static IProposalTemplateClient proposalTemplateClient;

    private StaticContestContext() {}

    public static void setClients(IContestClient contestClient,
            IContestTeamMemberClient contestTeamMemberClient, IImpactClient impactClient,
            IOntologyClient ontologyClient, IProposalTemplateClient proposalTemplateClient) {
        Assert.notNull(contestClient, "contestClient must not be null!");
        Assert.notNull(contestTeamMemberClient, "contestTeamMemberClient must not be null!");
        Assert.notNull(impactClient, "impactClient must not be null!");
        Assert.notNull(ontologyClient, "ontologyClient must not be null!");
        Assert.notNull(proposalTemplateClient, "proposalTemplateClient must not be null!");
        StaticContestContext.contestClient = contestClient;
        StaticContestContext.contestTeamMemberClient = contestTeamMemberClient;
        StaticContestContext.impactClient = impactClient;
        StaticContestContext.ontologyClient = ontologyClient;
        StaticContestContext.proposalTemplateClient = proposalTemplateClient;
    }

    public static IContestClient getContestClient() {
        return contestClient;
    }

    public static IContestTeamMemberClient getContestTeamMemberClient() {
        return contestTeamMemberClient;
    }

    public static IOntologyClient getOntologyClient() {
        return ontologyClient;
    }

    public static IImpactClient getImpactClient() {
        return impactClient;
    }

    public static IProposalTemplateClient getProposalTemplateClient() {
        return proposalTemplateClient;
    }
}
