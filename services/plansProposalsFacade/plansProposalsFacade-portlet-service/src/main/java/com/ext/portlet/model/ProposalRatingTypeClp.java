package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;

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


public class ProposalRatingTypeClp extends BaseModelImpl<ProposalRatingType>
    implements ProposalRatingType {
    private long _ratingTypeId;
    private String _label;
    private BaseModel<?> _proposalRatingTypeRemoteModel;

    public ProposalRatingTypeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalRatingType.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalRatingType.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _ratingTypeId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setRatingTypeId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ratingTypeId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ratingTypeId", getRatingTypeId());
        attributes.put("label", getLabel());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long ratingTypeId = (Long) attributes.get("ratingTypeId");

        if (ratingTypeId != null) {
            setRatingTypeId(ratingTypeId);
        }

        String label = (String) attributes.get("label");

        if (label != null) {
            setLabel(label);
        }
    }

    @Override
    public long getRatingTypeId() {
        return _ratingTypeId;
    }

    @Override
    public void setRatingTypeId(long ratingTypeId) {
        _ratingTypeId = ratingTypeId;

        if (_proposalRatingTypeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setRatingTypeId", long.class);

                method.invoke(_proposalRatingTypeRemoteModel, ratingTypeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLabel() {
        return _label;
    }

    @Override
    public void setLabel(String label) {
        _label = label;

        if (_proposalRatingTypeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setLabel", String.class);

                method.invoke(_proposalRatingTypeRemoteModel, label);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalRatingTypeRemoteModel() {
        return _proposalRatingTypeRemoteModel;
    }

    public void setProposalRatingTypeRemoteModel(
        BaseModel<?> proposalRatingTypeRemoteModel) {
        _proposalRatingTypeRemoteModel = proposalRatingTypeRemoteModel;
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

        Class<?> remoteModelClass = _proposalRatingTypeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalRatingTypeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalRatingTypeLocalServiceUtil.addProposalRatingType(this);
        } else {
            ProposalRatingTypeLocalServiceUtil.updateProposalRatingType(this);
        }
    }

    @Override
    public ProposalRatingType toEscapedModel() {
        return (ProposalRatingType) ProxyUtil.newProxyInstance(ProposalRatingType.class.getClassLoader(),
            new Class[] { ProposalRatingType.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalRatingTypeClp clone = new ProposalRatingTypeClp();

        clone.setRatingTypeId(getRatingTypeId());
        clone.setLabel(getLabel());

        return clone;
    }

    @Override
    public int compareTo(ProposalRatingType proposalRatingType) {
        long primaryKey = proposalRatingType.getPrimaryKey();

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

        if (!(obj instanceof ProposalRatingTypeClp)) {
            return false;
        }

        ProposalRatingTypeClp proposalRatingType = (ProposalRatingTypeClp) obj;

        long primaryKey = proposalRatingType.getPrimaryKey();

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

        sb.append("{ratingTypeId=");
        sb.append(getRatingTypeId());
        sb.append(", label=");
        sb.append(getLabel());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalRatingType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ratingTypeId</column-name><column-value><![CDATA[");
        sb.append(getRatingTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>label</column-name><column-value><![CDATA[");
        sb.append(getLabel());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
