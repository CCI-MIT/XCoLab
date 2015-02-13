package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;

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


public class ContestPhaseTypeClp extends BaseModelImpl<ContestPhaseType>
    implements ContestPhaseType {
    private long _id;
    private String _name;
    private String _description;
    private String _status;
    private boolean _fellowScreeningActiveDefault;
    private String _contestPhaseAutopromoteDefault;
    private boolean _invisible;
    private int _pointsAccessible;
    private BaseModel<?> _contestPhaseTypeRemoteModel;

    public ContestPhaseTypeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContestPhaseType.class;
    }

    @Override
    public String getModelClassName() {
        return ContestPhaseType.class.getName();
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
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("status", getStatus());
        attributes.put("fellowScreeningActiveDefault",
            getFellowScreeningActiveDefault());
        attributes.put("contestPhaseAutopromoteDefault",
            getContestPhaseAutopromoteDefault());
        attributes.put("invisible", getInvisible());
        attributes.put("pointsAccessible", getPointsAccessible());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Boolean fellowScreeningActiveDefault = (Boolean) attributes.get(
                "fellowScreeningActiveDefault");

        if (fellowScreeningActiveDefault != null) {
            setFellowScreeningActiveDefault(fellowScreeningActiveDefault);
        }

        String contestPhaseAutopromoteDefault = (String) attributes.get(
                "contestPhaseAutopromoteDefault");

        if (contestPhaseAutopromoteDefault != null) {
            setContestPhaseAutopromoteDefault(contestPhaseAutopromoteDefault);
        }

        Boolean invisible = (Boolean) attributes.get("invisible");

        if (invisible != null) {
            setInvisible(invisible);
        }

        Integer pointsAccessible = (Integer) attributes.get("pointsAccessible");

        if (pointsAccessible != null) {
            setPointsAccessible(pointsAccessible);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_contestPhaseTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_contestPhaseTypeRemoteModel, id);
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

        if (_contestPhaseTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_contestPhaseTypeRemoteModel, name);
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

        if (_contestPhaseTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_contestPhaseTypeRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStatus() {
        return _status;
    }

    @Override
    public void setStatus(String status) {
        _status = status;

        if (_contestPhaseTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setStatus", String.class);

                method.invoke(_contestPhaseTypeRemoteModel, status);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getFellowScreeningActiveDefault() {
        return _fellowScreeningActiveDefault;
    }

    @Override
    public boolean isFellowScreeningActiveDefault() {
        return _fellowScreeningActiveDefault;
    }

    @Override
    public void setFellowScreeningActiveDefault(
        boolean fellowScreeningActiveDefault) {
        _fellowScreeningActiveDefault = fellowScreeningActiveDefault;

        if (_contestPhaseTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setFellowScreeningActiveDefault",
                        boolean.class);

                method.invoke(_contestPhaseTypeRemoteModel,
                    fellowScreeningActiveDefault);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContestPhaseAutopromoteDefault() {
        return _contestPhaseAutopromoteDefault;
    }

    @Override
    public void setContestPhaseAutopromoteDefault(
        String contestPhaseAutopromoteDefault) {
        _contestPhaseAutopromoteDefault = contestPhaseAutopromoteDefault;

        if (_contestPhaseTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPhaseAutopromoteDefault",
                        String.class);

                method.invoke(_contestPhaseTypeRemoteModel,
                    contestPhaseAutopromoteDefault);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getInvisible() {
        return _invisible;
    }

    @Override
    public boolean isInvisible() {
        return _invisible;
    }

    @Override
    public void setInvisible(boolean invisible) {
        _invisible = invisible;

        if (_contestPhaseTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setInvisible", boolean.class);

                method.invoke(_contestPhaseTypeRemoteModel, invisible);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPointsAccessible() {
        return _pointsAccessible;
    }

    @Override
    public void setPointsAccessible(int pointsAccessible) {
        _pointsAccessible = pointsAccessible;

        if (_contestPhaseTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestPhaseTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setPointsAccessible", int.class);

                method.invoke(_contestPhaseTypeRemoteModel, pointsAccessible);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContestPhaseTypeRemoteModel() {
        return _contestPhaseTypeRemoteModel;
    }

    public void setContestPhaseTypeRemoteModel(
        BaseModel<?> contestPhaseTypeRemoteModel) {
        _contestPhaseTypeRemoteModel = contestPhaseTypeRemoteModel;
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

        Class<?> remoteModelClass = _contestPhaseTypeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_contestPhaseTypeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestPhaseTypeLocalServiceUtil.addContestPhaseType(this);
        } else {
            ContestPhaseTypeLocalServiceUtil.updateContestPhaseType(this);
        }
    }

    @Override
    public ContestPhaseType toEscapedModel() {
        return (ContestPhaseType) ProxyUtil.newProxyInstance(ContestPhaseType.class.getClassLoader(),
            new Class[] { ContestPhaseType.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestPhaseTypeClp clone = new ContestPhaseTypeClp();

        clone.setId(getId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setStatus(getStatus());
        clone.setFellowScreeningActiveDefault(getFellowScreeningActiveDefault());
        clone.setContestPhaseAutopromoteDefault(getContestPhaseAutopromoteDefault());
        clone.setInvisible(getInvisible());
        clone.setPointsAccessible(getPointsAccessible());

        return clone;
    }

    @Override
    public int compareTo(ContestPhaseType contestPhaseType) {
        long primaryKey = contestPhaseType.getPrimaryKey();

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

        if (!(obj instanceof ContestPhaseTypeClp)) {
            return false;
        }

        ContestPhaseTypeClp contestPhaseType = (ContestPhaseTypeClp) obj;

        long primaryKey = contestPhaseType.getPrimaryKey();

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
        StringBundler sb = new StringBundler(17);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", fellowScreeningActiveDefault=");
        sb.append(getFellowScreeningActiveDefault());
        sb.append(", contestPhaseAutopromoteDefault=");
        sb.append(getContestPhaseAutopromoteDefault());
        sb.append(", invisible=");
        sb.append(getInvisible());
        sb.append(", pointsAccessible=");
        sb.append(getPointsAccessible());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ContestPhaseType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
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
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fellowScreeningActiveDefault</column-name><column-value><![CDATA[");
        sb.append(getFellowScreeningActiveDefault());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestPhaseAutopromoteDefault</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseAutopromoteDefault());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invisible</column-name><column-value><![CDATA[");
        sb.append(getInvisible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pointsAccessible</column-name><column-value><![CDATA[");
        sb.append(getPointsAccessible());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
