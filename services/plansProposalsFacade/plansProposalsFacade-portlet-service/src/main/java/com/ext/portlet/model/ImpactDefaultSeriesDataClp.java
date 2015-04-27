package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ImpactDefaultSeriesDataLocalServiceUtil;
import com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPK;

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


public class ImpactDefaultSeriesDataClp extends BaseModelImpl<ImpactDefaultSeriesData>
    implements ImpactDefaultSeriesData {
    private long _seriesId;
    private int _year;
    private double _value;
    private BaseModel<?> _impactDefaultSeriesDataRemoteModel;

    public ImpactDefaultSeriesDataClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImpactDefaultSeriesData.class;
    }

    @Override
    public String getModelClassName() {
        return ImpactDefaultSeriesData.class.getName();
    }

    @Override
    public ImpactDefaultSeriesDataPK getPrimaryKey() {
        return new ImpactDefaultSeriesDataPK(_seriesId, _year);
    }

    @Override
    public void setPrimaryKey(ImpactDefaultSeriesDataPK primaryKey) {
        setSeriesId(primaryKey.seriesId);
        setYear(primaryKey.year);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ImpactDefaultSeriesDataPK(_seriesId, _year);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ImpactDefaultSeriesDataPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("seriesId", getSeriesId());
        attributes.put("year", getYear());
        attributes.put("value", getValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long seriesId = (Long) attributes.get("seriesId");

        if (seriesId != null) {
            setSeriesId(seriesId);
        }

        Integer year = (Integer) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        Double value = (Double) attributes.get("value");

        if (value != null) {
            setValue(value);
        }
    }

    @Override
    public long getSeriesId() {
        return _seriesId;
    }

    @Override
    public void setSeriesId(long seriesId) {
        _seriesId = seriesId;

        if (_impactDefaultSeriesDataRemoteModel != null) {
            try {
                Class<?> clazz = _impactDefaultSeriesDataRemoteModel.getClass();

                Method method = clazz.getMethod("setSeriesId", long.class);

                method.invoke(_impactDefaultSeriesDataRemoteModel, seriesId);
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

        if (_impactDefaultSeriesDataRemoteModel != null) {
            try {
                Class<?> clazz = _impactDefaultSeriesDataRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", int.class);

                method.invoke(_impactDefaultSeriesDataRemoteModel, year);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getValue() {
        return _value;
    }

    @Override
    public void setValue(double value) {
        _value = value;

        if (_impactDefaultSeriesDataRemoteModel != null) {
            try {
                Class<?> clazz = _impactDefaultSeriesDataRemoteModel.getClass();

                Method method = clazz.getMethod("setValue", double.class);

                method.invoke(_impactDefaultSeriesDataRemoteModel, value);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImpactDefaultSeriesDataRemoteModel() {
        return _impactDefaultSeriesDataRemoteModel;
    }

    public void setImpactDefaultSeriesDataRemoteModel(
        BaseModel<?> impactDefaultSeriesDataRemoteModel) {
        _impactDefaultSeriesDataRemoteModel = impactDefaultSeriesDataRemoteModel;
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

        Class<?> remoteModelClass = _impactDefaultSeriesDataRemoteModel.getClass();

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

        Object returnValue = method.invoke(_impactDefaultSeriesDataRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImpactDefaultSeriesDataLocalServiceUtil.addImpactDefaultSeriesData(this);
        } else {
            ImpactDefaultSeriesDataLocalServiceUtil.updateImpactDefaultSeriesData(this);
        }
    }

    @Override
    public ImpactDefaultSeriesData toEscapedModel() {
        return (ImpactDefaultSeriesData) ProxyUtil.newProxyInstance(ImpactDefaultSeriesData.class.getClassLoader(),
            new Class[] { ImpactDefaultSeriesData.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImpactDefaultSeriesDataClp clone = new ImpactDefaultSeriesDataClp();

        clone.setSeriesId(getSeriesId());
        clone.setYear(getYear());
        clone.setValue(getValue());

        return clone;
    }

    @Override
    public int compareTo(ImpactDefaultSeriesData impactDefaultSeriesData) {
        ImpactDefaultSeriesDataPK primaryKey = impactDefaultSeriesData.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImpactDefaultSeriesDataClp)) {
            return false;
        }

        ImpactDefaultSeriesDataClp impactDefaultSeriesData = (ImpactDefaultSeriesDataClp) obj;

        ImpactDefaultSeriesDataPK primaryKey = impactDefaultSeriesData.getPrimaryKey();

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

        sb.append("{seriesId=");
        sb.append(getSeriesId());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", value=");
        sb.append(getValue());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ImpactDefaultSeriesData");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>seriesId</column-name><column-value><![CDATA[");
        sb.append(getSeriesId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>value</column-name><column-value><![CDATA[");
        sb.append(getValue());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
