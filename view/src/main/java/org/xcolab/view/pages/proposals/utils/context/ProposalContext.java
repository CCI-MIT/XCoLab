package org.xcolab.view.pages.proposals.utils.context;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.ContestWrapper;
import org.xcolab.client.contest.pojo.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.client.contest.pojo.Proposal2Phase;
import org.xcolab.view.pages.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.wrappers.ProposalsPreferencesWrapper;

public interface ProposalContext {

    ContestWrapper getContest();

    ContestType getContestType();

    ContestPhaseWrapper getContestPhase();

    Proposal2Phase getProposal2Phase();

    Proposal getProposal();

    ProposalsPreferencesWrapper getPreferences();

    ProposalsPermissions getPermissions();

    ProposalsDisplayPermissions getDisplayPermissions();

    ClientHelper getClients();
}
