package com.ext.portlet.model;

import com.ext.portlet.service.ProposalAttributeTypeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ProposalAttributeTypeClp extends BaseModelImpl<ProposalAttributeType>
    implements ProposalAttributeType {
    private String _name;
    private boolean _visibleInVersionHistory;
    private boolean _copyOnPromote;

    public ProposalAttributeTypeClp() {
    }

    public Class<?> getModelClass() {
        return ProposalAttributeType.class;
    }

    public String getModelClassName() {
        return ProposalAttributeType.class.getName();
    }

    public String getPrimaryKey() {
        return _name;
    }

    public void setPrimaryKey(String primaryKey) {
        setName(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return _name;
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((String) primaryKeyObj);
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public boolean getVisibleInVersionHistory() {
        return _visibleInVersionHistory;
    }

    public boolean isVisibleInVersionHistory() {
        return _visibleInVersionHistory;
    }

    public void setVisibleInVersionHistory(boolean visibleInVersionHistory) {
        _visibleInVersionHistory = visibleInVersionHistory;
    }

    public boolean getCopyOnPromote() {
        return _copyOnPromote;
    }

    public boolean isCopyOnPromote() {
        return _copyOnPromote;
    }

    public void setCopyOnPromote(boolean copyOnPromote) {
        _copyOnPromote = copyOnPromote;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalAttributeTypeLocalServiceUtil.addProposalAttributeType(this);
        } else {
            ProposalAttributeTypeLocalServiceUtil.updateProposalAttributeType(this);
        }
    }

    @Override
    public ProposalAttributeType toEscapedModel() {
        return (ProposalAttributeType) Proxy.newProxyInstance(ProposalAttributeType.class.getClassLoader(),
            new Class[] { ProposalAttributeType.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalAttributeTypeClp clone = new ProposalAttributeTypeClp();

        clone.setName(getName());
        clone.setVisibleInVersionHistory(getVisibleInVersionHistory());
        clone.setCopyOnPromote(getCopyOnPromote());

        return clone;
    }

    public int compareTo(ProposalAttributeType proposalAttributeType) {
        String primaryKey = proposalAttributeType.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ProposalAttributeTypeClp proposalAttributeType = null;

        try {
            proposalAttributeType = (ProposalAttributeTypeClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        String primaryKey = proposalAttributeType.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{name=");
        sb.append(getName());
        sb.append(", visibleInVersionHistory=");
        sb.append(getVisibleInVersionHistory());
        sb.append(", copyOnPromote=");
        sb.append(getCopyOnPromote());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalAttributeType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visibleInVersionHistory</column-name><column-value><![CDATA[");
        sb.append(getVisibleInVersionHistory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>copyOnPromote</column-name><column-value><![CDATA[");
        sb.append(getCopyOnPromote());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
