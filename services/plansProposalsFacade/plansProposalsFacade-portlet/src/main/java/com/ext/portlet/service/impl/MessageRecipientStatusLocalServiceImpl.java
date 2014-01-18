package com.ext.portlet.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ext.portlet.NoSuchMessageRecipientStatusException;
import com.ext.portlet.model.Message;
import com.ext.portlet.model.MessageRecipientStatus;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.MessageLocalServiceUtil;
import com.ext.portlet.service.base.MessageRecipientStatusLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

/**
 * The implementation of the message recipient status local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.MessageRecipientStatusLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.MessageRecipientStatusLocalServiceBaseImpl
 * @see com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil
 */
public class MessageRecipientStatusLocalServiceImpl
    extends MessageRecipientStatusLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil} to access the message recipient status local service.
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

    public int countUnreadMessages(long userId) throws SystemException, NoSuchMessageRecipientStatusException{
        List<MessageRecipientStatus> threads = messageRecipientStatusPersistence.findByReceivingUserArchived(userId, false);
        int unreadMessages = 0;
        for (MessageRecipientStatus mr : threads)
            if (!mr.getOpened()) unreadMessages++;
        return unreadMessages;
    }

    public boolean didReceiveJudgeCommentForProposal(Proposal p, User judge) throws SystemException, PortalException {
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        for (Message message : MessageLocalServiceUtil.findSentMessages(judge.getUserId(),0,Integer.MAX_VALUE)){
            Matcher m = pattern.matcher(message.getSubject());
            while (m.find()) {
                String s = m.group(1);
                if (s.equalsIgnoreCase(p.getPrimaryKey() + "")) return true;
            }
        }
        return false;
    }
}
