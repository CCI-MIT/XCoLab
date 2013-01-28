package com.ext.portlet.model;

import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ContestPhaseTypeClp extends BaseModelImpl<ContestPhaseType>
    implements ContestPhaseType {
    private long _id;
    private String _name;
    private String _description;
    private String _status;

    public ContestPhaseTypeClp() {
    }

    public Class<?> getModelClass() {
        return ContestPhaseType.class;
    }

    public String getModelClassName() {
        return ContestPhaseType.class.getName();
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

    public String getStatus() {
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestPhaseTypeLocalServiceUtil.addContestPhaseType(this);
        } else {
            ContestPhaseTypeLocalServiceUtil.updateContestPhaseType(this);
        }
    }

    @Override
    public ContestPhaseType toEscapedModel() {
        return (ContestPhaseType) Proxy.newProxyInstance(ContestPhaseType.class.getClassLoader(),
            new Class[] { ContestPhaseType.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestPhaseTypeClp clone = new ContestPhaseTypeClp();

        clone.setId(getId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setStatus(getStatus());

        return clone;
    }

    public int compareTo(ContestPhaseType contestPhaseType) {
        long primaryKey = contestPhaseType.getPrimaryKey();

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

        ContestPhaseTypeClp contestPhaseType = null;

        try {
            contestPhaseType = (ContestPhaseTypeClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = contestPhaseType.getPrimaryKey();

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
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ContestPhaseType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
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
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
