package com.ext.portlet.service.impl;

import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.LoginLog;
import com.ext.portlet.service.LoginLogLocalServiceUtil;
import com.ext.portlet.service.base.LoginLogLocalServiceBaseImpl;
import com.ext.utils.iptranslation.Location;
import com.ext.utils.iptranslation.service.IpTranslationServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

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

	public LoginLog createLoginLog(User user, String ipAddr, String entryUrl) throws SystemException, PortalException {
		Long pk = CounterLocalServiceUtil.increment(LoginLog.class.getName());
		LoginLog newLog = createLoginLog(pk);

		newLog.setUserId(user.getUserId());
		newLog.setCreateDate(new Date());
		newLog.setIpAddress(ipAddr);
		newLog.setEntryUrl(entryUrl.substring(0,Math.min(250, entryUrl.length())));

        try {
            Location userLocation = IpTranslationServiceUtil.getLocationForIp(ipAddr);

            if (Validator.isNotNull(userLocation)) {
                newLog.setCity(userLocation.getCity());
                newLog.setCountry(userLocation.getCountry());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        updateLoginLog(newLog);

		return newLog;
	}
}
