package com.ext.portlet.service.impl;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.base.ContestScheduleLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;

/**
 * The implementation of the contest schedule local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ContestScheduleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ContestScheduleLocalServiceBaseImpl
 * @see com.ext.portlet.service.ContestScheduleLocalServiceUtil
 */
public class ContestScheduleLocalServiceImpl
    extends ContestScheduleLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ContestScheduleLocalServiceUtil} to access the contest schedule local service.
     */

    @Override
    public Boolean isContestScheduleUsed(long contestScheduleId) throws SystemException {

        DynamicQuery queryPhasesForContestScheduleIdNotEqualDefaultScheduleContestId =
                DynamicQueryFactoryUtil.forClass(ContestPhase.class, PortletClassLoaderUtil.getClassLoader())
                        .add(PropertyFactoryUtil.forName("contestScheduleId").eq(contestScheduleId))
                        .add(PropertyFactoryUtil.forName("primaryKey.ContestPhasePK").ne(
                                org.xcolab.client.contest.pojo.ContestPhase.SCHEDULE_TEMPLATE_PHASE_CONTEST_ID));

        return contestPhasePersistence.findWithDynamicQuery(queryPhasesForContestScheduleIdNotEqualDefaultScheduleContestId).size() > 0;
    }

}
