package org.xcolab.portlets.discussions;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * Created by johannes on 12/1/15.
 */
@Controller
@RequestMapping("view")
public class DiscussionController {
    @RequestMapping
    public String showContests(PortletRequest request, PortletResponse response, Model model) throws SystemException, PortalException {
        return "discussions";
    }
}
