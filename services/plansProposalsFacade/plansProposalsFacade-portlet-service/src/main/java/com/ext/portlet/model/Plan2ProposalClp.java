package com.ext.portlet.model;

import com.ext.portlet.service.Plan2ProposalLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class Plan2ProposalClp extends BaseModelImpl<Plan2Proposal>
    implements Plan2Proposal {
    private long _planId;
    private long _proposalId;

    public Plan2ProposalClp() {
    }

    public Class<?> getModelClass() {
        return Plan2Proposal.class;
    }

    public String getModelClassName() {
        return Plan2Proposal.class.getName();
    }

    public long getPrimaryKey() {
        return _planId;
    }

    public void setPrimaryKey(long primaryKey) {
        setPlanId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_planId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            Plan2ProposalLocalServiceUtil.addPlan2Proposal(this);
        } else {
            Plan2ProposalLocalServiceUtil.updatePlan2Proposal(this);
        }
    }

    @Override
    public Plan2Proposal toEscapedModel() {
        return (Plan2Proposal) Proxy.newProxyInstance(Plan2Proposal.class.getClassLoader(),
            new Class[] { Plan2Proposal.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        Plan2ProposalClp clone = new Plan2ProposalClp();

        clone.setPlanId(getPlanId());
        clone.setProposalId(getProposalId());

        return clone;
    }

    public int compareTo(Plan2Proposal plan2Proposal) {
        long primaryKey = plan2Proposal.getPrimaryKey();

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

        Plan2ProposalClp plan2Proposal = null;

        try {
            plan2Proposal = (Plan2ProposalClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = plan2Proposal.getPrimaryKey();

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
        StringBundler sb = new StringBundler(5);

        sb.append("{planId=");
        sb.append(getPlanId());
        sb.append(", proposalId=");
        sb.append(getProposalId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.Plan2Proposal");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
