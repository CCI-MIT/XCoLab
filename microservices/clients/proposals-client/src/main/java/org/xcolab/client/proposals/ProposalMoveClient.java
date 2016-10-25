package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.phases.ProposalMoveHistory;
import org.xcolab.client.proposals.pojo.phases.ProposalMoveHistoryDto;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ProposalMoveClient {

    private static final Map<RestService, ProposalMoveClient> instances = new HashMap<>();

    private final RestService proposalService;
    private final RestResource1<ProposalMoveHistoryDto, Long> proposalMoveHistoryResource;

    private ProposalMoveClient(RestService proposalService) {
        proposalMoveHistoryResource = new RestResource1<>(proposalService,
                "proposalMoveHistories", ProposalMoveHistoryDto.TYPES);
        this.proposalService = proposalService;
    }

    public static ProposalMoveClient fromService(RestService proposalService) {
        ProposalMoveClient instance = instances.get(proposalService);
        if (instance == null) {
            instance = new ProposalMoveClient(proposalService);
            instances.put(proposalService, instance);
        }
        return instance;
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
                        CacheRetention.MEDIUM)
                .optionalQueryParam("sourceProposalId", sourceProposalId)
                .optionalQueryParam("sourceContestId", sourceContestId)
                .optionalQueryParam("targetProposalId", targetProposalId)
                .optionalQueryParam("targetContestId", targetContestId)
                .execute(), proposalService);
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
            long targetContestId,
            long srcPhaseId, long targetPhaseId, long userId) {
        return createProposalMoveHistory(proposalId, proposalId, srcContestId, targetContestId,
                srcPhaseId, targetPhaseId, userId, "MOVE_PERMANENTLY");
    }

    public ProposalMoveHistory createProposalMoveHistory(long srcProposalId, long targetProposalId,
            long srcContestId, long targetContestId,
            long sourcePhaseId, long targetPhaseId, long userId, String moveType) {
        ProposalMoveHistory proposalMoveHistory = new ProposalMoveHistory();
        proposalMoveHistory.setSourceProposalId(srcProposalId);
        proposalMoveHistory.setTargetProposalId(targetProposalId);

        proposalMoveHistory.setSourceContestId(srcContestId);
        proposalMoveHistory.setTargetContestId(targetContestId);

        proposalMoveHistory.setSourcePhaseId(sourcePhaseId);
        proposalMoveHistory.setTargetPhaseId(targetPhaseId);

        proposalMoveHistory.setMovingUserId(userId);
        proposalMoveHistory.setMoveDate(new Timestamp(new Date().getTime()));
        proposalMoveHistory.setMoveType(moveType);

        proposalMoveHistory = createProposalMoveHistory(proposalMoveHistory);

        return proposalMoveHistory;
    }

    public ProposalMoveHistory createProposalMoveHistory(ProposalMoveHistory proposalMoveHistory) {
        return proposalMoveHistoryResource
                .create(new ProposalMoveHistoryDto(proposalMoveHistory))
                .execute().toPojo(proposalService);
    }

    public ProposalMoveHistory createCopyProposalMoveHistory(long proposalId, long srcContestId,
            long targetContestId,
            long srcPhaseId, long targetPhaseId, long userId) {
        return createProposalMoveHistory(proposalId, proposalId, srcContestId, targetContestId,
                srcPhaseId, targetPhaseId, userId, "COPY");
    }

    public ProposalMoveHistory createForkProposalMoveHistory(long srcProposalId,
            long targetProposalId, long srcContestId, long targetContestId,
            long srcPhaseId, long targetPhaseId, long userId) {
        return createProposalMoveHistory(srcProposalId, targetProposalId, srcContestId,
                targetContestId, srcPhaseId, targetPhaseId, userId, "FORK");
    }
}
