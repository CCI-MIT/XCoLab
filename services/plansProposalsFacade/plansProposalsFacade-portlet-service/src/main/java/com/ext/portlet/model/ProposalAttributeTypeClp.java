package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalAttributeTypeLocalServiceUtil;

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


public class ProposalAttributeTypeClp extends BaseModelImpl<ProposalAttributeType>
    implements ProposalAttributeType {
    private String _name;
    private boolean _visibleInVersionHistory;
    private boolean _copyOnPromote;
    private BaseModel<?> _proposalAttributeTypeRemoteModel;

    public ProposalAttributeTypeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalAttributeType.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalAttributeType.class.getName();
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
        attributes.put("visibleInVersionHistory", getVisibleInVersionHistory());
        attributes.put("copyOnPromote", getCopyOnPromote());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Boolean visibleInVersionHistory = (Boolean) attributes.get(
                "visibleInVersionHistory");

        if (visibleInVersionHistory != null) {
            setVisibleInVersionHistory(visibleInVersionHistory);
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

        if (_proposalAttributeTypeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalAttributeTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_proposalAttributeTypeRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getVisibleInVersionHistory() {
        return _visibleInVersionHistory;
    }

    @Override
    public boolean isVisibleInVersionHistory() {
        return _visibleInVersionHistory;
    }

    @Override
    public void setVisibleInVersionHistory(boolean visibleInVersionHistory) {
        _visibleInVersionHistory = visibleInVersionHistory;

        if (_proposalAttributeTypeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalAttributeTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setVisibleInVersionHistory",
                        boolean.class);

                method.invoke(_proposalAttributeTypeRemoteModel,
                    visibleInVersionHistory);
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

        if (_proposalAttributeTypeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalAttributeTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setCopyOnPromote",
                        boolean.class);

                method.invoke(_proposalAttributeTypeRemoteModel, copyOnPromote);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalAttributeTypeRemoteModel() {
        return _proposalAttributeTypeRemoteModel;
    }

    public void setProposalAttributeTypeRemoteModel(
        BaseModel<?> proposalAttributeTypeRemoteModel) {
        _proposalAttributeTypeRemoteModel = proposalAttributeTypeRemoteModel;
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

        Class<?> remoteModelClass = _proposalAttributeTypeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalAttributeTypeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalAttributeTypeLocalServiceUtil.addProposalAttributeType(this);
        } else {
            ProposalAttributeTypeLocalServiceUtil.updateProposalAttributeType(this);
        }
    }

    @Override
    public ProposalAttributeType toEscapedModel() {
        return (ProposalAttributeType) ProxyUtil.newProxyInstance(ProposalAttributeType.class.getClassLoader(),
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

    @Override
    public int compareTo(ProposalAttributeType proposalAttributeType) {
        String primaryKey = proposalAttributeType.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalAttributeTypeClp)) {
            return false;
        }

        ProposalAttributeTypeClp proposalAttributeType = (ProposalAttributeTypeClp) obj;

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

    @Override
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
