package org.xcolab.portlets.reporting.beans.proposalsinyear.proposalversiondeterminer;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;

/**
 * @author pdeboer
 *         First created on 27/07/14 at 19:22
 */
public interface ProposalVersionDeterminer {
    public int getTargetVersion(Proposal proposal);
}
