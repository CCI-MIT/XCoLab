package com.ext.portlet.service.impl;

import com.ext.portlet.model.AnalyticsUserEvent;
import com.ext.portlet.service.base.AnalyticsUserEventLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.AnalyticsUserEventPK;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;

/**
 * The implementation of the analytics user event local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.AnalyticsUserEventLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.AnalyticsUserEventLocalServiceBaseImpl
 * @see com.ext.portlet.service.AnalyticsUserEventLocalServiceUtil
 */
public class AnalyticsUserEventLocalServiceImpl
    extends AnalyticsUserEventLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.AnalyticsUserEventLocalServiceUtil} to access the analytics user event local service.
     */
	
	@Override
	public boolean eventExists(long userId, String idString) {
		try {
			return (analyticsUserEventLocalService.fetchAnalyticsUserEvent(new AnalyticsUserEventPK(userId, idString)) != null);
		} catch (SystemException e) {
			return false;
		}
	}
	
	@Override
	public AnalyticsUserEvent createEvent(long userId, String idString, String category, String action, String label, int value) throws SystemException {
		AnalyticsUserEvent event = analyticsUserEventLocalService.createAnalyticsUserEvent(new AnalyticsUserEventPK(userId, idString));
		
		event.setCreated(new Date());
		event.setCategory(category);
		event.setAction(action);
		event.setLabel(label);
		event.setValue(value);
		
		analyticsUserEventLocalService.addAnalyticsUserEvent(event);
		
		return event;
	}
}
