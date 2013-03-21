/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.portlets.massmessaging.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xcolab.portlets.massmessaging.InvalidMessageRecipientException;
import org.xcolab.portlets.massmessaging.MessageSendAsBean;
import org.xcolab.portlets.massmessaging.MessagingConstants;
import org.xcolab.portlets.massmessaging.MessagingUtils;

import com.ext.portlet.model.MessagingIgnoredRecipients;
import com.ext.portlet.model.MessagingMessage;
import com.ext.portlet.model.MessagingMessageRecipient;
import com.ext.portlet.model.MessagingRedirectLink;
import com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil;
import com.ext.portlet.service.MessagingMessageLocalServiceUtil;
import com.ext.portlet.service.MessagingMessageRecipientLocalServiceUtil;
import com.ext.portlet.service.MessagingRedirectLinkLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.portlet.PortletProps;

public class EditMessagingMessageAction extends BaseStrutsPortletAction {

    private final static String emailValidationRegexp = "^([a-zA-Z0-9_\\.-])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$"; 
    
    private final static Pattern linkSearchRegexp = Pattern.compile("<a[^>]*href=\\\"");
    
    private final static String HTML_HEADER = "<html><head></head><body>";
    private final static String HTML_FOOTER = "</body></html>";

    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
        
        
        renderRequest.setAttribute("sendAs", readSendAsProperties());
        
        System.out.println(readSendAsProperties());

