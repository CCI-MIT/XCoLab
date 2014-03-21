package com.ext.portlet.service.impl;

import java.util.List;

import com.ext.portlet.model.BalloonUserTracking;
import com.ext.portlet.service.base.BalloonUserTrackingLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the balloon user tracking local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.BalloonUserTrackingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.BalloonUserTrackingLocalServiceBaseImpl
 * @see com.ext.portlet.service.BalloonUserTrackingLocalServiceUtil
 */
public class BalloonUserTrackingLocalServiceImpl
    extends BalloonUserTrackingLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.BalloonUserTrackingLocalServiceUtil} to access the balloon user tracking local service.
     */
	
	public List<BalloonUserTracking> findByEmail(String email) throws SystemException {
		return balloonUserTrackingPersistence.findByEmail(email);
	}
}
