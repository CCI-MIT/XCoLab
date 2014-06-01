package com.ext.portlet.model.impl;

import com.ext.portlet.model.ModelGlobalPreference;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ModelGlobalPreference in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelGlobalPreference
 * @generated
 */
public class ModelGlobalPreferenceCacheModel implements CacheModel<ModelGlobalPreference>,
    Externalizable {
    public long modelGlobalPreferencePK;
    public long modelId;
    public boolean visible;
    public int weight;
    public long expertEvaluationPageId;
    public long modelCategoryId;
    public boolean usesCustomInputs;
    public String customInputsDefinition;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{modelGlobalPreferencePK=");
        sb.append(modelGlobalPreferencePK);
        sb.append(", modelId=");
        sb.append(modelId);
        sb.append(", visible=");
        sb.append(visible);
        sb.append(", weight=");
        sb.append(weight);
        sb.append(", expertEvaluationPageId=");
        sb.append(expertEvaluationPageId);
        sb.append(", modelCategoryId=");
        sb.append(modelCategoryId);
        sb.append(", usesCustomInputs=");
        sb.append(usesCustomInputs);
        sb.append(", customInputsDefinition=");
        sb.append(customInputsDefinition);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ModelGlobalPreference toEntityModel() {
        ModelGlobalPreferenceImpl modelGlobalPreferenceImpl = new ModelGlobalPreferenceImpl();

        modelGlobalPreferenceImpl.setModelGlobalPreferencePK(modelGlobalPreferencePK);
        modelGlobalPreferenceImpl.setModelId(modelId);
        modelGlobalPreferenceImpl.setVisible(visible);
        modelGlobalPreferenceImpl.setWeight(weight);
        modelGlobalPreferenceImpl.setExpertEvaluationPageId(expertEvaluationPageId);
        modelGlobalPreferenceImpl.setModelCategoryId(modelCategoryId);
        modelGlobalPreferenceImpl.setUsesCustomInputs(usesCustomInputs);

        if (customInputsDefinition == null) {
            modelGlobalPreferenceImpl.setCustomInputsDefinition(StringPool.BLANK);
        } else {
            modelGlobalPreferenceImpl.setCustomInputsDefinition(customInputsDefinition);
        }

        modelGlobalPreferenceImpl.resetOriginalValues();

        return modelGlobalPreferenceImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        modelGlobalPreferencePK = objectInput.readLong();
        modelId = objectInput.readLong();
        visible = objectInput.readBoolean();
        weight = objectInput.readInt();
        expertEvaluationPageId = objectInput.readLong();
        modelCategoryId = objectInput.readLong();
        usesCustomInputs = objectInput.readBoolean();
        customInputsDefinition = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(modelGlobalPreferencePK);
        objectOutput.writeLong(modelId);
        objectOutput.writeBoolean(visible);
        objectOutput.writeInt(weight);
        objectOutput.writeLong(expertEvaluationPageId);
        objectOutput.writeLong(modelCategoryId);
        objectOutput.writeBoolean(usesCustomInputs);

        if (customInputsDefinition == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(customInputsDefinition);
        }
    }
}
