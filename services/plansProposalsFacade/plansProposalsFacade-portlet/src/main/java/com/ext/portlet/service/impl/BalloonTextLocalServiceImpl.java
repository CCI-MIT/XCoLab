package com.ext.portlet.service.impl;

import com.ext.portlet.model.BalloonText;
import com.ext.portlet.service.base.BalloonTextLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the balloon text local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.BalloonTextLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.BalloonTextLocalServiceBaseImpl
 * @see com.ext.portlet.service.BalloonTextLocalServiceUtil
 */
public class BalloonTextLocalServiceImpl extends BalloonTextLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.BalloonTextLocalServiceUtil} to access the balloon text local service.
     */
	
	@Override
	public List<BalloonText> getBalloonTextsEnabled(boolean enabled) throws SystemException {
		return balloonTextPersistence.findByEnabled(enabled);
	}
}
