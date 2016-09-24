package org.xcolab.portlets.proposals.wrappers;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.points.PointsTarget;

/**
 * Created by johannes on 12/10/15.
 */
public class PointsTargetProposalWrapper {


    private final PointsTarget target;
    private final double percentageIn;

    public PointsTargetProposalWrapper(PointsTarget target, double percentageIn) {

        this.target = target;
        this.percentageIn = percentageIn;
    }

    public ProposalWrapper getProposal() throws PortalException, SystemException {
        try {
            return new ProposalWrapper(target.getProposalId());
        }catch (ProposalNotFoundException ignored){
            return null;
        }
    }

    public double getPercentage() {
        return target.getPercentage() * percentageIn;
    }
}
