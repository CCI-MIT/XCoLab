package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.TrackedVisitLocalServiceUtil;

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


public class TrackedVisitClp extends BaseModelImpl<TrackedVisit>
    implements TrackedVisit {
    private long _id;
    private String _uuid;
    private String _ip;
    private String _city;
    private String _country;
    private String _url;
    private String _browser;
    private String _headers;
    private String _referer;
    private Date _createDate;
    private BaseModel<?> _trackedVisitRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public TrackedVisitClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return TrackedVisit.class;
    }

    @Override
    public String getModelClassName() {
        return TrackedVisit.class.getName();
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
        attributes.put("uuid", getUuid());
        attributes.put("ip", getIp());
        attributes.put("city", getCity());
        attributes.put("country", getCountry());
        attributes.put("url", getUrl());
        attributes.put("browser", getBrowser());
        attributes.put("headers", getHeaders());
        attributes.put("referer", getReferer());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
        }

        String ip = (String) attributes.get("ip");

        if (ip != null) {
            setIp(ip);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String url = (String) attributes.get("url");

        if (url != null) {
            setUrl(url);
        }

        String browser = (String) attributes.get("browser");

        if (browser != null) {
            setBrowser(browser);
        }

        String headers = (String) attributes.get("headers");

        if (headers != null) {
            setHeaders(headers);
        }

        String referer = (String) attributes.get("referer");

        if (referer != null) {
            setReferer(referer);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_trackedVisitRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_trackedVisitRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUuid() {
        return _uuid;
    }

    @Override
    public void setUuid(String uuid) {
        _uuid = uuid;

        if (_trackedVisitRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitRemoteModel.getClass();

                Method method = clazz.getMethod("setUuid", String.class);

                method.invoke(_trackedVisitRemoteModel, uuid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIp() {
        return _ip;
    }

    @Override
    public void setIp(String ip) {
        _ip = ip;

        if (_trackedVisitRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitRemoteModel.getClass();

                Method method = clazz.getMethod("setIp", String.class);

                method.invoke(_trackedVisitRemoteModel, ip);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCity() {
        return _city;
    }

    @Override
    public void setCity(String city) {
        _city = city;

        if (_trackedVisitRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitRemoteModel.getClass();

                Method method = clazz.getMethod("setCity", String.class);

                method.invoke(_trackedVisitRemoteModel, city);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCountry() {
        return _country;
    }

    @Override
    public void setCountry(String country) {
        _country = country;

        if (_trackedVisitRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_trackedVisitRemoteModel, country);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUrl() {
        return _url;
    }

    @Override
    public void setUrl(String url) {
        _url = url;

        if (_trackedVisitRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitRemoteModel.getClass();

                Method method = clazz.getMethod("setUrl", String.class);

                method.invoke(_trackedVisitRemoteModel, url);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrowser() {
        return _browser;
    }

    @Override
    public void setBrowser(String browser) {
        _browser = browser;

        if (_trackedVisitRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitRemoteModel.getClass();

                Method method = clazz.getMethod("setBrowser", String.class);

                method.invoke(_trackedVisitRemoteModel, browser);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getHeaders() {
        return _headers;
    }

    @Override
    public void setHeaders(String headers) {
        _headers = headers;

        if (_trackedVisitRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitRemoteModel.getClass();

                Method method = clazz.getMethod("setHeaders", String.class);

                method.invoke(_trackedVisitRemoteModel, headers);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReferer() {
        return _referer;
    }

    @Override
    public void setReferer(String referer) {
        _referer = referer;

        if (_trackedVisitRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitRemoteModel.getClass();

                Method method = clazz.getMethod("setReferer", String.class);

                method.invoke(_trackedVisitRemoteModel, referer);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreateDate() {
        return _createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        _createDate = createDate;

        if (_trackedVisitRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_trackedVisitRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getTrackedVisitRemoteModel() {
        return _trackedVisitRemoteModel;
    }

    public void setTrackedVisitRemoteModel(BaseModel<?> trackedVisitRemoteModel) {
        _trackedVisitRemoteModel = trackedVisitRemoteModel;
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

        Class<?> remoteModelClass = _trackedVisitRemoteModel.getClass();

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

        Object returnValue = method.invoke(_trackedVisitRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            TrackedVisitLocalServiceUtil.addTrackedVisit(this);
        } else {
            TrackedVisitLocalServiceUtil.updateTrackedVisit(this);
        }
    }

    @Override
    public TrackedVisit toEscapedModel() {
        return (TrackedVisit) ProxyUtil.newProxyInstance(TrackedVisit.class.getClassLoader(),
            new Class[] { TrackedVisit.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        TrackedVisitClp clone = new TrackedVisitClp();

        clone.setId(getId());
        clone.setUuid(getUuid());
        clone.setIp(getIp());
        clone.setCity(getCity());
        clone.setCountry(getCountry());
        clone.setUrl(getUrl());
        clone.setBrowser(getBrowser());
        clone.setHeaders(getHeaders());
        clone.setReferer(getReferer());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    @Override
    public int compareTo(TrackedVisit trackedVisit) {
        long primaryKey = trackedVisit.getPrimaryKey();

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

        if (!(obj instanceof TrackedVisitClp)) {
            return false;
        }

        TrackedVisitClp trackedVisit = (TrackedVisitClp) obj;

        long primaryKey = trackedVisit.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", uuid=");
        sb.append(getUuid());
        sb.append(", ip=");
        sb.append(getIp());
        sb.append(", city=");
        sb.append(getCity());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", url=");
        sb.append(getUrl());
        sb.append(", browser=");
        sb.append(getBrowser());
        sb.append(", headers=");
        sb.append(getHeaders());
        sb.append(", referer=");
        sb.append(getReferer());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.TrackedVisit");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uuid</column-name><column-value><![CDATA[");
        sb.append(getUuid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ip</column-name><column-value><![CDATA[");
        sb.append(getIp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>city</column-name><column-value><![CDATA[");
        sb.append(getCity());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>url</column-name><column-value><![CDATA[");
        sb.append(getUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>browser</column-name><column-value><![CDATA[");
        sb.append(getBrowser());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>headers</column-name><column-value><![CDATA[");
        sb.append(getHeaders());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>referer</column-name><column-value><![CDATA[");
        sb.append(getReferer());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
