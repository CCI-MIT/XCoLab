package com.ext.portlet.model.impl;

import com.ext.portlet.model.MessagingRedirectLink;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MessagingRedirectLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingRedirectLink
 * @generated
 */
public class MessagingRedirectLinkCacheModel implements CacheModel<MessagingRedirectLink>,
    Externalizable {
    public long redirectId;
    public String link;
    public long messageId;
    public long createDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{redirectId=");
        sb.append(redirectId);
        sb.append(", link=");
        sb.append(link);
        sb.append(", messageId=");
        sb.append(messageId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MessagingRedirectLink toEntityModel() {
        MessagingRedirectLinkImpl messagingRedirectLinkImpl = new MessagingRedirectLinkImpl();

        messagingRedirectLinkImpl.setRedirectId(redirectId);

        if (link == null) {
            messagingRedirectLinkImpl.setLink(StringPool.BLANK);
        } else {
            messagingRedirectLinkImpl.setLink(link);
        }

        messagingRedirectLinkImpl.setMessageId(messageId);

        if (createDate == Long.MIN_VALUE) {
            messagingRedirectLinkImpl.setCreateDate(null);
        } else {
            messagingRedirectLinkImpl.setCreateDate(new Date(createDate));
        }

        messagingRedirectLinkImpl.resetOriginalValues();

        return messagingRedirectLinkImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        redirectId = objectInput.readLong();
        link = objectInput.readUTF();
        messageId = objectInput.readLong();
        createDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(redirectId);

        if (link == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(link);
        }

        objectOutput.writeLong(messageId);
        objectOutput.writeLong(createDate);
    }
}
