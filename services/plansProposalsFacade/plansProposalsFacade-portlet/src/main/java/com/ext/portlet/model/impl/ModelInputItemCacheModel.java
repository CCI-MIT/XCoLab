package com.ext.portlet.model.impl;

import com.ext.portlet.model.ModelInputItem;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ModelInputItem in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputItem
 * @generated
 */
public class ModelInputItemCacheModel implements CacheModel<ModelInputItem>,
    Externalizable {
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

    @Override
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

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        modelInputItemPK = objectInput.readLong();
        modelId = objectInput.readLong();
        modelInputItemID = objectInput.readLong();
        modelGroupId = objectInput.readLong();
        displayItemOrder = objectInput.readInt();
        type = objectInput.readUTF();
        properties = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(modelInputItemPK);
        objectOutput.writeLong(modelId);
        objectOutput.writeLong(modelInputItemID);
        objectOutput.writeLong(modelGroupId);
        objectOutput.writeInt(displayItemOrder);

        if (type == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(type);
        }

        if (properties == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(properties);
        }
    }
}
