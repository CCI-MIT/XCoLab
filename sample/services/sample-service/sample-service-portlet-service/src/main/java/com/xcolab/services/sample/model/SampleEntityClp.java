package com.xcolab.services.sample.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.xcolab.services.sample.service.SampleEntityLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class SampleEntityClp extends BaseModelImpl<SampleEntity>
    implements SampleEntity {
    private long _id;
    private String _content;
    private Date _created;
    private long _authorId;

    public SampleEntityClp() {
    }

    public Class<?> getModelClass() {
        return SampleEntity.class;
    }

    public String getModelClassName() {
        return SampleEntity.class.getName();
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            SampleEntityLocalServiceUtil.addSampleEntity(this);
        } else {
            SampleEntityLocalServiceUtil.updateSampleEntity(this);
        }
    }

    @Override
    public SampleEntity toEscapedModel() {
        return (SampleEntity) Proxy.newProxyInstance(SampleEntity.class.getClassLoader(),
            new Class[] { SampleEntity.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        SampleEntityClp clone = new SampleEntityClp();

        clone.setId(getId());
        clone.setContent(getContent());
        clone.setCreated(getCreated());
        clone.setAuthorId(getAuthorId());

        return clone;
    }

    public int compareTo(SampleEntity sampleEntity) {
        long primaryKey = sampleEntity.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        SampleEntityClp sampleEntity = null;

        try {
            sampleEntity = (SampleEntityClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = sampleEntity.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", content=");
        sb.append(getContent());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.xcolab.services.sample.model.SampleEntity");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>content</column-name><column-value><![CDATA[");
        sb.append(getContent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
