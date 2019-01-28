package org.xcolab.client.contest;

import org.springframework.util.Assert;

import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.ICommentClient;
import org.xcolab.client.comment.IThreadClient;

public final class StaticContestContext {

    private static ICommentClient commentClient;
    private static ICategoryClient categoryClient;
    private static IThreadClient threadClient;
    private static IContestClient contestClient;
    private static IContestTeamMemberClient contestTeamMemberClient;
    private static IImpactClient impactClient;
    private static IOntologyClient ontologyClient;
    private static IProposalTemplateClient proposalTemplateClient;

    private StaticContestContext() {}

    public static void setClients(ICommentClient commentClient, ICategoryClient categoryClient,
            IThreadClient threadClient, IContestClient contestClient,
            IContestTeamMemberClient contestTeamMemberClient, IImpactClient impactClient,
            IOntologyClient ontologyClient, IProposalTemplateClient proposalTemplateClient) {
        Assert.notNull(commentClient, "commentClient must not be null!");
        Assert.notNull(categoryClient, "categoryClient must not be null!");
        Assert.notNull(threadClient, "threadClient must not be null!");
        Assert.notNull(contestClient, "contestClient must not be null!");
        Assert.notNull(contestTeamMemberClient, "contestTeamMemberClient must not be null!");
        Assert.notNull(impactClient, "impactClient must not be null!");
        Assert.notNull(ontologyClient, "ontologyClient must not be null!");
        Assert.notNull(proposalTemplateClient, "proposalTemplateClient must not be null!");
        StaticContestContext.commentClient = commentClient;
        StaticContestContext.categoryClient = categoryClient;
        StaticContestContext.threadClient = threadClient;
        StaticContestContext.contestClient = contestClient;
        StaticContestContext.contestTeamMemberClient = contestTeamMemberClient;
        StaticContestContext.impactClient = impactClient;
        StaticContestContext.ontologyClient = ontologyClient;
        StaticContestContext.proposalTemplateClient = proposalTemplateClient;
    }

    public static ICommentClient getCommentClient() {
        return commentClient;
    }

    public static ICategoryClient getCategoryClient() {
        return categoryClient;
    }

    public static IThreadClient getThreadClient() {
        return threadClient;
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
