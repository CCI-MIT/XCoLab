package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ImpactTemplateSeriesLocalServiceUtil;

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


public class ImpactTemplateSeriesClp extends BaseModelImpl<ImpactTemplateSeries>
    implements ImpactTemplateSeries {
    private long _seriesId;
    private long _iterationId;
    private String _name;
    private BaseModel<?> _impactTemplateSeriesRemoteModel;

    public ImpactTemplateSeriesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImpactTemplateSeries.class;
    }

    @Override
    public String getModelClassName() {
        return ImpactTemplateSeries.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _seriesId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setSeriesId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _seriesId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("seriesId", getSeriesId());
        attributes.put("iterationId", getIterationId());
        attributes.put("name", getName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long seriesId = (Long) attributes.get("seriesId");

        if (seriesId != null) {
            setSeriesId(seriesId);
        }

        Long iterationId = (Long) attributes.get("iterationId");

        if (iterationId != null) {
            setIterationId(iterationId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }
    }

    @Override
    public long getSeriesId() {
        return _seriesId;
    }

    @Override
    public void setSeriesId(long seriesId) {
        _seriesId = seriesId;

        if (_impactTemplateSeriesRemoteModel != null) {
            try {
                Class<?> clazz = _impactTemplateSeriesRemoteModel.getClass();

                Method method = clazz.getMethod("setSeriesId", long.class);

                method.invoke(_impactTemplateSeriesRemoteModel, seriesId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getIterationId() {
        return _iterationId;
    }

    @Override
    public void setIterationId(long iterationId) {
        _iterationId = iterationId;

        if (_impactTemplateSeriesRemoteModel != null) {
            try {
                Class<?> clazz = _impactTemplateSeriesRemoteModel.getClass();

                Method method = clazz.getMethod("setIterationId", long.class);

                method.invoke(_impactTemplateSeriesRemoteModel, iterationId);
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

        if (_impactTemplateSeriesRemoteModel != null) {
            try {
                Class<?> clazz = _impactTemplateSeriesRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_impactTemplateSeriesRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImpactTemplateSeriesRemoteModel() {
        return _impactTemplateSeriesRemoteModel;
    }

    public void setImpactTemplateSeriesRemoteModel(
        BaseModel<?> impactTemplateSeriesRemoteModel) {
        _impactTemplateSeriesRemoteModel = impactTemplateSeriesRemoteModel;
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

        Class<?> remoteModelClass = _impactTemplateSeriesRemoteModel.getClass();

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

        Object returnValue = method.invoke(_impactTemplateSeriesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImpactTemplateSeriesLocalServiceUtil.addImpactTemplateSeries(this);
        } else {
            ImpactTemplateSeriesLocalServiceUtil.updateImpactTemplateSeries(this);
        }
    }

    @Override
    public ImpactTemplateSeries toEscapedModel() {
        return (ImpactTemplateSeries) ProxyUtil.newProxyInstance(ImpactTemplateSeries.class.getClassLoader(),
            new Class[] { ImpactTemplateSeries.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImpactTemplateSeriesClp clone = new ImpactTemplateSeriesClp();

        clone.setSeriesId(getSeriesId());
        clone.setIterationId(getIterationId());
        clone.setName(getName());

        return clone;
    }

    @Override
    public int compareTo(ImpactTemplateSeries impactTemplateSeries) {
        long primaryKey = impactTemplateSeries.getPrimaryKey();

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

        if (!(obj instanceof ImpactTemplateSeriesClp)) {
            return false;
        }

        ImpactTemplateSeriesClp impactTemplateSeries = (ImpactTemplateSeriesClp) obj;

        long primaryKey = impactTemplateSeries.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{seriesId=");
        sb.append(getSeriesId());
        sb.append(", iterationId=");
        sb.append(getIterationId());
        sb.append(", name=");
        sb.append(getName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ImpactTemplateSeries");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>seriesId</column-name><column-value><![CDATA[");
        sb.append(getSeriesId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>iterationId</column-name><column-value><![CDATA[");
        sb.append(getIterationId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
