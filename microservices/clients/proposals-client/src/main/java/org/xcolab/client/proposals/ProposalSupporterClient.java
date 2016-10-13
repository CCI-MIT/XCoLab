package org.xcolab.client.proposals;

import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporter;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporterDto;
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

public final class ProposalSupporterClient {

    private static final Map<RestService, ProposalSupporterClient> instances = new HashMap<>();

    private final RestService proposalService;
    private final RestResource1<ProposalSupporterDto, Long> proposalSupporterResource;

    private ProposalSupporterClient(RestService proposalService) {
        proposalSupporterResource = new RestResource1<>(proposalService,
                "proposalSupporters", ProposalSupporterDto.TYPES);
        this.proposalService = proposalService;
    }

    public static ProposalSupporterClient fromService(RestService proposalService) {
        ProposalSupporterClient instance = instances.get(proposalService);
        if (instance == null) {
            instance = new ProposalSupporterClient(proposalService);
            instances.put(proposalService, instance);
        }
        return instance;
    }

    public List<ProposalSupporter> getProposalSupporters(Long proposalId) {
        return DtoUtil.toPojos(proposalSupporterResource.list()
                .withCache(CacheKeys.withClass(ProposalSupporterDto.class)
                                .withParameter("proposalId", proposalId)
                                .asList(),
                        CacheRetention.MEDIUM)
                .optionalQueryParam("proposalId", proposalId)
                .execute(), proposalService);
    }

    public List<ProposalSupporter> getProposalSupportersByUserId(Long userId) {
        return DtoUtil.toPojos(proposalSupporterResource.list()
                .optionalQueryParam("userId", userId)
                .execute(), proposalService);
    }

    public Integer getProposalSupportersCount(Long proposalId) {

        return proposalSupporterResource.service("count", Integer.class)
                .optionalQueryParam("proposalId", proposalId)
                .get();
    }

    public Boolean isMemberProposalSupporter(Long proposalId, Long memberId) {

        return proposalSupporterResource.service("isMemberProposalSupporter", Boolean.class)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("memberId", memberId)
                .get();
    }

    public void addProposalSupporter(long proposalId, long userId) {
        addProposalSupporter(proposalId, userId, true);
    }

    public void addProposalSupporter(long proposalId, long userId, boolean publishActivity) {
        ProposalSupporter supporter = new ProposalSupporter();
        supporter.setProposalId(proposalId);
        supporter.setUserId(userId);
        supporter.setCreateDate(new Timestamp(new Date().getTime()));
        createProposalSupporter(supporter);

        if (publishActivity) {
            ActivityEntryHelper.createActivityEntry(userId, proposalId, null,
                    ActivityProvidersType.ProposalSupporterAddedActivityEntry.getType());
        }
    }

    public ProposalSupporter createProposalSupporter(ProposalSupporter proposalSupporter) {
        return proposalSupporterResource
                .create(new ProposalSupporterDto(proposalSupporter))
                .execute().toPojo(proposalService);
    }

    public void removeProposalSupporter(long proposalId, long userId) {
        deleteProposalSupporter(proposalId, userId);
        ActivityEntryHelper.createActivityEntry(userId, proposalId, null,
                ActivityProvidersType.ProposalSupporterRemovedActivityEntry.getType());
    }

    public Boolean deleteProposalSupporter(Long proposalId, Long memberId) {
        return proposalSupporterResource.service("deleteProposalSupporter", Boolean.class)
                .queryParam("proposalId", proposalId)
                .queryParam("memberId", memberId)
                .delete();
    }
}
