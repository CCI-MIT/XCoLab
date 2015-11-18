package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.wrappers.BaseContestPhaseWrapper;

public class ContestPhaseWrapper extends BaseContestPhaseWrapper {
    private ContestPhase contestPhase;
    private ContestStatus status;

    public ContestPhaseWrapper(ContestPhase contestPhase) {
        super(contestPhase);
    }

    public String getContestPhaseUrl() throws SystemException {
        return ContestPhaseLocalServiceUtil.getContestPhaseLinkUrl(contestPhase);
    }
}
