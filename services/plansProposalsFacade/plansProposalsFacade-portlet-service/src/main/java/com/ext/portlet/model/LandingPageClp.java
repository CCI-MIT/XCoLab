package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.LandingPageLocalServiceUtil;

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


public class LandingPageClp extends BaseModelImpl<LandingPage>
    implements LandingPage {
    private long _id;
    private String _baseUrl;
    private String _targetUrl;
    private Date _updated;
    private BaseModel<?> _landingPageRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public LandingPageClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return LandingPage.class;
    }

    @Override
    public String getModelClassName() {
        return LandingPage.class.getName();
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
        attributes.put("baseUrl", getBaseUrl());
        attributes.put("targetUrl", getTargetUrl());
        attributes.put("updated", getUpdated());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String baseUrl = (String) attributes.get("baseUrl");

        if (baseUrl != null) {
            setBaseUrl(baseUrl);
        }

        String targetUrl = (String) attributes.get("targetUrl");

        if (targetUrl != null) {
            setTargetUrl(targetUrl);
        }

        Date updated = (Date) attributes.get("updated");

        if (updated != null) {
            setUpdated(updated);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_landingPageRemoteModel != null) {
            try {
                Class<?> clazz = _landingPageRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_landingPageRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBaseUrl() {
        return _baseUrl;
    }

    @Override
    public void setBaseUrl(String baseUrl) {
        _baseUrl = baseUrl;

        if (_landingPageRemoteModel != null) {
            try {
                Class<?> clazz = _landingPageRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseUrl", String.class);

                method.invoke(_landingPageRemoteModel, baseUrl);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTargetUrl() {
        return _targetUrl;
    }

    @Override
    public void setTargetUrl(String targetUrl) {
        _targetUrl = targetUrl;

        if (_landingPageRemoteModel != null) {
            try {
                Class<?> clazz = _landingPageRemoteModel.getClass();

                Method method = clazz.getMethod("setTargetUrl", String.class);

                method.invoke(_landingPageRemoteModel, targetUrl);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getUpdated() {
        return _updated;
    }

    @Override
    public void setUpdated(Date updated) {
        _updated = updated;

        if (_landingPageRemoteModel != null) {
            try {
                Class<?> clazz = _landingPageRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdated", Date.class);

                method.invoke(_landingPageRemoteModel, updated);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getLandingPageRemoteModel() {
        return _landingPageRemoteModel;
    }

    public void setLandingPageRemoteModel(BaseModel<?> landingPageRemoteModel) {
        _landingPageRemoteModel = landingPageRemoteModel;
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

        Class<?> remoteModelClass = _landingPageRemoteModel.getClass();

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

        Object returnValue = method.invoke(_landingPageRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            LandingPageLocalServiceUtil.addLandingPage(this);
        } else {
            LandingPageLocalServiceUtil.updateLandingPage(this);
        }
    }

    @Override
    public LandingPage toEscapedModel() {
        return (LandingPage) ProxyUtil.newProxyInstance(LandingPage.class.getClassLoader(),
            new Class[] { LandingPage.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        LandingPageClp clone = new LandingPageClp();

        clone.setId(getId());
        clone.setBaseUrl(getBaseUrl());
        clone.setTargetUrl(getTargetUrl());
        clone.setUpdated(getUpdated());

        return clone;
    }

    @Override
    public int compareTo(LandingPage landingPage) {
        long primaryKey = landingPage.getPrimaryKey();

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

        if (!(obj instanceof LandingPageClp)) {
            return false;
        }

        LandingPageClp landingPage = (LandingPageClp) obj;

        long primaryKey = landingPage.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    public Class<?> getClpSerializerClass() {
        return _clpSerializerClass;
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", baseUrl=");
        sb.append(getBaseUrl());
        sb.append(", targetUrl=");
        sb.append(getTargetUrl());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.LandingPage");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseUrl</column-name><column-value><![CDATA[");
        sb.append(getBaseUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>targetUrl</column-name><column-value><![CDATA[");
        sb.append(getTargetUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
