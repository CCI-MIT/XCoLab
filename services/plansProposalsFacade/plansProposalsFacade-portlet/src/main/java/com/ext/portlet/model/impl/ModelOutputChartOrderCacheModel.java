package com.ext.portlet.model.impl;

import com.ext.portlet.model.ModelOutputChartOrder;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ModelOutputChartOrder in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputChartOrder
 * @generated
 */
public class ModelOutputChartOrderCacheModel implements CacheModel<ModelOutputChartOrder>,
    Externalizable {
    public long modelOutputChartOrderPK;
    public long modelId;
    public String modelOutputLabel;
    public int modelOutputChartOrder;
    public String modelIndexRangePolicy;
    public String modelIndexRangeMessage;
    public String modelIndexErrorPolicy;
    public String modelIndexErrorMessage;
    public boolean modelChartIsVisible;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{modelOutputChartOrderPK=");
        sb.append(modelOutputChartOrderPK);
        sb.append(", modelId=");
        sb.append(modelId);
        sb.append(", modelOutputLabel=");
        sb.append(modelOutputLabel);
        sb.append(", modelOutputChartOrder=");
        sb.append(modelOutputChartOrder);
        sb.append(", modelIndexRangePolicy=");
        sb.append(modelIndexRangePolicy);
        sb.append(", modelIndexRangeMessage=");
        sb.append(modelIndexRangeMessage);
        sb.append(", modelIndexErrorPolicy=");
        sb.append(modelIndexErrorPolicy);
        sb.append(", modelIndexErrorMessage=");
        sb.append(modelIndexErrorMessage);
        sb.append(", modelChartIsVisible=");
        sb.append(modelChartIsVisible);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ModelOutputChartOrder toEntityModel() {
        ModelOutputChartOrderImpl modelOutputChartOrderImpl = new ModelOutputChartOrderImpl();

        modelOutputChartOrderImpl.setModelOutputChartOrderPK(modelOutputChartOrderPK);
        modelOutputChartOrderImpl.setModelId(modelId);

        if (modelOutputLabel == null) {
            modelOutputChartOrderImpl.setModelOutputLabel(StringPool.BLANK);
        } else {
            modelOutputChartOrderImpl.setModelOutputLabel(modelOutputLabel);
        }

        modelOutputChartOrderImpl.setModelOutputChartOrder(modelOutputChartOrder);

        if (modelIndexRangePolicy == null) {
            modelOutputChartOrderImpl.setModelIndexRangePolicy(StringPool.BLANK);
        } else {
            modelOutputChartOrderImpl.setModelIndexRangePolicy(modelIndexRangePolicy);
        }

        if (modelIndexRangeMessage == null) {
            modelOutputChartOrderImpl.setModelIndexRangeMessage(StringPool.BLANK);
        } else {
            modelOutputChartOrderImpl.setModelIndexRangeMessage(modelIndexRangeMessage);
        }

        if (modelIndexErrorPolicy == null) {
            modelOutputChartOrderImpl.setModelIndexErrorPolicy(StringPool.BLANK);
        } else {
            modelOutputChartOrderImpl.setModelIndexErrorPolicy(modelIndexErrorPolicy);
        }

        if (modelIndexErrorMessage == null) {
            modelOutputChartOrderImpl.setModelIndexErrorMessage(StringPool.BLANK);
        } else {
            modelOutputChartOrderImpl.setModelIndexErrorMessage(modelIndexErrorMessage);
        }

        modelOutputChartOrderImpl.setModelChartIsVisible(modelChartIsVisible);

        modelOutputChartOrderImpl.resetOriginalValues();

        return modelOutputChartOrderImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        modelOutputChartOrderPK = objectInput.readLong();
        modelId = objectInput.readLong();
        modelOutputLabel = objectInput.readUTF();
        modelOutputChartOrder = objectInput.readInt();
        modelIndexRangePolicy = objectInput.readUTF();
        modelIndexRangeMessage = objectInput.readUTF();
        modelIndexErrorPolicy = objectInput.readUTF();
        modelIndexErrorMessage = objectInput.readUTF();
        modelChartIsVisible = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(modelOutputChartOrderPK);
        objectOutput.writeLong(modelId);

        if (modelOutputLabel == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modelOutputLabel);
        }

        objectOutput.writeInt(modelOutputChartOrder);

        if (modelIndexRangePolicy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modelIndexRangePolicy);
        }

        if (modelIndexRangeMessage == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modelIndexRangeMessage);
        }

        if (modelIndexErrorPolicy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modelIndexErrorPolicy);
        }

        if (modelIndexErrorMessage == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modelIndexErrorMessage);
        }

        objectOutput.writeBoolean(modelChartIsVisible);
    }
}
