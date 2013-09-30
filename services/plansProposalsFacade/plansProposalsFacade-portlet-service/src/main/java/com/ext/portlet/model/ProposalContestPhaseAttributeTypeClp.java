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
    private long _id;
    private String _ribbon;
    private String _hoverText;
    private String _description;
    private boolean _copyOnPromote;

    public ProposalContestPhaseAttributeTypeClp() {
    }

    public Class<?> getModelClass() {
        return ProposalContestPhaseAttributeType.class;
    }

    public String getModelClassName() {
        return ProposalContestPhaseAttributeType.class.getName();
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

    public String getRibbon() {
        return _ribbon;
    }

    public void setRibbon(String ribbon) {
        _ribbon = ribbon;
    }

    public String getHoverText() {
        return _hoverText;
    }

    public void setHoverText(String hoverText) {
        _hoverText = hoverText;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
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

        clone.setId(getId());
        clone.setRibbon(getRibbon());
        clone.setHoverText(getHoverText());
        clone.setDescription(getDescription());
        clone.setCopyOnPromote(getCopyOnPromote());

        return clone;
    }

    public int compareTo(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        long primaryKey = proposalContestPhaseAttributeType.getPrimaryKey();

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

        ProposalContestPhaseAttributeTypeClp proposalContestPhaseAttributeType = null;

        try {
            proposalContestPhaseAttributeType = (ProposalContestPhaseAttributeTypeClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = proposalContestPhaseAttributeType.getPrimaryKey();

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
        sb.append(", ribbon=");
        sb.append(getRibbon());
        sb.append(", hoverText=");
        sb.append(getHoverText());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", copyOnPromote=");
        sb.append(getCopyOnPromote());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalContestPhaseAttributeType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ribbon</column-name><column-value><![CDATA[");
        sb.append(getRibbon());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hoverText</column-name><column-value><![CDATA[");
        sb.append(getHoverText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>copyOnPromote</column-name><column-value><![CDATA[");
        sb.append(getCopyOnPromote());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
