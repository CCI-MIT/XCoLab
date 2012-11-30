package com.ext.portlet.messaging.model.impl;

import java.util.List;

import com.ext.portlet.messaging.NoSuchMessageRecipientStatusException;
import com.ext.portlet.messaging.model.MessageRecipientStatus;
import com.ext.portlet.messaging.service.MessageRecipientStatusLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model implementation for the Message service. Represents a row in the &quot;Messaging_Message&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.messaging.model.Message} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class MessageImpl extends MessageBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a message model instance should use the {@link com.ext.portlet.messaging.model.Message} interface instead.
     */
    public MessageImpl() {
    }
    

    public List<MessageRecipientStatus> getRecipients() throws SystemException {
        return MessageRecipientStatusLocalServiceUtil.findByMessageId(getMessageId(),0,Short.MAX_VALUE);
    }

    public boolean hasReciever(long userid) throws SystemException {
        MessageRecipientStatus status = null;
        try {
            status = MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid,getMessageId());
        }  catch (NoSuchMessageRecipientStatusException e) {
           //no worries
        }
        return status!=null;
    }

    public boolean isOpened(long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        return MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid,getMessageId()).getOpened();
    }

    public void setOpened(long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        MessageRecipientStatus status = MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid,getMessageId());
        status.setOpened(true);
        MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus(status);
    }

    public boolean isArchived(long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        return MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid,getMessageId()).getArchived();
    }

    public void setArchived(long userid) throws SystemException, NoSuchMessageRecipientStatusException {
        MessageRecipientStatus status = MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(userid,getMessageId());
        status.setArchived(true);
        MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus(status);
    }

}
