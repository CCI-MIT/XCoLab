package com.ext.portlet.model.impl;

import com.ext.portlet.model.ModelInputGroup;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ModelInputGroup in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputGroup
 * @generated
 */
public class ModelInputGroupCacheModel implements CacheModel<ModelInputGroup>,
    Serializable {
    public long modelInputGroupPK;
    public long modelId;
    public long nameAndDescriptionMetaDataId;
    public String name;
    public String description;
    public int displayItemOrder;
    public String groupType;
    public long parentGroupPK;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{modelInputGroupPK=");
        sb.append(modelInputGroupPK);
        sb.append(", modelId=");
        sb.append(modelId);
        sb.append(", nameAndDescriptionMetaDataId=");
        sb.append(nameAndDescriptionMetaDataId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(", displayItemOrder=");
        sb.append(displayItemOrder);
        sb.append(", groupType=");
        sb.append(groupType);
        sb.append(", parentGroupPK=");
        sb.append(parentGroupPK);
        sb.append("}");

        return sb.toString();
    }

    public ModelInputGroup toEntityModel() {
        ModelInputGroupImpl modelInputGroupImpl = new ModelInputGroupImpl();

        modelInputGroupImpl.setModelInputGroupPK(modelInputGroupPK);
        modelInputGroupImpl.setModelId(modelId);
        modelInputGroupImpl.setNameAndDescriptionMetaDataId(nameAndDescriptionMetaDataId);

        if (name == null) {
            modelInputGroupImpl.setName(StringPool.BLANK);
        } else {
            modelInputGroupImpl.setName(name);
        }

        if (description == null) {
            modelInputGroupImpl.setDescription(StringPool.BLANK);
        } else {
            modelInputGroupImpl.setDescription(description);
        }

        modelInputGroupImpl.setDisplayItemOrder(displayItemOrder);

        if (groupType == null) {
            modelInputGroupImpl.setGroupType(StringPool.BLANK);
        } else {
            modelInputGroupImpl.setGroupType(groupType);
        }

        modelInputGroupImpl.setParentGroupPK(parentGroupPK);

        modelInputGroupImpl.resetOriginalValues();

        return modelInputGroupImpl;
    }
}
