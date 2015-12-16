package com.ext.portlet.service.impl;

import java.util.List;

import com.ext.portlet.NoSuchMessageRecipientStatusException;
import com.ext.portlet.model.Message;
import com.ext.portlet.model.MessageRecipientStatus;
import com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil;
import com.ext.portlet.service.base.MessageLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the message local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.MessageLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.MessageLocalServiceBaseImpl
 * @see com.ext.portlet.service.MessageLocalServiceUtil
 */
public class MessageLocalServiceImpl extends MessageLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.MessageLocalServiceUtil} to access the message local service.
     */

    @Override
    public int countSentMessage(long userid) throws SystemException {
        return messagePersistence.countBySendingUser(userid);
    }

    @Override
    public List<Message> findSentMessages(long userid, int pagerstart, int pagerend) throws SystemException {
        return messagePersistence.findBySendingUser(userid,pagerstart,pagerend);

    }
    

    @Override
    public List<MessageRecipientStatus> getRecipients(Message msg) throws SystemException {
        return MessageRecipientStatusLocalServiceUtil.findByMessageId(msg.getMessageId(),0,Short.MAX_VALUE);
    }

    @Override
    public boolean hasReciever(Message msg, long userid) throws SystemException {
        MessageRecipientStatus status = null;
        try {
            status = MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid, msg.getMessageId());
        }  catch (NoSuchMessageRecipientStatusException e) {
           //no worries
        }
        return status!=null;
    }

    @Override
    public boolean isOpened(Message msg, long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        return MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid, msg.getMessageId()).getOpened();
    }

    @Override
    public void setOpened(Message msg, long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        MessageRecipientStatus status = MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid, msg.getMessageId());
        status.setOpened(true);
        MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus(status);
    }

    @Override
    public boolean isArchived(Message msg, long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        return MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid, msg.getMessageId()).getArchived();
    }

    @Override
    public void setArchived(Message msg, long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        MessageRecipientStatus status = MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid, msg.getMessageId());
        status.setArchived(true);
        MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus(status);
    }
}
