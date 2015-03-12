package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ImpactDefaultSeriesLocalServiceUtil;
import com.ext.portlet.service.persistence.ImpactDefaultSeriesPK;

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


public class ImpactDefaultSeriesClp extends BaseModelImpl<ImpactDefaultSeries>
    implements ImpactDefaultSeries {
    private long _seriesId;
    private String _name;
    private String _description;
    private long _focusAreaId;
    private boolean _visible;
    private boolean _editable;
    private BaseModel<?> _impactDefaultSeriesRemoteModel;

    public ImpactDefaultSeriesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImpactDefaultSeries.class;
    }

    @Override
    public String getModelClassName() {
        return ImpactDefaultSeries.class.getName();
    }

    @Override
    public ImpactDefaultSeriesPK getPrimaryKey() {
        return new ImpactDefaultSeriesPK(_seriesId, _name);
    }

    @Override
    public void setPrimaryKey(ImpactDefaultSeriesPK primaryKey) {
        setSeriesId(primaryKey.seriesId);
        setName(primaryKey.name);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ImpactDefaultSeriesPK(_seriesId, _name);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ImpactDefaultSeriesPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("seriesId", getSeriesId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("focusAreaId", getFocusAreaId());
        attributes.put("visible", getVisible());
        attributes.put("editable", getEditable());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long seriesId = (Long) attributes.get("seriesId");

        if (seriesId != null) {
            setSeriesId(seriesId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Long focusAreaId = (Long) attributes.get("focusAreaId");

        if (focusAreaId != null) {
            setFocusAreaId(focusAreaId);
        }

        Boolean visible = (Boolean) attributes.get("visible");

        if (visible != null) {
            setVisible(visible);
        }

        Boolean editable = (Boolean) attributes.get("editable");

        if (editable != null) {
            setEditable(editable);
        }
    }

    @Override
    public long getSeriesId() {
        return _seriesId;
    }

    @Override
    public void setSeriesId(long seriesId) {
        _seriesId = seriesId;

        if (_impactDefaultSeriesRemoteModel != null) {
            try {
                Class<?> clazz = _impactDefaultSeriesRemoteModel.getClass();

                Method method = clazz.getMethod("setSeriesId", long.class);

                method.invoke(_impactDefaultSeriesRemoteModel, seriesId);
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

        if (_impactDefaultSeriesRemoteModel != null) {
            try {
                Class<?> clazz = _impactDefaultSeriesRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_impactDefaultSeriesRemoteModel, name);
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

        if (_impactDefaultSeriesRemoteModel != null) {
            try {
                Class<?> clazz = _impactDefaultSeriesRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_impactDefaultSeriesRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getFocusAreaId() {
        return _focusAreaId;
    }

    @Override
    public void setFocusAreaId(long focusAreaId) {
        _focusAreaId = focusAreaId;

        if (_impactDefaultSeriesRemoteModel != null) {
            try {
                Class<?> clazz = _impactDefaultSeriesRemoteModel.getClass();

                Method method = clazz.getMethod("setFocusAreaId", long.class);

                method.invoke(_impactDefaultSeriesRemoteModel, focusAreaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getVisible() {
        return _visible;
    }

    @Override
    public boolean isVisible() {
        return _visible;
    }

    @Override
    public void setVisible(boolean visible) {
        _visible = visible;

        if (_impactDefaultSeriesRemoteModel != null) {
            try {
                Class<?> clazz = _impactDefaultSeriesRemoteModel.getClass();

                Method method = clazz.getMethod("setVisible", boolean.class);

                method.invoke(_impactDefaultSeriesRemoteModel, visible);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getEditable() {
        return _editable;
    }

    @Override
    public boolean isEditable() {
        return _editable;
    }

    @Override
    public void setEditable(boolean editable) {
        _editable = editable;

        if (_impactDefaultSeriesRemoteModel != null) {
            try {
                Class<?> clazz = _impactDefaultSeriesRemoteModel.getClass();

                Method method = clazz.getMethod("setEditable", boolean.class);

                method.invoke(_impactDefaultSeriesRemoteModel, editable);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImpactDefaultSeriesRemoteModel() {
        return _impactDefaultSeriesRemoteModel;
    }

    public void setImpactDefaultSeriesRemoteModel(
        BaseModel<?> impactDefaultSeriesRemoteModel) {
        _impactDefaultSeriesRemoteModel = impactDefaultSeriesRemoteModel;
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

        Class<?> remoteModelClass = _impactDefaultSeriesRemoteModel.getClass();

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

        Object returnValue = method.invoke(_impactDefaultSeriesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImpactDefaultSeriesLocalServiceUtil.addImpactDefaultSeries(this);
        } else {
            ImpactDefaultSeriesLocalServiceUtil.updateImpactDefaultSeries(this);
        }
    }

    @Override
    public ImpactDefaultSeries toEscapedModel() {
        return (ImpactDefaultSeries) ProxyUtil.newProxyInstance(ImpactDefaultSeries.class.getClassLoader(),
            new Class[] { ImpactDefaultSeries.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImpactDefaultSeriesClp clone = new ImpactDefaultSeriesClp();

        clone.setSeriesId(getSeriesId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setFocusAreaId(getFocusAreaId());
        clone.setVisible(getVisible());
        clone.setEditable(getEditable());

        return clone;
    }

    @Override
    public int compareTo(ImpactDefaultSeries impactDefaultSeries) {
        ImpactDefaultSeriesPK primaryKey = impactDefaultSeries.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImpactDefaultSeriesClp)) {
            return false;
        }

        ImpactDefaultSeriesClp impactDefaultSeries = (ImpactDefaultSeriesClp) obj;

        ImpactDefaultSeriesPK primaryKey = impactDefaultSeries.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{seriesId=");
        sb.append(getSeriesId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", focusAreaId=");
        sb.append(getFocusAreaId());
        sb.append(", visible=");
        sb.append(getVisible());
        sb.append(", editable=");
        sb.append(getEditable());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ImpactDefaultSeries");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>seriesId</column-name><column-value><![CDATA[");
        sb.append(getSeriesId());
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
            "<column><column-name>focusAreaId</column-name><column-value><![CDATA[");
        sb.append(getFocusAreaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visible</column-name><column-value><![CDATA[");
        sb.append(getVisible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>editable</column-name><column-value><![CDATA[");
        sb.append(getEditable());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