        return mapping.findForward(MessagingConstants.EDIT_MESSAGE_FORWARD);
    }

    public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig, 
            ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        

        String name = ParamUtil.getString(actionRequest, "name");
        String description = ParamUtil.getString(actionRequest, "description");
        String replyTo = ParamUtil.getString(actionRequest, "replyto");
        String subject = ParamUtil.getString(actionRequest, "subject");
        String body = ParamUtil.getString(actionRequest, "body"); 
        String recipientsStr = ParamUtil.getString(actionRequest, "recipients");
        String messageSenderName = ParamUtil.getString(actionRequest, "messageSenderName");
        boolean sendToAll = ParamUtil.getBoolean(actionRequest, "sendtoall");

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        Long companyId = themeDisplay.getCompanyId();


        if (isNullOrEmpty(name) || isNullOrEmpty(replyTo) || isNullOrEmpty(subject) || isNullOrEmpty(body)) {
            SessionErrors.add(actionRequest, "Input validation error");
        }
        
        // get ignored recipients 
        List<MessagingIgnoredRecipients> ignoredRecipients =
            MessagingIgnoredRecipientsLocalServiceUtil.getMessagingIgnoredRecipientses(0, Integer.MAX_VALUE);
        
        Set<String> doNotSendSet = new HashSet<String>();
        
        for (MessagingIgnoredRecipients recipient: ignoredRecipients) {
            doNotSendSet.add(recipient.getEmail());
            if (recipient.getName() != null) {
                doNotSendSet.add(recipient.getName());
            }
        }
        
        
        // validate recipients
        String[] recipientsArray = recipientsStr.split(MessagingConstants.RECIPIENT_DELIMITER);
        List<MessagingMessageRecipient> recipients = new ArrayList<MessagingMessageRecipient>();
        try {
            for(String recipientName: recipientsArray) {
                if (doNotSendSet.contains(recipientName)) {
                    continue;
                }
                
                if (recipientName.trim().equals("")) {
                    continue;
                }
                
                Long recipientId = CounterLocalServiceUtil.increment(MessagingMessageRecipient.class.getName());
                MessagingMessageRecipient msgRecipient = 
                    MessagingMessageRecipientLocalServiceUtil.createMessagingMessageRecipient(recipientId);

                if (recipientName.lastIndexOf('@') > 0) {
                    // we have an email
                    if (!recipientName.matches(emailValidationRegexp)) {
                        throw new InvalidMessageRecipientException("Provided email is invalid: " + recipientName);
                    }
                    msgRecipient.setEmailAddress(recipientName);
                }
                else if (!sendToAll) {
                    try {
                        User user = UserLocalServiceUtil.getUserByScreenName(companyId, recipientName);
                        if (!doNotSendSet.contains(user.getEmailAddress()) && !doNotSendSet.contains(user.getScreenName())) {
                            msgRecipient.setUserId(user.getUserId());
                            msgRecipient.setEmailAddress(user.getEmailAddress());
                        }
                    }
                    catch (NoSuchUserException e) {
                        throw new InvalidMessageRecipientException("User with screen name: " + 
                                recipientName + " doesn't exist", e);
                    }
                }
                recipients.add(msgRecipient);
            }
        }
        catch (Exception e) {
            if (e instanceof InvalidMessageRecipientException) {
                SessionErrors.add(actionRequest, e.getClass().getName());
            }
            return;
        }
        if (recipients.size() == 0 && !sendToAll) {
            SessionErrors.add(actionRequest, "No recipients given.");
            return;
        }
        
        // validation complete save 


        Date now = new Date();


        Long messageId = CounterLocalServiceUtil.increment(MessagingMessage.class.getName());
        MessagingMessage message = MessagingMessageLocalServiceUtil.createMessagingMessage(messageId);
        
        
        StringBuilder messageBody = new StringBuilder();   
        
        // rewrite all links to track if they are clicked
        Matcher linkMatcher = linkSearchRegexp.matcher(body);
        StringBuilder modifiedBody = new StringBuilder();
        int lastAppendIndex = 0;

        Map<String, String> linkParameters = new HashMap<String, String>();
        linkParameters.put(MessagingConstants.PARAMETER_ACTION, MessagingConstants.ACTION_REDIRECT);
        linkParameters.put(MessagingConstants.PARAMETER_MESSAGE_ID, String.valueOf(messageId));
        
        while (linkMatcher.find()) {
            
            int linkEndIndex = body.indexOf('"', linkMatcher.end() + 1);
            
            String linkURL = body.substring(linkMatcher.end(), linkEndIndex);

            Long redirectId = CounterLocalServiceUtil.increment(MessagingRedirectLink.class.getName());
            MessagingRedirectLink redirectLink = MessagingRedirectLinkLocalServiceUtil.createMessagingRedirectLink(redirectId);
            
            redirectLink.setLink(linkURL);
            redirectLink.setCreateDate(now);
            redirectLink.setMessageId(messageId);
            
            MessagingRedirectLinkLocalServiceUtil.addMessagingRedirectLink(redirectLink);
            
            linkParameters.put(MessagingConstants.PARAMETER_REDIRECT_ID, String.valueOf(redirectId));
            linkParameters.put(MessagingConstants.PARAMETER_RECIPIENT_ID, MessagingConstants.RECIPIENT_ID_PLACEHOLDER);
            
            String newLink = MessagingUtils.createConvertionLink(linkParameters, actionRequest);

            modifiedBody.append(body.substring(lastAppendIndex, linkMatcher.end()));
            modifiedBody.append(newLink);
            
            lastAppendIndex = linkEndIndex;
        }
        
        modifiedBody.append(body.substring(lastAppendIndex));
            

        // add link to image to trace mail opening
        linkParameters.clear();
        linkParameters.put(MessagingConstants.PARAMETER_ACTION, MessagingConstants.ACTION_IMAGE);
        linkParameters.put(MessagingConstants.PARAMETER_MESSAGE_ID, String.valueOf(messageId));
        
        String imgStr = " <img src=\"" + MessagingUtils.createConvertionLink(linkParameters, actionRequest) + "\" />";
        
        messageBody.append(HTML_HEADER);
        messageBody.append(modifiedBody);
        messageBody.append(imgStr);
        messageBody.append(HTML_FOOTER);
        
        body = messageBody.toString();
        
        if (sendToAll) {
            List<User> users = UserLocalServiceUtil.getUsers(0, UserLocalServiceUtil.getUsersCount());
            for(User user: users) {
                if (! isNullOrEmpty(user.getEmailAddress()) && 
                        !doNotSendSet.contains(user.getScreenName()) && !doNotSendSet.contains(user.getEmailAddress())) {
                    
                    Long recipientId = CounterLocalServiceUtil.increment(MessagingMessageRecipient.class.getName());
                    MessagingMessageRecipient msgRecipient = 
                        MessagingMessageRecipientLocalServiceUtil.createMessagingMessageRecipient(recipientId);
                    
                    msgRecipient.setEmailAddress(user.getEmailAddress());
                    msgRecipient.setUserId(user.getUserId());
                    recipients.add(msgRecipient);
                }
            }
        }
        
        if (recipients.size() == 0) {
            SessionErrors.add(actionRequest, "No recipients given.");
            return;
        }
        
        
        // save all recipients
        for(MessagingMessageRecipient rec: recipients) {
            rec.setMessageId(messageId);
            MessagingMessageRecipientLocalServiceUtil.addMessagingMessageRecipient(rec);
        }
        
        
        
        message.setName(name);
        message.setDescription(description);
        message.setReplyTo(replyTo);
        message.setSubject(subject);
        message.setBody(body.replaceAll(MessagingConstants.RECIPIENT_ID_PLACEHOLDER, String.valueOf(recipients.get(0).getRecipientId())));
        message.setConversionCount(0L);


        message.setCreatorId(themeDisplay.getUserId());
        message.setCreateDate(now);
        message.setModifiedDate(now);
        message.setSendToAll(sendToAll);

        MessagingMessageLocalServiceUtil.addMessagingMessage(message);

        InternetAddress from = new InternetAddress(replyTo);
        if (!isNullOrEmpty(messageSenderName)) {
            from.setPersonal(messageSenderName);    
        }
        for(MessagingMessageRecipient rec: recipients) {
            InternetAddress to = new InternetAddress(rec.getEmailAddress());
            //System.out.println(to.getAddress());
            MailEngine.send(from, to, subject, body.replaceAll(MessagingConstants.RECIPIENT_ID_PLACEHOLDER, String.valueOf(rec.getRecipientId())), true);
        }
        
        SessionMessages.add(actionRequest, "Message was sent");
        actionResponse.sendRedirect(MessagingConstants.VIEW_MESSAGE_DETAILS + messageId);
        
    }
    
    public static boolean isNullOrEmpty(String var) {
        if (var == null || var.trim().equals("")) {
            return true;
        }
        return false;
    }
    
    private List<MessageSendAsBean> readSendAsProperties() {
        String accounts = PortletProps.get("user.accounts");
        List<MessageSendAsBean> msgSendAsBeans = new ArrayList<MessageSendAsBean>();
        if (StringUtils.isNotBlank(accounts)) {
            for (String account: accounts.split(",")) {
                MessageSendAsBean sendAsBean = new MessageSendAsBean();
                sendAsBean.setName(account);
                sendAsBean.setPassword(PortletProps.get("user.account." + account + ".password"));
                sendAsBean.setHost(PortletProps.get("user.account." + account + ".host"));
                sendAsBean.setPort(Integer.valueOf(PortletProps.get("user.account." + account + ".port")));
            }
        }
        return msgSendAsBeans;
    }

}
