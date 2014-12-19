package com.ext.portlet.model.impl;

import com.ext.portlet.model.ModelDiscussion;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ModelDiscussion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelDiscussion
 * @generated
 */
public class ModelDiscussionCacheModel implements CacheModel<ModelDiscussion>,
    Externalizable {
    public long modelDiscussionId;
    public long modelId;
    public long categoryId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{modelDiscussionId=");
        sb.append(modelDiscussionId);
        sb.append(", modelId=");
        sb.append(modelId);
        sb.append(", categoryId=");
        sb.append(categoryId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ModelDiscussion toEntityModel() {
        ModelDiscussionImpl modelDiscussionImpl = new ModelDiscussionImpl();

        modelDiscussionImpl.setModelDiscussionId(modelDiscussionId);
        modelDiscussionImpl.setModelId(modelId);
        modelDiscussionImpl.setCategoryId(categoryId);

        modelDiscussionImpl.resetOriginalValues();

        return modelDiscussionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        modelDiscussionId = objectInput.readLong();
        modelId = objectInput.readLong();
        categoryId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(modelDiscussionId);
        objectOutput.writeLong(modelId);
        objectOutput.writeLong(categoryId);
    }
}
