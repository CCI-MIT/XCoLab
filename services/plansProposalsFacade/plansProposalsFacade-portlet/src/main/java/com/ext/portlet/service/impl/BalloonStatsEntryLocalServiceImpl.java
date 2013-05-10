package com.ext.portlet.service.impl;

import com.ext.portlet.model.BalloonStatsEntry;
import com.ext.portlet.service.base.BalloonStatsEntryLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the balloon stats entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.BalloonStatsEntryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.BalloonStatsEntryLocalServiceBaseImpl
 * @see com.ext.portlet.service.BalloonStatsEntryLocalServiceUtil
 */
public class BalloonStatsEntryLocalServiceImpl
    extends BalloonStatsEntryLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.BalloonStatsEntryLocalServiceUtil} to access the balloon stats entry local service.
     */
    
    public BalloonStatsEntry store(BalloonStatsEntry entry) throws SystemException {
        if (entry.isNew() || entry.getId() <= 0) {
            if (entry.getId() <= 0) {
                entry.setId(CounterLocalServiceUtil.increment(BalloonStatsEntry.class.getName()));
            }
            addBalloonStatsEntry(entry);
        }
        else {
            updateBalloonStatsEntry(entry);
        }
        return entry;
    }
}
