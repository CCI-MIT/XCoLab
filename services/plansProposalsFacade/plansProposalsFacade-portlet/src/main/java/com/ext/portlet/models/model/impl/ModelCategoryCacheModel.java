package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelCategory;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ModelCategory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelCategory
 * @generated
 */
public class ModelCategoryCacheModel implements CacheModel<ModelCategory>,
    Serializable {
    public long modelCategoryPK;
    public String modelCategoryName;
    public String modelCategoryDescription;
    public int modelCategoryDisplayWeight;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{modelCategoryPK=");
        sb.append(modelCategoryPK);
        sb.append(", modelCategoryName=");
        sb.append(modelCategoryName);
        sb.append(", modelCategoryDescription=");
        sb.append(modelCategoryDescription);
        sb.append(", modelCategoryDisplayWeight=");
        sb.append(modelCategoryDisplayWeight);
        sb.append("}");

        return sb.toString();
    }

    public ModelCategory toEntityModel() {
        ModelCategoryImpl modelCategoryImpl = new ModelCategoryImpl();

        modelCategoryImpl.setModelCategoryPK(modelCategoryPK);

        if (modelCategoryName == null) {
            modelCategoryImpl.setModelCategoryName(StringPool.BLANK);
        } else {
            modelCategoryImpl.setModelCategoryName(modelCategoryName);
        }

        if (modelCategoryDescription == null) {
            modelCategoryImpl.setModelCategoryDescription(StringPool.BLANK);
        } else {
            modelCategoryImpl.setModelCategoryDescription(modelCategoryDescription);
        }

        modelCategoryImpl.setModelCategoryDisplayWeight(modelCategoryDisplayWeight);

        modelCategoryImpl.resetOriginalValues();

        return modelCategoryImpl;
    }
}
