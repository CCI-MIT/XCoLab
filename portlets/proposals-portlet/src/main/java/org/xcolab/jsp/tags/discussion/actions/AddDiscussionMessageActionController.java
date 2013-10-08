package org.xcolab.jsp.tags.discussion.actions;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.jsp.tags.discussion.wrappers.NewMessageWrapper;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("view")
public class AddDiscussionMessageActionController {

    @RequestMapping(params = "action=addDiscussionMessage")
    public void handleAction(ActionRequest request, ActionResponse response, NewMessageWrapper newMessage) throws IOException, PortalException, SystemException {
        
        
        HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        DiscussionCategoryGroup dcg = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(newMessage.getDiscussionId());
        DiscussionCategoryGroupLocalServiceUtil.addComment(dcg, newMessage.getTitle(), newMessage.getDescription(), themeDisplay.getUser());

        request.setAttribute("ACTION_REDIRECTING", true);
        
        String referer = httpRequest.getHeader("referer");
        response.sendRedirect(referer);
        
        
        System.out.println("referer " + referer);
        System.out.println(request.getParameterMap());
        
        
    }
    
}
