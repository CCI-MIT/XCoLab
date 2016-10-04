package org.xcolab.client.proposals;

import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.proposals.pojo.ProposalSupporter;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public final class ProposalSupporterClient {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final RestResource1<ProposalSupporter, Long> proposalSupporterResource = new RestResource1<>(proposalService,
            "proposalSupporters", ProposalSupporter.TYPES);

    public static List<ProposalSupporter> getProposalSupporters(Long proposalId) {
        return proposalSupporterResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }

    public static List<ProposalSupporter> getProposalSupportersByUserId(Long userId) {
        return proposalSupporterResource.list()
                .optionalQueryParam("userId", userId)
                .execute();
    }

    public static Integer getProposalSupportersCount(Long proposalId) {

        return proposalSupporterResource.service("count", Integer.class)
                .optionalQueryParam("proposalId", proposalId)
                .get();

    }

    public static Boolean isMemberProposalSupporter(Long proposalId, Long memberId) {

        return proposalSupporterResource.service("isMemberProposalSupporter", Boolean.class)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("memberId", memberId)
                .get();

    }

    public static ProposalSupporter createProposalSupporter(ProposalSupporter proposalSupporter) {
        return proposalSupporterResource.create(proposalSupporter).execute();
    }

    public static Boolean deleteProposalSupporter(Long proposalId, Long memberId) {
        return proposalSupporterResource.service("deleteProposalSupporter", Boolean.class)
                .queryParam("proposalId", proposalId)
                .queryParam("memberId", memberId)
                .delete();
    }

    public static void addProposalSupporter(long proposalId, long userId) {
        addProposalSupporter(proposalId, userId, true);
    }

    public static void addProposalSupporter(long proposalId, long userId, boolean publishActivity) {
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

    public static void removeProposalSupporter(long proposalId, long userId) {
        deleteProposalSupporter(proposalId, userId);
        ActivityEntryHelper.createActivityEntry(userId, proposalId, null,
                ActivityProvidersType.ProposalSupporterRemovedActivityEntry.getType());
    }
}
