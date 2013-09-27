package com.ext.portlet.model;

import com.ext.portlet.service.ProposalSupporterLocalServiceUtil;
import com.ext.portlet.service.persistence.ProposalSupporterPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class ProposalSupporterClp extends BaseModelImpl<ProposalSupporter>
    implements ProposalSupporter {
    private long _proposalId;
    private long _userId;
    private String _userUuid;
    private Date _createDate;

    public ProposalSupporterClp() {
    }

    public Class<?> getModelClass() {
        return ProposalSupporter.class;
    }

    public String getModelClassName() {
        return ProposalSupporter.class.getName();
    }

    public ProposalSupporterPK getPrimaryKey() {
        return new ProposalSupporterPK(_proposalId, _userId);
    }

    public void setPrimaryKey(ProposalSupporterPK primaryKey) {
        setProposalId(primaryKey.proposalId);
        setUserId(primaryKey.userId);
    }

    public Serializable getPrimaryKeyObj() {
        return new ProposalSupporterPK(_proposalId, _userId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ProposalSupporterPK) primaryKeyObj);
    }

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalSupporterLocalServiceUtil.addProposalSupporter(this);
        } else {
            ProposalSupporterLocalServiceUtil.updateProposalSupporter(this);
        }
    }

    @Override
    public ProposalSupporter toEscapedModel() {
        return (ProposalSupporter) Proxy.newProxyInstance(ProposalSupporter.class.getClassLoader(),
            new Class[] { ProposalSupporter.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalSupporterClp clone = new ProposalSupporterClp();

        clone.setProposalId(getProposalId());
        clone.setUserId(getUserId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    public int compareTo(ProposalSupporter proposalSupporter) {
        ProposalSupporterPK primaryKey = proposalSupporter.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ProposalSupporterClp proposalSupporter = null;

        try {
            proposalSupporter = (ProposalSupporterClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        ProposalSupporterPK primaryKey = proposalSupporter.getPrimaryKey();

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

        sb.append("{proposalId=");
        sb.append(getProposalId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalSupporter");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
