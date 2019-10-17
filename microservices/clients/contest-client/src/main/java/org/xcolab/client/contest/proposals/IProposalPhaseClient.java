package org.xcolab.client.contest.proposals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.client.contest.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import java.util.List;

@FeignClient("xcolab-contest-service")
public interface IProposalPhaseClient {

    @GetMapping("/proposal2Phases")
    List<IProposal2Phase> getProposal2Phases(
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "contestPhaseId", required = false) Long contestPhaseId,
            @RequestParam(value = "version", required = false) Integer version);

    default IProposal2Phase getProposal2PhaseByProposalIdContestPhaseId(Long proposalId,
            Long contestPhaseId) throws Proposal2PhaseNotFoundException {
        return getProposal2Phases(proposalId, contestPhaseId, null)
                .stream()
                .findFirst()
                .orElseThrow(() -> new Proposal2PhaseNotFoundException(proposalId, contestPhaseId));
    }

    default List<IProposal2Phase> getProposal2PhaseByProposalId(Long proposalId) {
        return getProposal2Phases(proposalId, null, null);
    }

    default IProposal2Phase getProposal2PhaseByProposalIdVersion(long proposalId, int version) {
        return getProposal2Phases(proposalId, null, version)
                .stream()
                .findFirst()
                .orElseThrow(IndexOutOfBoundsException::new);
    }

    default List<IProposal2Phase> getProposal2PhaseByContestPhaseId(Long contestPhaseId) {
        return getProposal2Phases(null, contestPhaseId, null);
    }

    @PostMapping("/proposal2Phases")
    void createProposal2Phase(@RequestBody IProposal2Phase proposal2Phase);

    @PutMapping("/proposal2Phases")
    boolean updateProposal2Phase(@RequestBody IProposal2Phase proposal2Phase);

    @DeleteMapping("/proposal2Phases")
    boolean deleteProposal2Phase(@RequestBody IProposal2Phase proposal2Phase);

    @GetMapping("/count/proposal2Phases/{proposal2PhaseId}")
    int getProposalCountForActiveContestPhase(
            @PathVariable("proposal2PhaseId") Long proposal2PhaseId);

    @PostMapping("/proposal2Phases/promoteProposal")
    boolean promoteProposal(@RequestParam("proposalId") Long proposalId,
            @RequestParam("activePhaseForContest") Long activePhaseForContest,
            @RequestParam("currentProposalContestPhase") Long currentProposalContestPhase);

    @GetMapping("/proposalContestPhaseAttributes")
    List<IProposalContestPhaseAttribute> getProposalContestPhaseAttributes(
            @RequestParam(value = "contestPhaseId", required = false) Long contestPhaseId,
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "name", required = false) String name);

    default List<IProposalContestPhaseAttribute> getAllProposalContestPhaseProposalAttributes(
            Long contestPhaseId, Long proposalId) {
        return getProposalContestPhaseAttributes(contestPhaseId, proposalId, null);
    }

    default Boolean isProposalContestPhaseAttributeSetAndTrue(Long proposalId, long contestPhaseId,
            String name) {
        IProposalContestPhaseAttribute proposalAttribute =
                getProposalContestPhaseAttribute(contestPhaseId,proposalId, name);
        return proposalAttribute != null && proposalAttribute.getStringValue().equals("true");
    }

    @GetMapping("/proposalContestPhaseAttributes/getByContestPhaseProposalIdName")
    IProposalContestPhaseAttribute getProposalContestPhaseAttribute(
            @RequestParam(value = "contestPhaseId", required = false) Long contestPhaseId,
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "name", required = false) String name);

    default boolean persistSelectedJudgesAttribute(Long proposalId, Long contestPhaseId,
            List<Long> selectedJudges) {
        IProposalContestPhaseAttribute judges =
                getOrCreateProposalContestPhaseAttribute(proposalId, contestPhaseId,
                        ProposalContestPhaseAttributeKeys.SELECTED_JUDGES, null, null, null);

        StringBuilder attributeValue = new StringBuilder();
        if (selectedJudges != null) {
            for (Long userId : selectedJudges) {
                attributeValue.append(userId).append(";");
            }
        }
        judges.setStringValue(attributeValue.toString().replaceAll(";$", ""));

        updateProposalContestPhaseAttribute(judges);
        return true;
    }

    default IProposalContestPhaseAttribute persistProposalContestPhaseAttribute(Long proposalId,
            Long contestPhaseId, String name, Long additionalId, Long numericValue,
            String stringValue) {
        IProposalContestPhaseAttribute proposalContestPhaseAttribute =
                new ProposalContestPhaseAttribute();
        proposalContestPhaseAttribute.setProposalId(proposalId);
        proposalContestPhaseAttribute.setName(name);
        proposalContestPhaseAttribute.setAdditionalId(additionalId);
        proposalContestPhaseAttribute.setNumericValue(numericValue);
        proposalContestPhaseAttribute.setStringValue(stringValue);
        proposalContestPhaseAttribute.setContestPhaseId(contestPhaseId);
        proposalContestPhaseAttribute =
                createProposalContestPhaseAttribute(proposalContestPhaseAttribute);
        return proposalContestPhaseAttribute;
    }

    default boolean hasProposalContestPhaseAttribute(Long proposalId, long contestPhaseId,
            String name) {
        IProposalContestPhaseAttribute proposalContestPhaseAttribute =
                getProposalContestPhaseAttribute(contestPhaseId,proposalId, name);
        return proposalContestPhaseAttribute != null;
    }

    default IProposalContestPhaseAttribute getOrCreateProposalContestPhaseAttribute(Long proposalId,
            Long contestPhaseId, String name, Long aditionalId, Long numericValue,
            String stringValue) {
        IProposalContestPhaseAttribute proposalContestPhaseAttribute =
                getProposalContestPhaseAttribute( contestPhaseId,proposalId, name);
        if (proposalContestPhaseAttribute != null) {
            return proposalContestPhaseAttribute;
        } else {
            proposalContestPhaseAttribute = new ProposalContestPhaseAttribute();
            proposalContestPhaseAttribute.setProposalId(proposalId);
            proposalContestPhaseAttribute.setName(name);
            proposalContestPhaseAttribute.setAdditionalId(aditionalId);
            proposalContestPhaseAttribute.setNumericValue(numericValue);
            proposalContestPhaseAttribute.setStringValue(stringValue);
            proposalContestPhaseAttribute.setContestPhaseId(contestPhaseId);
            proposalContestPhaseAttribute =
                    createProposalContestPhaseAttribute(proposalContestPhaseAttribute);
            return proposalContestPhaseAttribute;
        }
    }

    @PutMapping("/proposalContestPhaseAttributes")
    boolean updateProposalContestPhaseAttribute(
            @RequestBody IProposalContestPhaseAttribute proposalContestPhaseAttribute);


    default IProposalContestPhaseAttribute setProposalContestPhaseAttribute(Long proposalId,
            Long contestPhaseId, String name, Long aditionalId, Long numericValue,
            String stringValue) {
        IProposalContestPhaseAttribute proposalContestPhaseAttribute =
                getProposalContestPhaseAttribute(contestPhaseId,proposalId, name);
        if (proposalContestPhaseAttribute != null) {
            proposalContestPhaseAttribute.setAdditionalId(aditionalId);
            proposalContestPhaseAttribute.setNumericValue(numericValue);
            proposalContestPhaseAttribute.setStringValue(stringValue);
            updateProposalContestPhaseAttribute(proposalContestPhaseAttribute);
            return proposalContestPhaseAttribute;
        } else {
            proposalContestPhaseAttribute = new ProposalContestPhaseAttribute();
            proposalContestPhaseAttribute.setProposalId(proposalId);
            proposalContestPhaseAttribute.setName(name);
            proposalContestPhaseAttribute.setAdditionalId(aditionalId);
            proposalContestPhaseAttribute.setNumericValue(numericValue);
            proposalContestPhaseAttribute.setStringValue(stringValue);
            proposalContestPhaseAttribute.setContestPhaseId(contestPhaseId);
            proposalContestPhaseAttribute =
                    createProposalContestPhaseAttribute(proposalContestPhaseAttribute);
            return proposalContestPhaseAttribute;
        }
    }

    @PostMapping("/proposalContestPhaseAttributes")
    IProposalContestPhaseAttribute createProposalContestPhaseAttribute(
            @RequestBody IProposalContestPhaseAttribute proposalContestPhaseAttribute);

    @DeleteMapping("/proposalContestPhaseAttributes/{id}")
    boolean deleteProposalContestPhaseAttribute(@PathVariable("id") Long id);

    default boolean deleteProposalContestPhaseAttribute(Long proposalId, Long contestPhaseId,
            String name) {
        IProposalContestPhaseAttribute pcpa =
                getProposalContestPhaseAttribute(contestPhaseId,proposalId, name);
        return deleteProposalContestPhaseAttribute(pcpa.getId());
    }

    @GetMapping("/proposals/{proposalId}/phaseIds")
    List<Long> getContestPhasesForProposal(@PathVariable("proposalId") Long proposalId);
}
