package org.xcolab.portlets.reporting.beans.proposalsinyear.proposalversiondeterminer;

import com.ext.portlet.model.Proposal;

/**
 * @author pdeboer
 *         First created on 27/07/14 at 19:23
 */
public class NewestProposal implements ProposalVersionDeterminer {
    @Override
    public int getTargetVersion(Proposal proposal) {
        return proposal.getCurrentVersion();
    }
}
