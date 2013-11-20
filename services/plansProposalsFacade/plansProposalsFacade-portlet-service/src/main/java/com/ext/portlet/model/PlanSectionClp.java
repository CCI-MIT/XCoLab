package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanSectionLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PlanSectionClp extends BaseModelImpl<PlanSection>
    implements PlanSection {
    private long _id;
    private long _planSectionDefinitionId;
    private long _planId;
    private String _content;
    private long _numericValue;
    private Date _created;
    private long _version;
    private long _planVersion;
    private long _updateAuthorId;
    private BaseModel<?> _planSectionRemoteModel;

    public PlanSectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanSection.class;
    }

    @Override
    public String getModelClassName() {
        return PlanSection.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _id;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _id;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("planSectionDefinitionId", getPlanSectionDefinitionId());
        attributes.put("planId", getPlanId());
        attributes.put("content", getContent());
        attributes.put("numericValue", getNumericValue());
        attributes.put("created", getCreated());
        attributes.put("version", getVersion());
        attributes.put("planVersion", getPlanVersion());
        attributes.put("updateAuthorId", getUpdateAuthorId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long planSectionDefinitionId = (Long) attributes.get(
                "planSectionDefinitionId");

        if (planSectionDefinitionId != null) {
            setPlanSectionDefinitionId(planSectionDefinitionId);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        String content = (String) attributes.get("content");

        if (content != null) {
            setContent(content);
        }

        Long numericValue = (Long) attributes.get("numericValue");

        if (numericValue != null) {
            setNumericValue(numericValue);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Long version = (Long) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Long planVersion = (Long) attributes.get("planVersion");

        if (planVersion != null) {
            setPlanVersion(planVersion);
        }

        Long updateAuthorId = (Long) attributes.get("updateAuthorId");

        if (updateAuthorId != null) {
            setUpdateAuthorId(updateAuthorId);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_planSectionRemoteModel != null) {
            try {
                Class<?> clazz = _planSectionRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_planSectionRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanSectionDefinitionId() {
        return _planSectionDefinitionId;
    }

    @Override
    public void setPlanSectionDefinitionId(long planSectionDefinitionId) {
        _planSectionDefinitionId = planSectionDefinitionId;

        if (_planSectionRemoteModel != null) {
            try {
                Class<?> clazz = _planSectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanSectionDefinitionId",
                        long.class);

                method.invoke(_planSectionRemoteModel, planSectionDefinitionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanId() {
        return _planId;
    }

    @Override
    public void setPlanId(long planId) {
        _planId = planId;

        if (_planSectionRemoteModel != null) {
            try {
                Class<?> clazz = _planSectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_planSectionRemoteModel, planId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContent() {
        return _content;
    }

    @Override
    public void setContent(String content) {
        _content = content;

        if (_planSectionRemoteModel != null) {
            try {
                Class<?> clazz = _planSectionRemoteModel.getClass();

                Method method = clazz.getMethod("setContent", String.class);

                method.invoke(_planSectionRemoteModel, content);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getNumericValue() {
        return _numericValue;
    }

    @Override
    public void setNumericValue(long numericValue) {
        _numericValue = numericValue;

        if (_planSectionRemoteModel != null) {
            try {
                Class<?> clazz = _planSectionRemoteModel.getClass();

                Method method = clazz.getMethod("setNumericValue", long.class);

                method.invoke(_planSectionRemoteModel, numericValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreated() {
        return _created;
    }

    @Override
    public void setCreated(Date created) {
        _created = created;

        if (_planSectionRemoteModel != null) {
            try {
                Class<?> clazz = _planSectionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreated", Date.class);

                method.invoke(_planSectionRemoteModel, created);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getVersion() {
        return _version;
    }

    @Override
    public void setVersion(long version) {
        _version = version;

        if (_planSectionRemoteModel != null) {
            try {
                Class<?> clazz = _planSectionRemoteModel.getClass();

                Method method = clazz.getMethod("setVersion", long.class);

                method.invoke(_planSectionRemoteModel, version);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanVersion() {
        return _planVersion;
    }

    @Override
    public void setPlanVersion(long planVersion) {
        _planVersion = planVersion;

        if (_planSectionRemoteModel != null) {
            try {
                Class<?> clazz = _planSectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanVersion", long.class);

                method.invoke(_planSectionRemoteModel, planVersion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    @Override
    public void setUpdateAuthorId(long updateAuthorId) {
        _updateAuthorId = updateAuthorId;

        if (_planSectionRemoteModel != null) {
            try {
                Class<?> clazz = _planSectionRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdateAuthorId", long.class);

                method.invoke(_planSectionRemoteModel, updateAuthorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanSectionRemoteModel() {
        return _planSectionRemoteModel;
    }

    public void setPlanSectionRemoteModel(BaseModel<?> planSectionRemoteModel) {
        _planSectionRemoteModel = planSectionRemoteModel;
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

        Class<?> remoteModelClass = _planSectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planSectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanSectionLocalServiceUtil.addPlanSection(this);
        } else {
            PlanSectionLocalServiceUtil.updatePlanSection(this);
        }
    }

    @Override
    public PlanSection toEscapedModel() {
        return (PlanSection) ProxyUtil.newProxyInstance(PlanSection.class.getClassLoader(),
            new Class[] { PlanSection.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanSectionClp clone = new PlanSectionClp();

        clone.setId(getId());
        clone.setPlanSectionDefinitionId(getPlanSectionDefinitionId());
        clone.setPlanId(getPlanId());
        clone.setContent(getContent());
        clone.setNumericValue(getNumericValue());
        clone.setCreated(getCreated());
        clone.setVersion(getVersion());
        clone.setPlanVersion(getPlanVersion());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    @Override
    public int compareTo(PlanSection planSection) {
        int value = 0;

        if (getId() < planSection.getId()) {
            value = -1;
        } else if (getId() > planSection.getId()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanSectionClp)) {
            return false;
        }

        PlanSectionClp planSection = (PlanSectionClp) obj;

        long primaryKey = planSection.getPrimaryKey();

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
        StringBundler sb = new StringBundler(19);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planSectionDefinitionId=");
        sb.append(getPlanSectionDefinitionId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", content=");
        sb.append(getContent());
        sb.append(", numericValue=");
        sb.append(getNumericValue());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", planVersion=");
        sb.append(getPlanVersion());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanSection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planSectionDefinitionId</column-name><column-value><![CDATA[");
        sb.append(getPlanSectionDefinitionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>content</column-name><column-value><![CDATA[");
        sb.append(getContent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>numericValue</column-name><column-value><![CDATA[");
        sb.append(getNumericValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planVersion</column-name><column-value><![CDATA[");
        sb.append(getPlanVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
