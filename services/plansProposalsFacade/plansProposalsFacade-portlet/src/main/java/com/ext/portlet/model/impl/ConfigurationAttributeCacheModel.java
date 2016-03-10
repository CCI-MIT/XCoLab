package com.ext.portlet.model.impl;

import com.ext.portlet.model.ConfigurationAttribute;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ConfigurationAttribute in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ConfigurationAttribute
 * @generated
 */
public class ConfigurationAttributeCacheModel implements CacheModel<ConfigurationAttribute>,
    Externalizable {
    public String name;
    public long additionalId;
    public long numericValue;
    public String stringValue;
    public double realValue;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{name=");
        sb.append(name);
        sb.append(", additionalId=");
        sb.append(additionalId);
        sb.append(", numericValue=");
        sb.append(numericValue);
        sb.append(", stringValue=");
        sb.append(stringValue);
        sb.append(", realValue=");
        sb.append(realValue);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ConfigurationAttribute toEntityModel() {
        ConfigurationAttributeImpl configurationAttributeImpl = new ConfigurationAttributeImpl();

        if (name == null) {
            configurationAttributeImpl.setName(StringPool.BLANK);
        } else {
            configurationAttributeImpl.setName(name);
        }

        configurationAttributeImpl.setAdditionalId(additionalId);
        configurationAttributeImpl.setNumericValue(numericValue);

        if (stringValue == null) {
            configurationAttributeImpl.setStringValue(StringPool.BLANK);
        } else {
            configurationAttributeImpl.setStringValue(stringValue);
        }

        configurationAttributeImpl.setRealValue(realValue);

        configurationAttributeImpl.resetOriginalValues();

        return configurationAttributeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        name = objectInput.readUTF();
        additionalId = objectInput.readLong();
        numericValue = objectInput.readLong();
        stringValue = objectInput.readUTF();
        realValue = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        objectOutput.writeLong(additionalId);
        objectOutput.writeLong(numericValue);

        if (stringValue == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(stringValue);
        }

        objectOutput.writeDouble(realValue);
    }
}
