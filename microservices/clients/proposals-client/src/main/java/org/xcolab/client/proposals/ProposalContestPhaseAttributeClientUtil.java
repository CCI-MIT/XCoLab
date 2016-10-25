package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class ProposalContestPhaseAttributeClientUtil {

    private static final RestService proposalService = new RestService("proposals-service");

    private static final ProposalContestPhaseAttributeClient client
            = ProposalContestPhaseAttributeClient.fromService(proposalService);

    public static ProposalContestPhaseAttributeClient getClient() {
        return client;
    }

    public static List<ProposalContestPhaseAttribute> getAllProposalContestPhaseProposalAttributes(
            Long contestPhaseId, Long proposalId) {
        return client.getAllProposalContestPhaseProposalAttributes(contestPhaseId, proposalId);
    }

    public static Boolean isProposalContestPhaseAttributeSetAndTrue(Long proposalId,
            long contestPhaseId,
            String name) {
        return client.isProposalContestPhaseAttributeSetAndTrue(proposalId, contestPhaseId, name);
    }

    public static ProposalContestPhaseAttribute getProposalContestPhaseAttribute(
            Long proposalId, Long contestPhaseId, String name) {
        return client.getProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
    }

    public static boolean persistSelectedJudgesAttribute(Long proposalId, Long contestPhaseId,
            List<Long> selectedJudges) {
        return client.persistSelectedJudgesAttribute(proposalId, contestPhaseId, selectedJudges);
    }

    public static boolean updateProposalContestPhaseAttribute(
            ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return client.updateProposalContestPhaseAttribute(proposalContestPhaseAttribute);
    }

    public static ProposalContestPhaseAttribute setProposalContestPhaseAttribute(
            Long proposalId, Long contestPhaseId, String name, Long aditionalId,
            Long numericValue, String stringValue) {
        return client
                .setProposalContestPhaseAttribute(proposalId, contestPhaseId, name, aditionalId,
                        numericValue, stringValue);
    }

    public static ProposalContestPhaseAttribute createProposalContestPhaseAttribute(
            ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return client.createProposalContestPhaseAttribute(proposalContestPhaseAttribute);
    }

    public static Boolean deleteProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId,
            String name) {
        return client.deleteProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
    }
}
