package org.xcolab.portlets.proposals.exceptions;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * Created by Mente on 28/07/15.
 */
public class ProposalIdOrContestIdInvalidException extends SystemException {
    public ProposalIdOrContestIdInvalidException(Exception exception) {
        super (exception);
    }

    public ProposalIdOrContestIdInvalidException() {

    }
}
