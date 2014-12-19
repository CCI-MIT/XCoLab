package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanDescriptionLocalServiceUtil;

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


public class PlanDescriptionClp extends BaseModelImpl<PlanDescription>
    implements PlanDescription {
    private long _id;
    private long _planId;
    private String _name;
    private String _description;
    private long _version;
    private long _planVersion;
    private Date _created;
    private long _updateAuthorId;
    private long _image;
    private String _pitch;
    private BaseModel<?> _planDescriptionRemoteModel;

    public PlanDescriptionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanDescription.class;
    }

    @Override
    public String getModelClassName() {
        return PlanDescription.class.getName();
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
        attributes.put("planId", getPlanId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("version", getVersion());
        attributes.put("planVersion", getPlanVersion());
        attributes.put("created", getCreated());
        attributes.put("updateAuthorId", getUpdateAuthorId());
        attributes.put("image", getImage());
        attributes.put("pitch", getPitch());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Long version = (Long) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Long planVersion = (Long) attributes.get("planVersion");

        if (planVersion != null) {
            setPlanVersion(planVersion);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Long updateAuthorId = (Long) attributes.get("updateAuthorId");

        if (updateAuthorId != null) {
            setUpdateAuthorId(updateAuthorId);
        }

        Long image = (Long) attributes.get("image");

        if (image != null) {
            setImage(image);
        }

        String pitch = (String) attributes.get("pitch");

        if (pitch != null) {
            setPitch(pitch);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_planDescriptionRemoteModel != null) {
            try {
                Class<?> clazz = _planDescriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_planDescriptionRemoteModel, id);
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

        if (_planDescriptionRemoteModel != null) {
            try {
                Class<?> clazz = _planDescriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_planDescriptionRemoteModel, planId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;

        if (_planDescriptionRemoteModel != null) {
            try {
                Class<?> clazz = _planDescriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_planDescriptionRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;

        if (_planDescriptionRemoteModel != null) {
            try {
                Class<?> clazz = _planDescriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_planDescriptionRemoteModel, description);
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

        if (_planDescriptionRemoteModel != null) {
            try {
                Class<?> clazz = _planDescriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setVersion", long.class);

                method.invoke(_planDescriptionRemoteModel, version);
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

        if (_planDescriptionRemoteModel != null) {
            try {
                Class<?> clazz = _planDescriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanVersion", long.class);

                method.invoke(_planDescriptionRemoteModel, planVersion);
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

        if (_planDescriptionRemoteModel != null) {
            try {
                Class<?> clazz = _planDescriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreated", Date.class);

                method.invoke(_planDescriptionRemoteModel, created);
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

        if (_planDescriptionRemoteModel != null) {
            try {
                Class<?> clazz = _planDescriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdateAuthorId", long.class);

                method.invoke(_planDescriptionRemoteModel, updateAuthorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getImage() {
        return _image;
    }

    @Override
    public void setImage(long image) {
        _image = image;

        if (_planDescriptionRemoteModel != null) {
            try {
                Class<?> clazz = _planDescriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setImage", long.class);

                method.invoke(_planDescriptionRemoteModel, image);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPitch() {
        return _pitch;
    }

    @Override
    public void setPitch(String pitch) {
        _pitch = pitch;

        if (_planDescriptionRemoteModel != null) {
            try {
                Class<?> clazz = _planDescriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setPitch", String.class);

                method.invoke(_planDescriptionRemoteModel, pitch);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanDescriptionRemoteModel() {
        return _planDescriptionRemoteModel;
    }

    public void setPlanDescriptionRemoteModel(
        BaseModel<?> planDescriptionRemoteModel) {
        _planDescriptionRemoteModel = planDescriptionRemoteModel;
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

        Class<?> remoteModelClass = _planDescriptionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planDescriptionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanDescriptionLocalServiceUtil.addPlanDescription(this);
        } else {
            PlanDescriptionLocalServiceUtil.updatePlanDescription(this);
        }
    }

    @Override
    public PlanDescription toEscapedModel() {
        return (PlanDescription) ProxyUtil.newProxyInstance(PlanDescription.class.getClassLoader(),
            new Class[] { PlanDescription.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanDescriptionClp clone = new PlanDescriptionClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setVersion(getVersion());
        clone.setPlanVersion(getPlanVersion());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());
        clone.setImage(getImage());
        clone.setPitch(getPitch());

        return clone;
    }

    @Override
    public int compareTo(PlanDescription planDescription) {
        int value = 0;

        if (getId() < planDescription.getId()) {
            value = -1;
        } else if (getId() > planDescription.getId()) {
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

        if (!(obj instanceof PlanDescriptionClp)) {
            return false;
        }

        PlanDescriptionClp planDescription = (PlanDescriptionClp) obj;

        long primaryKey = planDescription.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", planVersion=");
        sb.append(getPlanVersion());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append(", image=");
        sb.append(getImage());
        sb.append(", pitch=");
        sb.append(getPitch());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanDescription");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
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
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>image</column-name><column-value><![CDATA[");
        sb.append(getImage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pitch</column-name><column-value><![CDATA[");
        sb.append(getPitch());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
