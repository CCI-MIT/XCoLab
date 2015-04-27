package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ImpactIterationLocalServiceUtil;
import com.ext.portlet.service.persistence.ImpactIterationPK;

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


public class ImpactIterationClp extends BaseModelImpl<ImpactIteration>
    implements ImpactIteration {
    private long _iterationId;
    private int _year;
    private BaseModel<?> _impactIterationRemoteModel;

    public ImpactIterationClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImpactIteration.class;
    }

    @Override
    public String getModelClassName() {
        return ImpactIteration.class.getName();
    }

    @Override
    public ImpactIterationPK getPrimaryKey() {
        return new ImpactIterationPK(_iterationId, _year);
    }

    @Override
    public void setPrimaryKey(ImpactIterationPK primaryKey) {
        setIterationId(primaryKey.iterationId);
        setYear(primaryKey.year);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ImpactIterationPK(_iterationId, _year);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ImpactIterationPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("iterationId", getIterationId());
        attributes.put("year", getYear());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long iterationId = (Long) attributes.get("iterationId");

        if (iterationId != null) {
            setIterationId(iterationId);
        }

        Integer year = (Integer) attributes.get("year");

        if (year != null) {
            setYear(year);
        }
    }

    @Override
    public long getIterationId() {
        return _iterationId;
    }

    @Override
    public void setIterationId(long iterationId) {
        _iterationId = iterationId;

        if (_impactIterationRemoteModel != null) {
            try {
                Class<?> clazz = _impactIterationRemoteModel.getClass();

                Method method = clazz.getMethod("setIterationId", long.class);

                method.invoke(_impactIterationRemoteModel, iterationId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getYear() {
        return _year;
    }

    @Override
    public void setYear(int year) {
        _year = year;

        if (_impactIterationRemoteModel != null) {
            try {
                Class<?> clazz = _impactIterationRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", int.class);

                method.invoke(_impactIterationRemoteModel, year);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImpactIterationRemoteModel() {
        return _impactIterationRemoteModel;
    }

    public void setImpactIterationRemoteModel(
        BaseModel<?> impactIterationRemoteModel) {
        _impactIterationRemoteModel = impactIterationRemoteModel;
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

        Class<?> remoteModelClass = _impactIterationRemoteModel.getClass();

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

        Object returnValue = method.invoke(_impactIterationRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImpactIterationLocalServiceUtil.addImpactIteration(this);
        } else {
            ImpactIterationLocalServiceUtil.updateImpactIteration(this);
        }
    }

    @Override
    public ImpactIteration toEscapedModel() {
        return (ImpactIteration) ProxyUtil.newProxyInstance(ImpactIteration.class.getClassLoader(),
            new Class[] { ImpactIteration.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImpactIterationClp clone = new ImpactIterationClp();

        clone.setIterationId(getIterationId());
        clone.setYear(getYear());

        return clone;
    }

    @Override
    public int compareTo(ImpactIteration impactIteration) {
        ImpactIterationPK primaryKey = impactIteration.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImpactIterationClp)) {
            return false;
        }

        ImpactIterationClp impactIteration = (ImpactIterationClp) obj;

        ImpactIterationPK primaryKey = impactIteration.getPrimaryKey();

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

        sb.append("{iterationId=");
        sb.append(getIterationId());
        sb.append(", year=");
        sb.append(getYear());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ImpactIteration");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>iterationId</column-name><column-value><![CDATA[");
        sb.append(getIterationId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
