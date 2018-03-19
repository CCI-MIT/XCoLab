package org.xcolab.view.pages.discussion.discussions.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.view.pages.discussion.discussions.DiscussionPreferences;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;

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

    @ModelAttribute("communityTopContentArticleId")
    public Long getCommunityTopContentArticleId(){
        return ConfigurationAttributeKey.DISCUSSION_CONTENT_ARTICLE_ID.get();
    }

    public abstract boolean getCanView(DiscussionPermissions permissions,
            CategoryGroup categoryGroup, long additionalId);

    public abstract boolean getCanEdit(DiscussionPermissions permissions,
            CategoryGroup categoryGroup, long additionalId);
}
