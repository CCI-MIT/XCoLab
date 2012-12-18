package com.ext.portlet.discussions.model.impl;

import com.ext.portlet.discussions.model.DiscussionCategoryGroup;

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

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(id);
        sb.append(", description=");
        sb.append(description);
        sb.append(", url=");
        sb.append(url);
        sb.append(", commentsThread=");
        sb.append(commentsThread);
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

        discussionCategoryGroupImpl.resetOriginalValues();

        return discussionCategoryGroupImpl;
    }
}
