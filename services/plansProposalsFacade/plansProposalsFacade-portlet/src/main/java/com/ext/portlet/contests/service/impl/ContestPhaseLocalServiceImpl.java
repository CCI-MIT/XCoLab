package com.ext.portlet.contests.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ext.portlet.contests.NoSuchContestPhaseException;
import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.base.ContestPhaseLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the contest phase local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.contests.service.ContestPhaseLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.contests.service.base.ContestPhaseLocalServiceBaseImpl
 * @see com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil
 */
public class ContestPhaseLocalServiceImpl
    extends ContestPhaseLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil} to access the contest phase local service.
     */

    public List<ContestPhase> getPhasesForContest(Contest contest) throws SystemException {
        List<ContestPhase> result = new ArrayList<ContestPhase>();
        return contestPhasePersistence.findByContestId(contest.getContestPK());
    }

    public ContestPhase getActivePhaseForContest(Contest contest) throws NoSuchContestPhaseException, SystemException {
        Date now = new Date();
        return contestPhasePersistence.findByContestIdStartEnd(contest.getContestPK(), now, now);
    }
}
