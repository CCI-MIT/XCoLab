package org.xcolab.view.pages.discussion.discussions.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.pojo.ICategoryGroup;
import org.xcolab.client.comment.pojo.tables.pojos.CategoryGroup;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.view.pages.discussion.discussions.DiscussionPreferences;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;

import javax.servlet.http.HttpServletRequest;

@Controller
public abstract class BaseDiscussionController {

    @Autowired
    private ICategoryClient categoryClient;

    protected ICategoryGroup getCategoryGroup(HttpServletRequest request) {
        DiscussionPreferences preferences = new DiscussionPreferences();

        try {
            return categoryClient.getCategoryGroup(preferences.getCategoryGroupId());
        } catch (CategoryGroupNotFoundException e) {
            throw ReferenceResolutionException
                    .toObject(CategoryGroup.class, preferences.getCategoryGroupId()).build();
        }
    }

    @ModelAttribute("communityTopContentArticleId")
    public Long getCommunityTopContentArticleId() {
        return ConfigurationAttributeKey.DISCUSSION_CONTENT_ARTICLE_ID.get();
    }

    public abstract boolean getCanView(DiscussionPermissions permissions,
            ICategoryGroup categoryGroup, long additionalId);

    public abstract boolean getCanEdit(DiscussionPermissions permissions,
            ICategoryGroup categoryGroup, long additionalId);
}
