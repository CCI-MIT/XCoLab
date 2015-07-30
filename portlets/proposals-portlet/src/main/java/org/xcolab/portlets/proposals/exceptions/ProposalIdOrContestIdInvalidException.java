package org.xcolab.portlets.proposals.exceptions;

import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.proposals.wrappers.InvalidUrlErrorWrapper;

/**
 * Created by Mente on 28/07/15.
 */
public class ProposalIdOrContestIdInvalidException extends SystemException {
    private InvalidUrlErrorWrapper invalidUrlErrorWrapper;

    public ProposalIdOrContestIdInvalidException(Exception exception) {
        super (exception);
        invalidUrlErrorWrapper = new InvalidUrlErrorWrapper();
    }

    public ProposalIdOrContestIdInvalidException() {
        invalidUrlErrorWrapper = new InvalidUrlErrorWrapper();
    }

    public void setInvalidUrlErrorWrapper(InvalidUrlErrorWrapper invalidUrlErrorWrapper) {
        this.invalidUrlErrorWrapper = invalidUrlErrorWrapper;
    }

    public InvalidUrlErrorWrapper getInvalidUrlErrorWrapper() {
        return this.invalidUrlErrorWrapper;
    }
}
