package org.xcolab.view.pages.proposals.tabs.access;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.tabs.ProposalTabCanAccessAlgorithm;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextWrapper;

public class PointsAccessAlgorithm implements ProposalTabCanAccessAlgorithm {

    private final boolean isEdit;

    private PointsAccessAlgorithm(boolean isEdit) {
        this.isEdit = isEdit;
    }

    public static PointsAccessAlgorithm view() {
        return new PointsAccessAlgorithm(false);
    }

    public static PointsAccessAlgorithm edit() {
        return new PointsAccessAlgorithm(true);
    }

    @Override
    public boolean canAccess(ProposalsContextWrapper contextWrapper) {
        Contest contest = contextWrapper.getContest();
        final boolean pointsActive = contest != null && contest.getDefaultParentPointType() > 0;
        if (!pointsActive) {
            return false;
        }

        ProposalsPermissions permissions = contextWrapper.getPermissions();
        final boolean canEdit = permissions.getIsTeamMember() || permissions.getCanAdminProposal();
        if (isEdit && !canEdit) {
            return false;
        }

        final ContestClient contestClient = contextWrapper.getClients().getContestClient();
        int pointsAccessible = contestClient.getPointsAccessibleForActivePhaseOfContest(contest);
        if (isEdit) {
            final boolean editAllowed = pointsAccessible >= 2;
            return editAllowed || permissions.getCanAdminAll();
        } else {
            return pointsAccessible >= 1;
        }
    }
}
