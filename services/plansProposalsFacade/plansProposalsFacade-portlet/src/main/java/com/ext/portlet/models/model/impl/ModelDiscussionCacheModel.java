package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelDiscussion;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ModelDiscussion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelDiscussion
 * @generated
 */
public class ModelDiscussionCacheModel implements CacheModel<ModelDiscussion>,
    Serializable {
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

    public ModelDiscussion toEntityModel() {
        ModelDiscussionImpl modelDiscussionImpl = new ModelDiscussionImpl();

        modelDiscussionImpl.setModelDiscussionId(modelDiscussionId);
        modelDiscussionImpl.setModelId(modelId);
        modelDiscussionImpl.setCategoryId(categoryId);

        modelDiscussionImpl.resetOriginalValues();

        return modelDiscussionImpl;
    }
}
