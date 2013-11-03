package com.ext.portlet.model;

import com.ext.portlet.service.ProposalContestPhaseAttributeTypeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ProposalContestPhaseAttributeTypeClp extends BaseModelImpl<ProposalContestPhaseAttributeType>
    implements ProposalContestPhaseAttributeType {
    private String _name;
    private boolean _copyOnPromote;

    public ProposalContestPhaseAttributeTypeClp() {
    }

    public Class<?> getModelClass() {
        return ProposalContestPhaseAttributeType.class;
    }

    public String getModelClassName() {
        return ProposalContestPhaseAttributeType.class.getName();
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
            ProposalContestPhaseAttributeTypeLocalServiceUtil.addProposalContestPhaseAttributeType(this);
        } else {
            ProposalContestPhaseAttributeTypeLocalServiceUtil.updateProposalContestPhaseAttributeType(this);
        }
    }

    @Override
    public ProposalContestPhaseAttributeType toEscapedModel() {
        return (ProposalContestPhaseAttributeType) Proxy.newProxyInstance(ProposalContestPhaseAttributeType.class.getClassLoader(),
            new Class[] { ProposalContestPhaseAttributeType.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalContestPhaseAttributeTypeClp clone = new ProposalContestPhaseAttributeTypeClp();

        clone.setName(getName());
        clone.setCopyOnPromote(getCopyOnPromote());

        return clone;
    }

    public int compareTo(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        String primaryKey = proposalContestPhaseAttributeType.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ProposalContestPhaseAttributeTypeClp proposalContestPhaseAttributeType = null;

        try {
            proposalContestPhaseAttributeType = (ProposalContestPhaseAttributeTypeClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        String primaryKey = proposalContestPhaseAttributeType.getPrimaryKey();

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
        StringBundler sb = new StringBundler(5);

        sb.append("{name=");
        sb.append(getName());
        sb.append(", copyOnPromote=");
        sb.append(getCopyOnPromote());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalContestPhaseAttributeType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>copyOnPromote</column-name><column-value><![CDATA[");
        sb.append(getCopyOnPromote());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
