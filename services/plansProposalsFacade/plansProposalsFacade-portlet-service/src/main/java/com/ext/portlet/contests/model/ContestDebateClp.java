package com.ext.portlet.contests.model;

import com.ext.portlet.contests.service.ContestDebateLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ContestDebateClp extends BaseModelImpl<ContestDebate>
    implements ContestDebate {
    private long _id;
    private long _debateId;
    private long _ContestPK;

    public ContestDebateClp() {
    }

    public Class<?> getModelClass() {
        return ContestDebate.class;
    }

    public String getModelClassName() {
        return ContestDebate.class.getName();
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

    public long getDebateId() {
        return _debateId;
    }

    public void setDebateId(long debateId) {
        _debateId = debateId;
    }

    public long getContestPK() {
        return _ContestPK;
    }

    public void setContestPK(long ContestPK) {
        _ContestPK = ContestPK;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestDebateLocalServiceUtil.addContestDebate(this);
        } else {
            ContestDebateLocalServiceUtil.updateContestDebate(this);
        }
    }

    @Override
    public ContestDebate toEscapedModel() {
        return (ContestDebate) Proxy.newProxyInstance(ContestDebate.class.getClassLoader(),
            new Class[] { ContestDebate.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestDebateClp clone = new ContestDebateClp();

        clone.setId(getId());
        clone.setDebateId(getDebateId());
        clone.setContestPK(getContestPK());

        return clone;
    }

    public int compareTo(ContestDebate contestDebate) {
        long primaryKey = contestDebate.getPrimaryKey();

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

        ContestDebateClp contestDebate = null;

        try {
            contestDebate = (ContestDebateClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = contestDebate.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", debateId=");
        sb.append(getDebateId());
        sb.append(", ContestPK=");
        sb.append(getContestPK());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.contests.model.ContestDebate");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateId</column-name><column-value><![CDATA[");
        sb.append(getDebateId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPK</column-name><column-value><![CDATA[");
        sb.append(getContestPK());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
