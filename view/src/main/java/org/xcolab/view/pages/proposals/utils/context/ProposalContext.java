package org.xcolab.view.pages.proposals.utils.context;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.view.pages.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.wrappers.ProposalsPreferencesWrapper;

public interface ProposalContext {

    Contest getContest();

    ContestType getContestType();

    ContestPhase getContestPhase();

    Proposal2Phase getProposal2Phase();

    Proposal getProposal();

    ProposalsPreferencesWrapper getPreferences();

    ProposalsPermissions getPermissions();

    ProposalsDisplayPermissions getDisplayPermissions();

    ClientHelper getClients();
}
