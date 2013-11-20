package com.ext.portlet.model.impl;

import com.ext.portlet.model.DiscussionCategory;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DiscussionCategory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategory
 * @generated
 */
public class DiscussionCategoryCacheModel implements CacheModel<DiscussionCategory>,
    Externalizable {
    public long pk;
    public long categoryId;
    public long categoryGroupId;
    public long authorId;
    public String name;
    public String description;
    public long createDate;
    public long deleted;
    public int threadsCount;
    public long lastActivityDate;
    public long lastActivityAuthorId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{pk=");
        sb.append(pk);
        sb.append(", categoryId=");
        sb.append(categoryId);
        sb.append(", categoryGroupId=");
        sb.append(categoryGroupId);
        sb.append(", authorId=");
        sb.append(authorId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", deleted=");
        sb.append(deleted);
        sb.append(", threadsCount=");
        sb.append(threadsCount);
        sb.append(", lastActivityDate=");
        sb.append(lastActivityDate);
        sb.append(", lastActivityAuthorId=");
        sb.append(lastActivityAuthorId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public DiscussionCategory toEntityModel() {
        DiscussionCategoryImpl discussionCategoryImpl = new DiscussionCategoryImpl();

        discussionCategoryImpl.setPk(pk);
        discussionCategoryImpl.setCategoryId(categoryId);
        discussionCategoryImpl.setCategoryGroupId(categoryGroupId);
        discussionCategoryImpl.setAuthorId(authorId);

        if (name == null) {
            discussionCategoryImpl.setName(StringPool.BLANK);
        } else {
            discussionCategoryImpl.setName(name);
        }

        if (description == null) {
            discussionCategoryImpl.setDescription(StringPool.BLANK);
        } else {
            discussionCategoryImpl.setDescription(description);
        }

        if (createDate == Long.MIN_VALUE) {
            discussionCategoryImpl.setCreateDate(null);
        } else {
            discussionCategoryImpl.setCreateDate(new Date(createDate));
        }

        if (deleted == Long.MIN_VALUE) {
            discussionCategoryImpl.setDeleted(null);
        } else {
            discussionCategoryImpl.setDeleted(new Date(deleted));
        }

        discussionCategoryImpl.setThreadsCount(threadsCount);

        if (lastActivityDate == Long.MIN_VALUE) {
            discussionCategoryImpl.setLastActivityDate(null);
        } else {
            discussionCategoryImpl.setLastActivityDate(new Date(
                    lastActivityDate));
        }

        discussionCategoryImpl.setLastActivityAuthorId(lastActivityAuthorId);

        discussionCategoryImpl.resetOriginalValues();

        return discussionCategoryImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        pk = objectInput.readLong();
        categoryId = objectInput.readLong();
        categoryGroupId = objectInput.readLong();
        authorId = objectInput.readLong();
        name = objectInput.readUTF();
        description = objectInput.readUTF();
        createDate = objectInput.readLong();
        deleted = objectInput.readLong();
        threadsCount = objectInput.readInt();
        lastActivityDate = objectInput.readLong();
        lastActivityAuthorId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(pk);
        objectOutput.writeLong(categoryId);
        objectOutput.writeLong(categoryGroupId);
        objectOutput.writeLong(authorId);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }

        objectOutput.writeLong(createDate);
        objectOutput.writeLong(deleted);
        objectOutput.writeInt(threadsCount);
        objectOutput.writeLong(lastActivityDate);
        objectOutput.writeLong(lastActivityAuthorId);
    }
}
