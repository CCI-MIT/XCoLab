package com.ext.portlet.service.impl;

import com.ext.portlet.model.LoginLog;
import com.ext.portlet.service.base.LoginLogLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;

import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.Location;

import java.util.Date;

/**
 * The implementation of the login log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.LoginLogLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.LoginLogLocalServiceBaseImpl
 * @see com.ext.portlet.service.LoginLogLocalServiceUtil
 */
public class LoginLogLocalServiceImpl extends LoginLogLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.LoginLogLocalServiceUtil} to access the login log local service.
     */
    
}
