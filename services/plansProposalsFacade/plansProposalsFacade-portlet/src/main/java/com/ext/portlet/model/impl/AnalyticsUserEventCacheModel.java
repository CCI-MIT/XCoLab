package com.ext.portlet.model.impl;

import com.ext.portlet.model.AnalyticsUserEvent;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AnalyticsUserEvent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsUserEvent
 * @generated
 */
public class AnalyticsUserEventCacheModel implements CacheModel<AnalyticsUserEvent>,
    Serializable {
    public long userId;
    public String idString;
    public String category;
    public String action;
    public String label;
    public int value;
    public long created;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{userId=");
        sb.append(userId);
        sb.append(", idString=");
        sb.append(idString);
        sb.append(", category=");
        sb.append(category);
        sb.append(", action=");
        sb.append(action);
        sb.append(", label=");
        sb.append(label);
        sb.append(", value=");
        sb.append(value);
        sb.append(", created=");
        sb.append(created);
        sb.append("}");

        return sb.toString();
    }

    public AnalyticsUserEvent toEntityModel() {
        AnalyticsUserEventImpl analyticsUserEventImpl = new AnalyticsUserEventImpl();

        analyticsUserEventImpl.setUserId(userId);

        if (idString == null) {
            analyticsUserEventImpl.setIdString(StringPool.BLANK);
        } else {
            analyticsUserEventImpl.setIdString(idString);
        }

        if (category == null) {
            analyticsUserEventImpl.setCategory(StringPool.BLANK);
        } else {
            analyticsUserEventImpl.setCategory(category);
        }

        if (action == null) {
            analyticsUserEventImpl.setAction(StringPool.BLANK);
        } else {
            analyticsUserEventImpl.setAction(action);
        }

        if (label == null) {
            analyticsUserEventImpl.setLabel(StringPool.BLANK);
        } else {
            analyticsUserEventImpl.setLabel(label);
        }

        analyticsUserEventImpl.setValue(value);

        if (created == Long.MIN_VALUE) {
            analyticsUserEventImpl.setCreated(null);
        } else {
            analyticsUserEventImpl.setCreated(new Date(created));
        }

        analyticsUserEventImpl.resetOriginalValues();

        return analyticsUserEventImpl;
    }
}
