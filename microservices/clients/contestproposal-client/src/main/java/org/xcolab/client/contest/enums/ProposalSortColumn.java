package org.xcolab.client.contest.enums;

import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.proposals.ProposalRibbon;

import java.util.Comparator;

public enum ProposalSortColumn {
    NAME(Comparator.comparing(o -> o.getName().toLowerCase())),
    AUTHOR(Comparator.comparing(o -> o.getAuthorName().toLowerCase())),
    SUPPORTERS((o1, o2) -> (int) (o1.getSupportersCount() - o2.getSupportersCount())),
    VOTES((o1, o2) -> (int) (o1.getVotesCountFromCache() - o2.getVotesCountFromCache())),
    COMMENTS((o1, o2) -> (int) (o1.getCommentsCount() - o2.getCommentsCount())),
    JUDGESTATUS(
            (o1, o2) -> (o1.getJudgeStatus().getStatusValue() - o2.getJudgeStatus().getStatusValue())),
    OVERALLSTATUS(
            (o1, o2) -> (o1.getOverallStatus().getStatusValue() - o2.getOverallStatus().getStatusValue())),
    SCREENINGSTATUS(
            (o1, o2) -> (o1.getScreeningStatus().getStatusValue() - o2.getScreeningStatus().getStatusValue())),
    IAFSTATUS(
            (o1, o2) -> (o1.getImpactAssessmentStatus().getStatusValue() - o2.getImpactAssessmentStatus().getStatusValue())),
    MODIFIED(Comparator.comparing(Proposal::getLastupdatedAt)),
    CONTRIBUTORS((o1, o2) -> {
        if (o1.isOpen()) {
            return o2.isOpen() ? 0 : -1;
        } else {
            return o2.isOpen() ? 1 : 0;
        }
    }),
    RIBBONS((o1, o2) -> {
        final ProposalRibbon ribbon1 = o1.getRibbonWrapper();
        final ProposalRibbon ribbon2 = o2.getRibbonWrapper();

        int sortOrderDiff = ribbon1.getSortOrder() - ribbon2.getSortOrder();
        if (sortOrderDiff != 0) {
            return sortOrderDiff;
        }

        return ribbon1.getRibbon() - ribbon2.getRibbon();
    });
    
    private final Comparator<Proposal> proposalsComparator;

    ProposalSortColumn(Comparator<Proposal> comparator) {
        proposalsComparator = comparator;
    }

    public Comparator<Proposal> getComparator() {
        return proposalsComparator;
    }

}
