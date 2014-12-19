package com.ext.portlet.model.impl;

import com.ext.portlet.model.MessagingMessageConversionType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MessagingMessageConversionType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionType
 * @generated
 */
public class MessagingMessageConversionTypeCacheModel implements CacheModel<MessagingMessageConversionType>,
    Externalizable {
    public long typeId;
    public String name;
    public String description;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{typeId=");
        sb.append(typeId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MessagingMessageConversionType toEntityModel() {
        MessagingMessageConversionTypeImpl messagingMessageConversionTypeImpl = new MessagingMessageConversionTypeImpl();

        messagingMessageConversionTypeImpl.setTypeId(typeId);

        if (name == null) {
            messagingMessageConversionTypeImpl.setName(StringPool.BLANK);
        } else {
            messagingMessageConversionTypeImpl.setName(name);
        }

        if (description == null) {
            messagingMessageConversionTypeImpl.setDescription(StringPool.BLANK);
        } else {
            messagingMessageConversionTypeImpl.setDescription(description);
        }

        messagingMessageConversionTypeImpl.resetOriginalValues();

        return messagingMessageConversionTypeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        typeId = objectInput.readLong();
        name = objectInput.readUTF();
        description = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(typeId);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }
    }
}
