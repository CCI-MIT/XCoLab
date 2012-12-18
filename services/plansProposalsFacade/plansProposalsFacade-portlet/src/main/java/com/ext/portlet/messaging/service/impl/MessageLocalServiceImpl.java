package com.ext.portlet.messaging.service.impl;

import java.util.List;

import com.ext.portlet.messaging.NoSuchMessageRecipientStatusException;
import com.ext.portlet.messaging.model.Message;
import com.ext.portlet.messaging.model.MessageRecipientStatus;
import com.ext.portlet.messaging.service.MessageRecipientStatusLocalServiceUtil;
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
    

    public List<MessageRecipientStatus> getRecipients(Message msg) throws SystemException {
        return MessageRecipientStatusLocalServiceUtil.findByMessageId(msg.getMessageId(),0,Short.MAX_VALUE);
    }

    public boolean hasReciever(Message msg, long userid) throws SystemException {
        MessageRecipientStatus status = null;
        try {
            status = MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid, msg.getMessageId());
        }  catch (NoSuchMessageRecipientStatusException e) {
           //no worries
        }
        return status!=null;
    }

    public boolean isOpened(Message msg, long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        return MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid, msg.getMessageId()).getOpened();
    }

    public void setOpened(Message msg, long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        MessageRecipientStatus status = MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid, msg.getMessageId());
        status.setOpened(true);
        MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus(status);
    }

    public boolean isArchived(Message msg, long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        return MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid, msg.getMessageId()).getArchived();
    }

    public void setArchived(Message msg, long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        MessageRecipientStatus status = MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid, msg.getMessageId());
        status.setArchived(true);
        MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus(status);
    }
}
