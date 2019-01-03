package org.xcolab.client.proposals;

import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;

import java.util.List;

public final class ProposalPhaseClientUtil {

    private static final ProposalPhaseClient client = new ProposalPhaseClient();

    public static ProposalPhaseClient getClient() {
        return client;
    }

    public static Proposal2Phase getProposal2PhaseByProposalIdContestPhaseId(
            Long proposalId, Long contestPhaseId) throws Proposal2PhaseNotFoundException {
        return client.getProposal2PhaseByProposalIdContestPhaseId(proposalId, contestPhaseId);
    }

    public static List<Proposal2Phase> getProposal2PhaseByProposalId(
            Long proposalId) {
        return client.getProposal2PhaseByProposalId(proposalId);
    }

    public static List<Proposal2Phase> getProposal2PhaseByContestPhaseId(Long contestPhaseId) {
        return client.getProposal2PhaseByContestPhaseId(contestPhaseId);
    }

    public static Proposal2Phase getProposal2PhaseByProposalIdVersion(long proposalId, int version) {
        return client.getProposal2PhaseByProposalIdVersion(proposalId, version);
    }

    public static void createProposal2Phase(Proposal2Phase proposal2Phase) {
        client.createProposal2Phase(proposal2Phase);
    }

    public static void updateProposal2Phase(Proposal2Phase proposal2Phase) {
        client.updateProposal2Phase(proposal2Phase);
    }

    public static void deleteProposal2Phase(Proposal2Phase proposal2Phase) {
        client.deleteProposal2Phase(proposal2Phase);
    }

    public static Integer getProposalCountForActiveContestPhase(Long contestPhaseId) {
        return client.getProposalCountForActiveContestPhase(contestPhaseId);
    }

    public static void promoteProposal(Long proposalId, Long activePhaseForContest,
            Long currentProposalContestPhase) {
        client.promoteProposal(proposalId, activePhaseForContest, currentProposalContestPhase);
    }

    public static List<ProposalContestPhaseAttribute> getAllProposalContestPhaseProposalAttributes(
            Long contestPhaseId, Long proposalId) {
        return client.getAllProposalContestPhaseProposalAttributes(contestPhaseId, proposalId);

    }

    public static Boolean isProposalContestPhaseAttributeSetAndTrue(Long proposalId,
            long contestPhaseId, String name) {
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
    public static ProposalContestPhaseAttribute persistProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId, String name, Long aditionalId, Long numericValue, String stringValue) {
        return client.persistProposalContestPhaseAttribute(proposalId,contestPhaseId, name, aditionalId, numericValue, stringValue);
    }

    public static boolean updateProposalContestPhaseAttribute(
            ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return client.updateProposalContestPhaseAttribute(proposalContestPhaseAttribute);
    }

    public static ProposalContestPhaseAttribute setProposalContestPhaseAttribute(
            Long proposalId, Long contestPhaseId, String name, Long additionalId,
            Long numericValue, String stringValue) {
        return client.setProposalContestPhaseAttribute(proposalId, contestPhaseId, name,
                additionalId, numericValue, stringValue);
    }
    public static Boolean hasProposalContestPhaseAttribute(Long proposalId, long contestPhaseId, String name) {
        return client.hasProposalContestPhaseAttribute(proposalId,contestPhaseId, name);
    }
    public static ProposalContestPhaseAttribute getOrCreateProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId, String name, Long aditionalId, Long numericValue, String stringValue) {
        return client.getOrCreateProposalContestPhaseAttribute(proposalId, contestPhaseId, name,aditionalId, numericValue, stringValue);
    }

    public static ProposalContestPhaseAttribute createProposalContestPhaseAttribute(
            ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return client.createProposalContestPhaseAttribute(proposalContestPhaseAttribute);
    }

    public static Boolean deleteProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId,
            String name) {
        return client.deleteProposalContestPhaseAttribute(proposalId, contestPhaseId, name);
    }

    public static List<Long> getContestPhasesForProposal(long proposalId) {
        return client.getContestPhasesForProposal(proposalId);
    }
}
