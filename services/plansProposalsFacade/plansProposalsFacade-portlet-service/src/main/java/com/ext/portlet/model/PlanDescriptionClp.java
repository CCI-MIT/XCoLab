package com.ext.portlet.model;

import com.ext.portlet.service.PlanDescriptionLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanDescriptionClp extends BaseModelImpl<PlanDescription>
    implements PlanDescription {
    private long _id;
    private long _planId;
    private String _name;
    private String _description;
    private long _version;
    private long _planVersion;
    private Date _created;
    private long _updateAuthorId;
    private long _image;
    private String _pitch;

    public PlanDescriptionClp() {
    }

    public Class<?> getModelClass() {
        return PlanDescription.class;
    }

    public String getModelClassName() {
        return PlanDescription.class.getName();
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

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public long getVersion() {
        return _version;
    }

    public void setVersion(long version) {
        _version = version;
    }

    public long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(long planVersion) {
        _planVersion = planVersion;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public long getImage() {
        return _image;
    }

    public void setImage(long image) {
        _image = image;
    }

    public String getPitch() {
        return _pitch;
    }

    public void setPitch(String pitch) {
        _pitch = pitch;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanDescriptionLocalServiceUtil.addPlanDescription(this);
        } else {
            PlanDescriptionLocalServiceUtil.updatePlanDescription(this);
        }
    }

    @Override
    public PlanDescription toEscapedModel() {
        return (PlanDescription) Proxy.newProxyInstance(PlanDescription.class.getClassLoader(),
            new Class[] { PlanDescription.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanDescriptionClp clone = new PlanDescriptionClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setVersion(getVersion());
        clone.setPlanVersion(getPlanVersion());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());
        clone.setImage(getImage());
        clone.setPitch(getPitch());

        return clone;
    }

    public int compareTo(PlanDescription planDescription) {
        int value = 0;

        if (getId() < planDescription.getId()) {
            value = -1;
        } else if (getId() > planDescription.getId()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanDescriptionClp planDescription = null;

        try {
            planDescription = (PlanDescriptionClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = planDescription.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", planVersion=");
        sb.append(getPlanVersion());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append(", image=");
        sb.append(getImage());
        sb.append(", pitch=");
        sb.append(getPitch());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanDescription");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planVersion</column-name><column-value><![CDATA[");
        sb.append(getPlanVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>image</column-name><column-value><![CDATA[");
        sb.append(getImage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pitch</column-name><column-value><![CDATA[");
        sb.append(getPitch());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
