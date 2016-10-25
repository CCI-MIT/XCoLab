package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporter;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class ProposalSupporterClientUtil {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final ProposalSupporterClient client =
            ProposalSupporterClient.fromService(proposalService);

    public static ProposalSupporterClient getClient() {
        return client;
    }

    public static List<ProposalSupporter> getProposalSupporters(
            Long proposalId) {
        return client.getProposalSupporters(proposalId);
    }

    public static List<ProposalSupporter> getProposalSupportersByUserId(
            Long userId) {
        return client.getProposalSupportersByUserId(userId);
    }

    public static Integer getProposalSupportersCount(Long proposalId) {
        return client.getProposalSupportersCount(proposalId);
    }

    public static Boolean isMemberProposalSupporter(Long proposalId, Long memberId) {
        return client.isMemberProposalSupporter(proposalId, memberId);
    }

    public static void addProposalSupporter(long proposalId, long userId) {
        client.addProposalSupporter(proposalId, userId);
    }

    public static void addProposalSupporter(long proposalId, long userId, boolean publishActivity) {
        client.addProposalSupporter(proposalId, userId, publishActivity);
    }

    public static ProposalSupporter createProposalSupporter(
            ProposalSupporter proposalSupporter) {
        return client.createProposalSupporter(proposalSupporter);
    }

    public static void removeProposalSupporter(long proposalId, long userId) {
        client.removeProposalSupporter(proposalId, userId);
    }

    public static Boolean deleteProposalSupporter(Long proposalId, Long memberId) {
        return client.deleteProposalSupporter(proposalId, memberId);
    }
}
