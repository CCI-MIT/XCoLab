package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelOutputChartOrder;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ModelOutputChartOrder in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputChartOrder
 * @generated
 */
public class ModelOutputChartOrderCacheModel implements CacheModel<ModelOutputChartOrder>,
    Serializable {
    public Long modelOutputChartOrderPK;
    public Long modelId;
    public String modelOutputLabel;
    public Integer modelOutputChartOrder;
    public String modelIndexRangePolicy;
    public String modelIndexRangeMessage;
    public String modelIndexErrorPolicy;
    public String modelIndexErrorMessage;
    public Boolean modelChartIsVisible;

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
}
