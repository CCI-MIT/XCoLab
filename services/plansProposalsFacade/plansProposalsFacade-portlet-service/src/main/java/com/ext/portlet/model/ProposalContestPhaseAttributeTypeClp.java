package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalContestPhaseAttributeTypeLocalServiceUtil;

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


public class ProposalContestPhaseAttributeTypeClp extends BaseModelImpl<ProposalContestPhaseAttributeType>
    implements ProposalContestPhaseAttributeType {
    private String _name;
    private boolean _copyOnPromote;
    private BaseModel<?> _proposalContestPhaseAttributeTypeRemoteModel;

    public ProposalContestPhaseAttributeTypeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalContestPhaseAttributeType.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalContestPhaseAttributeType.class.getName();
    }

    @Override
    public String getPrimaryKey() {
        return _name;
    }

    @Override
    public void setPrimaryKey(String primaryKey) {
        setName(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _name;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((String) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("name", getName());
        attributes.put("copyOnPromote", getCopyOnPromote());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Boolean copyOnPromote = (Boolean) attributes.get("copyOnPromote");

        if (copyOnPromote != null) {
            setCopyOnPromote(copyOnPromote);
        }
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;

        if (_proposalContestPhaseAttributeTypeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalContestPhaseAttributeTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_proposalContestPhaseAttributeTypeRemoteModel,
                    name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCopyOnPromote() {
        return _copyOnPromote;
    }

    @Override
    public boolean isCopyOnPromote() {
        return _copyOnPromote;
    }

    @Override
    public void setCopyOnPromote(boolean copyOnPromote) {
        _copyOnPromote = copyOnPromote;

        if (_proposalContestPhaseAttributeTypeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalContestPhaseAttributeTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setCopyOnPromote",
                        boolean.class);

                method.invoke(_proposalContestPhaseAttributeTypeRemoteModel,
                    copyOnPromote);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalContestPhaseAttributeTypeRemoteModel() {
        return _proposalContestPhaseAttributeTypeRemoteModel;
    }

    public void setProposalContestPhaseAttributeTypeRemoteModel(
        BaseModel<?> proposalContestPhaseAttributeTypeRemoteModel) {
        _proposalContestPhaseAttributeTypeRemoteModel = proposalContestPhaseAttributeTypeRemoteModel;
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

        Class<?> remoteModelClass = _proposalContestPhaseAttributeTypeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalContestPhaseAttributeTypeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalContestPhaseAttributeTypeLocalServiceUtil.addProposalContestPhaseAttributeType(this);
        } else {
            ProposalContestPhaseAttributeTypeLocalServiceUtil.updateProposalContestPhaseAttributeType(this);
        }
    }

    @Override
    public ProposalContestPhaseAttributeType toEscapedModel() {
        return (ProposalContestPhaseAttributeType) ProxyUtil.newProxyInstance(ProposalContestPhaseAttributeType.class.getClassLoader(),
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

    @Override
    public int compareTo(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        String primaryKey = proposalContestPhaseAttributeType.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalContestPhaseAttributeTypeClp)) {
            return false;
        }

        ProposalContestPhaseAttributeTypeClp proposalContestPhaseAttributeType = (ProposalContestPhaseAttributeTypeClp) obj;

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

    @Override
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
