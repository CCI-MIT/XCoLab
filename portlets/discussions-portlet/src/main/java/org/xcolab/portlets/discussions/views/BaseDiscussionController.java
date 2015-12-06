package org.xcolab.portlets.discussions.views;

import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.jspTags.discussion.wrappers.DiscussionCategoryGroupWrapper;
import org.xcolab.portlets.discussions.DiscussionPreferences;

import javax.portlet.PortletRequest;

/**
 * Created by johannes on 12/5/15.
 */
@Controller
@RequestMapping("view")
public class BaseDiscussionController {

    protected DiscussionCategoryGroupWrapper getDiscussionCategoryGroupWrapper(PortletRequest request) throws SystemException {
        DiscussionPreferences preferences = new DiscussionPreferences(request);

        return new DiscussionCategoryGroupWrapper(
                DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(preferences.getCategoryGroupId()));
    }
}
