package org.xcolab.jsp.tags.discussion.actions;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.jsp.tags.discussion.DiscussionPermissions;
import org.xcolab.jsp.tags.discussion.exceptions.DiscussionsException;

import com.ext.portlet.model.DiscussionMessage;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 05/11/13
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("view")
public class EditDiscussionMessageActionController extends BaseDiscussionsActionController  {

    @RequestMapping(params = "action=editComment")
    public void handleAction(ActionRequest request, ActionResponse response, @RequestParam("messageId") long messageId, @RequestParam("comment") String comment)
            throws IOException, PortalException, SystemException, DiscussionsException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        if (!themeDisplay.getPermissionChecker().isOmniadmin())
            throw new DiscussionsException("User isn't allowed to edit comment");

        DiscussionMessage m = DiscussionMessageLocalServiceUtil.getMessageByMessageId(messageId);
        m.setBody(cleanHtml(comment, Whitelist.basicWithImages()));
        DiscussionMessageLocalServiceUtil.updateDiscussionMessage(m);

        redirectToReferer(request, response);
    }

    private String cleanHtml(String text, Whitelist whitelist) {
        Document doc = Jsoup.parse(text);
        doc = new Cleaner(whitelist).clean(doc);
        // Adjust escape mode, http://stackoverflow.com/questions/8683018/jsoup-clean-without-adding-html-entities
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        return doc.body().html();
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions) {
        return permissions.getCanAdminMessages();
    }

}
