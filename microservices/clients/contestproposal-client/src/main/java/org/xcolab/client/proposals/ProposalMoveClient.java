package org.xcolab.client.proposals;

import org.xcolab.client.contest.resources.ProposalResource;
import org.xcolab.client.proposals.pojo.phases.ProposalMoveHistory;
import org.xcolab.client.proposals.pojo.phases.ProposalMoveHistoryDto;
import org.xcolab.util.enums.proposal.MoveType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.dto.DtoUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProposalMoveClient {

    private static final Map<ServiceNamespace, ProposalMoveClient> instances = new HashMap<>();

    private final ServiceNamespace serviceNamespace;
    private final RestResource1<ProposalMoveHistoryDto, Long> proposalMoveHistoryResource;

    private ProposalMoveClient(ServiceNamespace serviceNamespace) {
        proposalMoveHistoryResource = new RestResource1<>(ProposalResource.PROPOSAL_MOVE_HISTORY,
                ProposalMoveHistoryDto.TYPES, serviceNamespace);
        this.serviceNamespace = serviceNamespace;
    }

    public static ProposalMoveClient fromNamespace(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, ProposalMoveClient::new);
    }

    public List<ProposalMoveHistory> getBySourceProposalIdContestId(Long sourceProposalId,
            Long sourceContestId) {
        return getProposalMoveHistory(sourceProposalId, sourceContestId, null, null);
    }

    public List<ProposalMoveHistory> getProposalMoveHistory(Long sourceProposalId,
            Long sourceContestId, Long targetProposalId, Long targetContestId) {
        return DtoUtil.toPojos(proposalMoveHistoryResource.list()
                .withCache(CacheKeys.withClass(ProposalMoveHistoryDto.class)
                                .withParameter("sourceProposalId", sourceProposalId)
                                .withParameter("sourceContestId", sourceContestId)
                                .withParameter("targetProposalId", targetProposalId)
                                .withParameter("targetContestId", targetContestId)
                                .asList(),
                        CacheName.MISC_MEDIUM)
                .optionalQueryParam("sourceProposalId", sourceProposalId)
                .optionalQueryParam("sourceContestId", sourceContestId)
                .optionalQueryParam("targetProposalId", targetProposalId)
                .optionalQueryParam("targetContestId", targetContestId)
                .execute(), serviceNamespace);
    }

    public ProposalMoveHistory getByTargetProposalIdContestId(Long targetProposalId,
            Long targetContestId) {
        List<ProposalMoveHistory> list =
                getProposalMoveHistory(null, null, targetProposalId, targetContestId);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public ProposalMoveHistory createProposalMoveHistory(long proposalId, long srcContestId,
            long targetContestId, long targetPhaseId, long userId) {
        return createProposalMoveHistory(proposalId, proposalId, srcContestId, targetContestId,
                targetPhaseId, userId, MoveType.MOVE_PERMANENTLY);
    }

    public ProposalMoveHistory createProposalMoveHistory(long srcProposalId, long targetProposalId,
            long srcContestId, long targetContestId,
            long targetPhaseId, long userId, MoveType moveType) {
        ProposalMoveHistory proposalMoveHistory = new ProposalMoveHistory();
        proposalMoveHistory.setSourceProposalId(srcProposalId);
        proposalMoveHistory.setTargetProposalId(targetProposalId);

        proposalMoveHistory.setSourceContestId(srcContestId);
        proposalMoveHistory.setTargetContestId(targetContestId);

        proposalMoveHistory.setTargetPhaseId(targetPhaseId);

        proposalMoveHistory.setMovingUserId(userId);
        proposalMoveHistory.setMoveDate(new Timestamp(new Date().getTime()));
        proposalMoveHistory.setMoveType(moveType.name());

        proposalMoveHistory = createProposalMoveHistory(proposalMoveHistory);

        return proposalMoveHistory;
    }

    public ProposalMoveHistory createProposalMoveHistory(ProposalMoveHistory proposalMoveHistory) {
        return proposalMoveHistoryResource
                .create(new ProposalMoveHistoryDto(proposalMoveHistory))
                .execute().toPojo(serviceNamespace);
    }

    public ProposalMoveHistory createCopyProposalMoveHistory(long proposalId, long srcContestId,
            long targetContestId,
            long targetPhaseId, long userId) {
        return createProposalMoveHistory(proposalId, proposalId, srcContestId, targetContestId,
                targetPhaseId, userId, MoveType.COPY);
    }

    public ProposalMoveHistory createForkProposalMoveHistory(long srcProposalId,
            long targetProposalId, long srcContestId, long targetContestId,
            long targetPhaseId, long userId) {
        return createProposalMoveHistory(srcProposalId, targetProposalId, srcContestId,
                targetContestId, targetPhaseId, userId, MoveType.FORK);
    }
}
