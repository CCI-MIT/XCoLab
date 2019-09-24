package org.xcolab.view.pages.proposals.utils.context;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.view.pages.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.wrappers.ProposalsPreferencesWrapper;

public interface ProposalContext {

    ContestWrapper getContest();

    ContestType getContestType();

    ContestPhaseWrapper getContestPhase();

    IProposal2Phase getProposal2Phase();

    ProposalWrapper getProposal();

    ProposalsPreferencesWrapper getPreferences();

    ProposalsPermissions getPermissions();

    ProposalsDisplayPermissions getDisplayPermissions();

    ClientHelper getClients();
}
