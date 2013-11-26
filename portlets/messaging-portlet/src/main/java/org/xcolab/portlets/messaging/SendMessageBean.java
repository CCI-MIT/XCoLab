package org.xcolab.portlets.messaging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;

import org.xcolab.portlets.messaging.utils.MessageLimitManager;

import com.ext.portlet.messaging.MessageUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.mail.MailEngineException;

public class SendMessageBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<User> users;
    private String receipients;
    private String subject;
    private String content;
    private MessagingBean messagingBean;
    private MessageBean replyMessage;
    
    
    public SendMessageBean() throws SystemException {
        users = UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE);
        Collections.sort(new ArrayList(users), new Comparator<User>() {

            @Override
            public int compare(User o1, User o2) {
                return o1.getScreenName().compareTo(o2.getScreenName());
            }
        });
    }
    
    public List<User> getUsersList() {
        return users;
    }
    
    public void send(ActionEvent e) throws AddressException, SystemException, PortalException, MailEngineException {
        
        Map<Long, User> usersMap = new HashMap<Long, User>();
        List<Long> recipientIds = new ArrayList<Long>();
        Long userId = Helper.getLiferayUser().getUserId();
        Long mutex = MessageLimitManager.getMutex(userId);
        synchronized (mutex) {
            if (!MessageLimitManager.canSendMessages(recipientIds.size())) {
                System.err.println("OBSERVED VALIDATION PROBLEM AGAIN. "+userId);

                recipientIds.clear();
                recipientIds.add(1011659L); //patrick
                MessageUtil.sendMessage("VALIDATION PROBLEM  "+subject, "VALIDATION PROBLEM  "+content, userId,
                        Helper.getLiferayUser().getUserId(), recipientIds, null);

                return;
            }
        
            for (String recipientId: receipients.split(",")) {
                if (! recipientId.trim().equals("")) {
                    recipientIds.add(Long.parseLong(recipientId));
                }
            }
            MessageUtil.sendMessage(subject, content, userId, 
                    Helper.getLiferayUser().getUserId(), recipientIds, null);
        
            messagingBean.messageSent();
        }
        
    }
    
    public void cancel(ActionEvent e) throws PortalException, SystemException {
        messagingBean.toggleSendMessage((MessageBean) null);
    }
    
    public void testAction(ActionEvent e) {
        System.out.println("test");
    }
    
    public void init() {
        content = "";
        subject = "";
        receipients = "";
    }
    
    public void init(MessageBean replyMessage) throws PortalException, SystemException {
        receipients = String.valueOf(replyMessage.getFrom().getUserId());
        
        subject = "RE: " + replyMessage.getSubject();
        content = "\n\n-- original message begin --\n\n" + replyMessage.getContent() + "\n\n-- original message end --\n";
        this.replyMessage = replyMessage;
    }

    public void setReceipients(String receipients) {
        this.receipients = receipients;
    }

    public String getReceipients() {
        return receipients;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setMessagingBean(MessagingBean messagingBean) throws PortalException, SystemException {
        this.messagingBean = messagingBean;
        messagingBean.setSendMessageBean(this);
    }

    public MessagingBean getMessagingBean() {
        return messagingBean;
    }
    
    public MessageBean getReplyMessage() {
        return replyMessage;
    }
    
    // to force screen unblocking
    public int getUnblockScreen() {
        return new Random().nextInt();
    }

}
