package com.ext.portlet.model.impl;

import com.ext.portlet.model.DiscussionCategoryGroup;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing DiscussionCategoryGroup in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryGroup
 * @generated
 */
public class DiscussionCategoryGroupCacheModel implements CacheModel<DiscussionCategoryGroup>,
    Serializable {
    public long id;
    public String description;
    public String url;
    public long commentsThread;
    public boolean isQuiet;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(id);
        sb.append(", description=");
        sb.append(description);
        sb.append(", url=");
        sb.append(url);
        sb.append(", commentsThread=");
        sb.append(commentsThread);
        sb.append(", isQuiet=");
        sb.append(isQuiet);
        sb.append("}");

        return sb.toString();
    }

    public DiscussionCategoryGroup toEntityModel() {
        DiscussionCategoryGroupImpl discussionCategoryGroupImpl = new DiscussionCategoryGroupImpl();

        discussionCategoryGroupImpl.setId(id);

        if (description == null) {
            discussionCategoryGroupImpl.setDescription(StringPool.BLANK);
        } else {
            discussionCategoryGroupImpl.setDescription(description);
        }

        if (url == null) {
            discussionCategoryGroupImpl.setUrl(StringPool.BLANK);
        } else {
            discussionCategoryGroupImpl.setUrl(url);
        }

        discussionCategoryGroupImpl.setCommentsThread(commentsThread);
        discussionCategoryGroupImpl.setIsQuiet(isQuiet);

        discussionCategoryGroupImpl.resetOriginalValues();

        return discussionCategoryGroupImpl;
    }
}
