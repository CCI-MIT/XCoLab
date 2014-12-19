package com.ext.portlet.model.impl;

import com.ext.portlet.model.ModelOutputItem;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ModelOutputItem in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputItem
 * @generated
 */
public class ModelOutputItemCacheModel implements CacheModel<ModelOutputItem>,
    Externalizable {
    public long modelOutputItemModifierPK;
    public long modelId;
    public long modelOutputItemId;
    public int modelOutputItemOrder;
    public String modelItemRangePolicy;
    public String modelItemRangeMessage;
    public String modelItemErrorPolicy;
    public String modelItemErrorMessage;
    public String modelItemLabelFormat;
    public boolean modelItemIsVisible;
    public String itemType;
    public long relatedOutputItem;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{modelOutputItemModifierPK=");
        sb.append(modelOutputItemModifierPK);
        sb.append(", modelId=");
        sb.append(modelId);
        sb.append(", modelOutputItemId=");
        sb.append(modelOutputItemId);
        sb.append(", modelOutputItemOrder=");
        sb.append(modelOutputItemOrder);
        sb.append(", modelItemRangePolicy=");
        sb.append(modelItemRangePolicy);
        sb.append(", modelItemRangeMessage=");
        sb.append(modelItemRangeMessage);
        sb.append(", modelItemErrorPolicy=");
        sb.append(modelItemErrorPolicy);
        sb.append(", modelItemErrorMessage=");
        sb.append(modelItemErrorMessage);
        sb.append(", modelItemLabelFormat=");
        sb.append(modelItemLabelFormat);
        sb.append(", modelItemIsVisible=");
        sb.append(modelItemIsVisible);
        sb.append(", itemType=");
        sb.append(itemType);
        sb.append(", relatedOutputItem=");
        sb.append(relatedOutputItem);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ModelOutputItem toEntityModel() {
        ModelOutputItemImpl modelOutputItemImpl = new ModelOutputItemImpl();

        modelOutputItemImpl.setModelOutputItemModifierPK(modelOutputItemModifierPK);
        modelOutputItemImpl.setModelId(modelId);
        modelOutputItemImpl.setModelOutputItemId(modelOutputItemId);
        modelOutputItemImpl.setModelOutputItemOrder(modelOutputItemOrder);

        if (modelItemRangePolicy == null) {
            modelOutputItemImpl.setModelItemRangePolicy(StringPool.BLANK);
        } else {
            modelOutputItemImpl.setModelItemRangePolicy(modelItemRangePolicy);
        }

        if (modelItemRangeMessage == null) {
            modelOutputItemImpl.setModelItemRangeMessage(StringPool.BLANK);
        } else {
            modelOutputItemImpl.setModelItemRangeMessage(modelItemRangeMessage);
        }

        if (modelItemErrorPolicy == null) {
            modelOutputItemImpl.setModelItemErrorPolicy(StringPool.BLANK);
        } else {
            modelOutputItemImpl.setModelItemErrorPolicy(modelItemErrorPolicy);
        }

        if (modelItemErrorMessage == null) {
            modelOutputItemImpl.setModelItemErrorMessage(StringPool.BLANK);
        } else {
            modelOutputItemImpl.setModelItemErrorMessage(modelItemErrorMessage);
        }

        if (modelItemLabelFormat == null) {
            modelOutputItemImpl.setModelItemLabelFormat(StringPool.BLANK);
        } else {
            modelOutputItemImpl.setModelItemLabelFormat(modelItemLabelFormat);
        }

        modelOutputItemImpl.setModelItemIsVisible(modelItemIsVisible);

        if (itemType == null) {
            modelOutputItemImpl.setItemType(StringPool.BLANK);
        } else {
            modelOutputItemImpl.setItemType(itemType);
        }

        modelOutputItemImpl.setRelatedOutputItem(relatedOutputItem);

        modelOutputItemImpl.resetOriginalValues();

        return modelOutputItemImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        modelOutputItemModifierPK = objectInput.readLong();
        modelId = objectInput.readLong();
        modelOutputItemId = objectInput.readLong();
        modelOutputItemOrder = objectInput.readInt();
        modelItemRangePolicy = objectInput.readUTF();
        modelItemRangeMessage = objectInput.readUTF();
        modelItemErrorPolicy = objectInput.readUTF();
        modelItemErrorMessage = objectInput.readUTF();
        modelItemLabelFormat = objectInput.readUTF();
        modelItemIsVisible = objectInput.readBoolean();
        itemType = objectInput.readUTF();
        relatedOutputItem = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(modelOutputItemModifierPK);
        objectOutput.writeLong(modelId);
        objectOutput.writeLong(modelOutputItemId);
        objectOutput.writeInt(modelOutputItemOrder);

        if (modelItemRangePolicy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modelItemRangePolicy);
        }

        if (modelItemRangeMessage == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modelItemRangeMessage);
        }

        if (modelItemErrorPolicy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modelItemErrorPolicy);
        }

        if (modelItemErrorMessage == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modelItemErrorMessage);
        }

        if (modelItemLabelFormat == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modelItemLabelFormat);
        }

        objectOutput.writeBoolean(modelItemIsVisible);

        if (itemType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemType);
        }

        objectOutput.writeLong(relatedOutputItem);
    }
}
