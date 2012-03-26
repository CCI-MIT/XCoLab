package com.xcolab.services.sample.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.xcolab.services.sample.model.SampleEntity;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing SampleEntity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SampleEntity
 * @generated
 */
public class SampleEntityCacheModel implements CacheModel<SampleEntity>,
    Serializable {
    public long id;
    public String content;
    public long created;
    public long authorId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(id);
        sb.append(", content=");
        sb.append(content);
        sb.append(", created=");
        sb.append(created);
        sb.append(", authorId=");
        sb.append(authorId);
        sb.append("}");

        return sb.toString();
    }

    public SampleEntity toEntityModel() {
        SampleEntityImpl sampleEntityImpl = new SampleEntityImpl();

        sampleEntityImpl.setId(id);

        if (content == null) {
            sampleEntityImpl.setContent(StringPool.BLANK);
        } else {
            sampleEntityImpl.setContent(content);
        }

        if (created == Long.MIN_VALUE) {
            sampleEntityImpl.setCreated(null);
        } else {
            sampleEntityImpl.setCreated(new Date(created));
        }

        sampleEntityImpl.setAuthorId(authorId);

        sampleEntityImpl.resetOriginalValues();

        return sampleEntityImpl;
    }
}
