package com.ext.portlet.model;

import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.ext.portlet.service.persistence.ProposalVersionPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class ProposalVersionClp extends BaseModelImpl<ProposalVersion>
    implements ProposalVersion {
    private long _proposalId;
    private int _version;
    private long _authorId;
    private Date _createDate;
    private String _updateType;
    private long _updateAdditionalId;

    public ProposalVersionClp() {
    }

    public Class<?> getModelClass() {
        return ProposalVersion.class;
    }

    public String getModelClassName() {
        return ProposalVersion.class.getName();
    }

    public ProposalVersionPK getPrimaryKey() {
        return new ProposalVersionPK(_proposalId, _version);
    }

    public void setPrimaryKey(ProposalVersionPK primaryKey) {
        setProposalId(primaryKey.proposalId);
        setVersion(primaryKey.version);
    }

    public Serializable getPrimaryKeyObj() {
        return new ProposalVersionPK(_proposalId, _version);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ProposalVersionPK) primaryKeyObj);
    }

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }

    public int getVersion() {
        return _version;
    }

    public void setVersion(int version) {
        _version = version;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public String getUpdateType() {
        return _updateType;
    }

    public void setUpdateType(String updateType) {
        _updateType = updateType;
    }

    public long getUpdateAdditionalId() {
        return _updateAdditionalId;
    }

    public void setUpdateAdditionalId(long updateAdditionalId) {
        _updateAdditionalId = updateAdditionalId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalVersionLocalServiceUtil.addProposalVersion(this);
        } else {
            ProposalVersionLocalServiceUtil.updateProposalVersion(this);
        }
    }

    @Override
    public ProposalVersion toEscapedModel() {
        return (ProposalVersion) Proxy.newProxyInstance(ProposalVersion.class.getClassLoader(),
            new Class[] { ProposalVersion.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalVersionClp clone = new ProposalVersionClp();

        clone.setProposalId(getProposalId());
        clone.setVersion(getVersion());
        clone.setAuthorId(getAuthorId());
        clone.setCreateDate(getCreateDate());
        clone.setUpdateType(getUpdateType());
        clone.setUpdateAdditionalId(getUpdateAdditionalId());

        return clone;
    }

    public int compareTo(ProposalVersion proposalVersion) {
        ProposalVersionPK primaryKey = proposalVersion.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ProposalVersionClp proposalVersion = null;

        try {
            proposalVersion = (ProposalVersionClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        ProposalVersionPK primaryKey = proposalVersion.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{proposalId=");
        sb.append(getProposalId());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", updateType=");
        sb.append(getUpdateType());
        sb.append(", updateAdditionalId=");
        sb.append(getUpdateAdditionalId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalVersion");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateType</column-name><column-value><![CDATA[");
        sb.append(getUpdateType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAdditionalId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAdditionalId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
