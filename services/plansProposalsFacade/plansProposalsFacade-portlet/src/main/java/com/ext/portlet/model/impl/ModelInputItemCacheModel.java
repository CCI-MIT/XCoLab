package com.ext.portlet.model.impl;

import com.ext.portlet.model.ModelInputItem;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ModelInputItem in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputItem
 * @generated
 */
public class ModelInputItemCacheModel implements CacheModel<ModelInputItem>,
    Serializable {
    public long modelInputItemPK;
    public long modelId;
    public long modelInputItemID;
    public long modelGroupId;
    public int displayItemOrder;
    public String type;
    public String properties;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{modelInputItemPK=");
        sb.append(modelInputItemPK);
        sb.append(", modelId=");
        sb.append(modelId);
        sb.append(", modelInputItemID=");
        sb.append(modelInputItemID);
        sb.append(", modelGroupId=");
        sb.append(modelGroupId);
        sb.append(", displayItemOrder=");
        sb.append(displayItemOrder);
        sb.append(", type=");
        sb.append(type);
        sb.append(", properties=");
        sb.append(properties);
        sb.append("}");

        return sb.toString();
    }

    public ModelInputItem toEntityModel() {
        ModelInputItemImpl modelInputItemImpl = new ModelInputItemImpl();

        modelInputItemImpl.setModelInputItemPK(modelInputItemPK);
        modelInputItemImpl.setModelId(modelId);
        modelInputItemImpl.setModelInputItemID(modelInputItemID);
        modelInputItemImpl.setModelGroupId(modelGroupId);
        modelInputItemImpl.setDisplayItemOrder(displayItemOrder);

        if (type == null) {
            modelInputItemImpl.setType(StringPool.BLANK);
        } else {
            modelInputItemImpl.setType(type);
        }

        if (properties == null) {
            modelInputItemImpl.setProperties(StringPool.BLANK);
        } else {
            modelInputItemImpl.setProperties(properties);
        }

        modelInputItemImpl.resetOriginalValues();

        return modelInputItemImpl;
    }
}
