package com.ext.portlet.model.impl;

import com.ext.portlet.model.EmailList;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing EmailList in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see EmailList
 * @generated
 */
public class EmailListCacheModel implements CacheModel<EmailList>, Serializable {
    public long id;
    public String name;
    public String email;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{id=");
        sb.append(id);
        sb.append(", name=");
        sb.append(name);
        sb.append(", email=");
        sb.append(email);
        sb.append("}");

        return sb.toString();
    }

    public EmailList toEntityModel() {
        EmailListImpl emailListImpl = new EmailListImpl();

        emailListImpl.setId(id);

        if (name == null) {
            emailListImpl.setName(StringPool.BLANK);
        } else {
            emailListImpl.setName(name);
        }

        if (email == null) {
            emailListImpl.setEmail(StringPool.BLANK);
        } else {
            emailListImpl.setEmail(email);
        }

        emailListImpl.resetOriginalValues();

        return emailListImpl;
    }
}
