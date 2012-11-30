package com.ext.portlet.messaging.service.impl;

import java.util.List;

import com.ext.portlet.messaging.NoSuchMessageRecipientStatusException;
import com.ext.portlet.messaging.model.MessageRecipientStatus;
import com.ext.portlet.messaging.service.base.MessageRecipientStatusLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the message recipient status local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.messaging.service.MessageRecipientStatusLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.messaging.service.base.MessageRecipientStatusLocalServiceBaseImpl
 * @see com.ext.portlet.messaging.service.MessageRecipientStatusLocalServiceUtil
 */
public class MessageRecipientStatusLocalServiceImpl
    extends MessageRecipientStatusLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.messaging.service.MessageRecipientStatusLocalServiceUtil} to access the message recipient status local service.
     */

    public int countByMessageId(long messageId) throws SystemException {
        return messageRecipientStatusPersistence.countByMessageId(messageId);
    }

    public List<MessageRecipientStatus> findByMessageId(long messageId,int start,int end) throws SystemException {
        return messageRecipientStatusPersistence.findByMessageId(messageId,start,end);
    }


    public int countArchivedMessagesForUser(long userid) throws SystemException {
        return messageRecipientStatusPersistence.countByReceivingUserArchived(userid,true);
    }

    public List<MessageRecipientStatus> findArchivedMessagesForUser(long userid,int start, int end) throws SystemException {
        return messageRecipientStatusPersistence.findByReceivingUserArchived(userid,true,start,end);
    }

    public int countInboxMessagesForUser(long userid) throws SystemException {
        return messageRecipientStatusPersistence.countByReceivingUserArchived(userid,false);
    }

    public List<MessageRecipientStatus> findInboxMessagesForUser(long userid,int start, int end) throws SystemException {
        return messageRecipientStatusPersistence.findByReceivingUserArchived(userid,false,start,end);
    }

    public MessageRecipientStatus findByMessageRecipient(long userid, long messageid) throws SystemException, NoSuchMessageRecipientStatusException {
        return messageRecipientStatusPersistence.findByMessageReciever(messageid,userid);

    }
    
}
