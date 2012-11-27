package com.ext.portlet.contests.service.impl;

import java.util.List;

import com.ext.portlet.contests.model.ContestPhaseColumn;
import com.ext.portlet.contests.service.base.ContestPhaseColumnLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the contest phase column local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.contests.service.ContestPhaseColumnLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.contests.service.base.ContestPhaseColumnLocalServiceBaseImpl
 * @see com.ext.portlet.contests.service.ContestPhaseColumnLocalServiceUtil
 */
public class ContestPhaseColumnLocalServiceImpl
    extends ContestPhaseColumnLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.contests.service.ContestPhaseColumnLocalServiceUtil} to access the contest phase column local service.
     */

    public List<ContestPhaseColumn> getPhaseColumns(Long contestPhasePK) throws SystemException {
        return contestPhaseColumnPersistence.findByContestPhasePK(contestPhasePK);
    }
    
}
