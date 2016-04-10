package org.xcolab.portlets.messaging.views;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.mail.MailEngineException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.xcolab.legacy.enums.MessageType;
import org.xcolab.pojo.Message;
import org.xcolab.portlets.messaging.beans.MessageBean;
import org.xcolab.portlets.messaging.beans.MessagingBean;
import org.xcolab.portlets.messaging.beans.SendMessageBean;
import org.xcolab.portlets.messaging.utils.MessagingPermissions;
import org.xcolab.service.client.MessagingClient;
import org.xcolab.utils.LinkUtils;

import javax.mail.internet.AddressException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("view")
public class MessagingController {

    @RenderMapping
    public String showMessages(RenderRequest request, RenderResponse response, Model model,
            @RequestParam(required = false) String mailboxType,
            @RequestParam(required = false) Integer pageNumber)
            throws SystemException, PortalException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();

        final MessagingBean messagingBean = new MessagingBean(user,
                pageNumber != null ? pageNumber : 1,
                StringUtils.isNotBlank(mailboxType) ? MessageType.valueOf(mailboxType) : MessageType.INBOX);
        model.addAttribute("messagingBean", messagingBean);

        return "messages";
    }

    @RenderMapping(params = {"page=composeMessage"})
    public String composeMessage(RenderRequest request, RenderResponse response, Model model,
            @RequestParam(required = false) Integer messageId)
            throws SystemException, PortalException {

        model.addAttribute("sendMessageBean", new SendMessageBean());
        return "composeMessage";
    }

    @RenderMapping(params = {"page=viewMessage"})
    public String showMessage(RenderRequest request, RenderResponse response, Model model,
            @RequestParam(required = false) Integer messageId)
            throws SystemException, PortalException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();

        model.addAttribute("user", user);

        final MessageBean messageBean = new MessageBean(MessagingClient.getMessage(messageId));
        messageBean.markMessageAsOpened(user.getUserId());
        final SendMessageBean sendMessageBean = new SendMessageBean(messageBean);
        model.addAttribute("sendMessageBean", sendMessageBean);
        model.addAttribute("messageBean", messageBean);

        return "message";
    }

    @RequestMapping(params = {"action=archiveMessages"})
    public void archiveMessages(ActionRequest request, ActionResponse response, Model model,
            @ModelAttribute("messagingBean") MessagingBean messagingBean)
            throws PortalException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();

        List<MessageBean> items = messagingBean.getDataPage().getMessages();
        for (MessageBean item : items) {
            if (item.isSelected()) {
                Message message = item.getMessage();
                MessagingClient.setArchived(message.getMessageId(), user.getUserId(), true);
            }
        }
    }

    @RequestMapping(params = {"action=sendMessage"})
    public void sendMessage(ActionRequest request, ActionResponse response, Model model,
            @ModelAttribute("sendMessageBean") SendMessageBean sendMessageBean)
            throws AddressException, PortalException, UnsupportedEncodingException, MailEngineException,
            SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();

        final MessagingPermissions messagingPermissions = new MessagingPermissions(request);

        if (messagingPermissions.getCanSendMessage()) {
            sendMessageBean.send(user, LinkUtils.getBaseUri(request));
        }
    }
}
