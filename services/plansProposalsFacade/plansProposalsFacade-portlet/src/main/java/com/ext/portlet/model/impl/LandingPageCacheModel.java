package com.ext.portlet.model.impl;

import com.ext.portlet.model.LandingPage;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LandingPage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LandingPage
 * @generated
 */
public class LandingPageCacheModel implements CacheModel<LandingPage>,
    Externalizable {
    public long id;
    public String baseUrl;
    public String targetUrl;
    public long updated;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(id);
        sb.append(", baseUrl=");
        sb.append(baseUrl);
        sb.append(", targetUrl=");
        sb.append(targetUrl);
        sb.append(", updated=");
        sb.append(updated);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public LandingPage toEntityModel() {
        LandingPageImpl landingPageImpl = new LandingPageImpl();

        landingPageImpl.setId(id);

        if (baseUrl == null) {
            landingPageImpl.setBaseUrl(StringPool.BLANK);
        } else {
            landingPageImpl.setBaseUrl(baseUrl);
        }

        if (targetUrl == null) {
            landingPageImpl.setTargetUrl(StringPool.BLANK);
        } else {
            landingPageImpl.setTargetUrl(targetUrl);
        }

        if (updated == Long.MIN_VALUE) {
            landingPageImpl.setUpdated(null);
        } else {
            landingPageImpl.setUpdated(new Date(updated));
        }

        landingPageImpl.resetOriginalValues();

        return landingPageImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        baseUrl = objectInput.readUTF();
        targetUrl = objectInput.readUTF();
        updated = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);

        if (baseUrl == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(baseUrl);
        }

        if (targetUrl == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(targetUrl);
        }

        objectOutput.writeLong(updated);
    }
}
