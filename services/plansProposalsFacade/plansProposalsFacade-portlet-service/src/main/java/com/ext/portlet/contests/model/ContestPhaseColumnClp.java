package com.ext.portlet.contests.model;

import com.ext.portlet.contests.service.ContestPhaseColumnLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ContestPhaseColumnClp extends BaseModelImpl<ContestPhaseColumn>
    implements ContestPhaseColumn {
    private long _id;
    private long _ContestPhasePK;
    private String _columnName;
    private int _columnWeight;
    private boolean _defaultSort;

    public ContestPhaseColumnClp() {
    }

    public Class<?> getModelClass() {
        return ContestPhaseColumn.class;
    }

    public String getModelClassName() {
        return ContestPhaseColumn.class.getName();
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

    public long getContestPhasePK() {
        return _ContestPhasePK;
    }

    public void setContestPhasePK(long ContestPhasePK) {
        _ContestPhasePK = ContestPhasePK;
    }

    public String getColumnName() {
        return _columnName;
    }

    public void setColumnName(String columnName) {
        _columnName = columnName;
    }

    public int getColumnWeight() {
        return _columnWeight;
    }

    public void setColumnWeight(int columnWeight) {
        _columnWeight = columnWeight;
    }

    public boolean getDefaultSort() {
        return _defaultSort;
    }

    public boolean isDefaultSort() {
        return _defaultSort;
    }

    public void setDefaultSort(boolean defaultSort) {
        _defaultSort = defaultSort;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestPhaseColumnLocalServiceUtil.addContestPhaseColumn(this);
        } else {
            ContestPhaseColumnLocalServiceUtil.updateContestPhaseColumn(this);
        }
    }

    @Override
    public ContestPhaseColumn toEscapedModel() {
        return (ContestPhaseColumn) Proxy.newProxyInstance(ContestPhaseColumn.class.getClassLoader(),
            new Class[] { ContestPhaseColumn.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestPhaseColumnClp clone = new ContestPhaseColumnClp();

        clone.setId(getId());
        clone.setContestPhasePK(getContestPhasePK());
        clone.setColumnName(getColumnName());
        clone.setColumnWeight(getColumnWeight());
        clone.setDefaultSort(getDefaultSort());

        return clone;
    }

    public int compareTo(ContestPhaseColumn contestPhaseColumn) {
        int value = 0;

        if (getColumnWeight() < contestPhaseColumn.getColumnWeight()) {
            value = -1;
        } else if (getColumnWeight() > contestPhaseColumn.getColumnWeight()) {
            value = 1;
        } else {
            value = 0;
        }

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

        ContestPhaseColumnClp contestPhaseColumn = null;

        try {
            contestPhaseColumn = (ContestPhaseColumnClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = contestPhaseColumn.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", ContestPhasePK=");
        sb.append(getContestPhasePK());
        sb.append(", columnName=");
        sb.append(getColumnName());
        sb.append(", columnWeight=");
        sb.append(getColumnWeight());
        sb.append(", defaultSort=");
        sb.append(getDefaultSort());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.contests.model.ContestPhaseColumn");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPhasePK</column-name><column-value><![CDATA[");
        sb.append(getContestPhasePK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnName</column-name><column-value><![CDATA[");
        sb.append(getColumnName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnWeight</column-name><column-value><![CDATA[");
        sb.append(getColumnWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultSort</column-name><column-value><![CDATA[");
        sb.append(getDefaultSort());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
