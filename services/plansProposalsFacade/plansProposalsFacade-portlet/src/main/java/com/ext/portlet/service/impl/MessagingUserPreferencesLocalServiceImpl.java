package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchMessagingUserPreferencesException;
import com.ext.portlet.model.MessagingUserPreferences;
import com.ext.portlet.service.base.MessagingUserPreferencesLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the messaging user preferences local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.MessagingUserPreferencesLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.MessagingUserPreferencesLocalServiceBaseImpl
 * @see com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil
 */
public class MessagingUserPreferencesLocalServiceImpl
    extends MessagingUserPreferencesLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil} to access the messaging user preferences local service.
     */
    @Override
    public MessagingUserPreferences findByUser(long userId) throws SystemException {
        try {
            return messagingUserPreferencesPersistence.findBymessagingPreferences(userId);
        } catch (NoSuchMessagingUserPreferencesException e) {
            return null;
        }
    }
}
