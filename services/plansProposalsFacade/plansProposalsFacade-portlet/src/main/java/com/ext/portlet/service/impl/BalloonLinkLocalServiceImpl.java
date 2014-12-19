package com.ext.portlet.service.impl;

import java.util.List;

import com.ext.portlet.model.BalloonLink;
import com.ext.portlet.service.base.BalloonLinkLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the balloon link local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.BalloonLinkLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.BalloonLinkLocalServiceBaseImpl
 * @see com.ext.portlet.service.BalloonLinkLocalServiceUtil
 */
public class BalloonLinkLocalServiceImpl extends BalloonLinkLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.BalloonLinkLocalServiceUtil} to access the balloon link local service.
     */
	
	public BalloonLink getBalloonLinkForUser(String uuid) throws SystemException {
		List<BalloonLink> links = balloonLinkPersistence.findByBalloonUserUuid(uuid);
		if (links == null || links.isEmpty()) {
			return null;
		}
		return links.get(0);
	}
}
