package org.xcolab.view.pages.discussion.discussions.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.util.exceptions.ReferenceResolutionException;
import org.xcolab.view.pages.discussion.discussions.DiscussionPreferences;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions
        .DiscussionAuthorizationException;

import javax.servlet.http.HttpServletRequest;

@Controller
public abstract class BaseDiscussionController {

    protected CategoryGroup getCategoryGroup(HttpServletRequest request) {
        DiscussionPreferences preferences = new DiscussionPreferences();

        try {
            return CategoryClientUtil.getCategoryGroup(preferences.getCategoryGroupId());
        } catch (CategoryGroupNotFoundException e) {
            throw ReferenceResolutionException
                    .toObject(CategoryGroup.class, preferences.getCategoryGroupId()).build();
        }
    }

    protected void checkCanView(HttpServletRequest request, String accessDeniedMessage,
            CategoryGroup categoryGroup, long additionalId)
            throws DiscussionAuthorizationException {
        checkPermissions(request, accessDeniedMessage, categoryGroup, additionalId, false);
    }

    protected void checkCanEdit(HttpServletRequest request, String accessDeniedMessage,
            CategoryGroup categoryGroup, long additionalId)
            throws DiscussionAuthorizationException {
        checkPermissions(request, accessDeniedMessage, categoryGroup, additionalId, true);
    }

    private void checkPermissions(HttpServletRequest request, String accessDeniedMessage,
            CategoryGroup categoryGroup,
            long additionalId, boolean checkEditPermissions)
            throws DiscussionAuthorizationException {
        DiscussionPermissions permissions = new DiscussionPermissions(request);

        if (additionalId > 0 && !getCanView(permissions, categoryGroup, additionalId)) {
            throw new DiscussionAuthorizationException(accessDeniedMessage);
        }
        if (checkEditPermissions && !getCanEdit(permissions, categoryGroup, additionalId)) {
            throw new DiscussionAuthorizationException(accessDeniedMessage);
        }
    }

    @ModelAttribute("communityTopContentArticleId")
    public Long getCommunityTopContentArticleId(){
        return ConfigurationAttributeKey.DISCUSSION_CONTENT_ARTICLE_ID.get();
    }

    public abstract boolean getCanView(DiscussionPermissions permissions,
            CategoryGroup categoryGroup, long additionalId);

    public abstract boolean getCanEdit(DiscussionPermissions permissions,
            CategoryGroup categoryGroup, long additionalId);
}
