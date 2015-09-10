package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.Plan2ProposalLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class Plan2ProposalClp extends BaseModelImpl<Plan2Proposal>
    implements Plan2Proposal {
    private long _planId;
    private long _proposalId;
    private BaseModel<?> _plan2ProposalRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public Plan2ProposalClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return Plan2Proposal.class;
    }

    @Override
    public String getModelClassName() {
        return Plan2Proposal.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _planId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setPlanId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _planId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planId", getPlanId());
        attributes.put("proposalId", getProposalId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }
    }

    @Override
    public long getPlanId() {
        return _planId;
    }

    @Override
    public void setPlanId(long planId) {
        _planId = planId;

        if (_plan2ProposalRemoteModel != null) {
            try {
                Class<?> clazz = _plan2ProposalRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_plan2ProposalRemoteModel, planId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getProposalId() {
        return _proposalId;
    }

    @Override
    public void setProposalId(long proposalId) {
        _proposalId = proposalId;

        if (_plan2ProposalRemoteModel != null) {
            try {
                Class<?> clazz = _plan2ProposalRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_plan2ProposalRemoteModel, proposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlan2ProposalRemoteModel() {
        return _plan2ProposalRemoteModel;
    }

    public void setPlan2ProposalRemoteModel(
        BaseModel<?> plan2ProposalRemoteModel) {
        _plan2ProposalRemoteModel = plan2ProposalRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _plan2ProposalRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_plan2ProposalRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            Plan2ProposalLocalServiceUtil.addPlan2Proposal(this);
        } else {
            Plan2ProposalLocalServiceUtil.updatePlan2Proposal(this);
        }
    }

    @Override
    public Plan2Proposal toEscapedModel() {
        return (Plan2Proposal) ProxyUtil.newProxyInstance(Plan2Proposal.class.getClassLoader(),
            new Class[] { Plan2Proposal.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        Plan2ProposalClp clone = new Plan2ProposalClp();

        clone.setPlanId(getPlanId());
        clone.setProposalId(getProposalId());

        return clone;
    }

    @Override
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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Plan2ProposalClp)) {
            return false;
        }

        Plan2ProposalClp plan2Proposal = (Plan2ProposalClp) obj;

        long primaryKey = plan2Proposal.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    public Class<?> getClpSerializerClass() {
        return _clpSerializerClass;
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

    @Override
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
