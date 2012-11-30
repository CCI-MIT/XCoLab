package com.ext.portlet.messaging.service.impl;

import java.util.List;

import com.ext.portlet.messaging.model.Message;
import com.ext.portlet.messaging.service.base.MessageLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the message local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.messaging.service.MessageLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.messaging.service.base.MessageLocalServiceBaseImpl
 * @see com.ext.portlet.messaging.service.MessageLocalServiceUtil
 */
public class MessageLocalServiceImpl extends MessageLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.messaging.service.MessageLocalServiceUtil} to access the message local service.
     */

    public int countSentMessage(long userid) throws SystemException {
        return messagePersistence.countBySendingUser(userid);
    }

    public List<Message> findSentMessages(long userid, int pagerstart, int pagerend) throws SystemException {
        return messagePersistence.findBySendingUser(userid,pagerstart,pagerend);

    }
}
