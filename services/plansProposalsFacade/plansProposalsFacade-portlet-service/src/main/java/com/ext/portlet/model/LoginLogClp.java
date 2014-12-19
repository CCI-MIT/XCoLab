package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.LoginLogLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class LoginLogClp extends BaseModelImpl<LoginLog> implements LoginLog {
    private long _pk;
    private long _userId;
    private String _userUuid;
    private Date _createDate;
    private String _ipAddress;
    private String _city;
    private String _country;
    private String _entryUrl;
    private BaseModel<?> _loginLogRemoteModel;

    public LoginLogClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return LoginLog.class;
    }

    @Override
    public String getModelClassName() {
        return LoginLog.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _pk;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setPk(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _pk;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("pk", getPk());
        attributes.put("userId", getUserId());
        attributes.put("createDate", getCreateDate());
        attributes.put("ipAddress", getIpAddress());
        attributes.put("city", getCity());
        attributes.put("country", getCountry());
        attributes.put("entryUrl", getEntryUrl());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long pk = (Long) attributes.get("pk");

        if (pk != null) {
            setPk(pk);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        String ipAddress = (String) attributes.get("ipAddress");

        if (ipAddress != null) {
            setIpAddress(ipAddress);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String entryUrl = (String) attributes.get("entryUrl");

        if (entryUrl != null) {
            setEntryUrl(entryUrl);
        }
    }

    @Override
    public long getPk() {
        return _pk;
    }

    @Override
    public void setPk(long pk) {
        _pk = pk;

        if (_loginLogRemoteModel != null) {
            try {
                Class<?> clazz = _loginLogRemoteModel.getClass();

                Method method = clazz.getMethod("setPk", long.class);

                method.invoke(_loginLogRemoteModel, pk);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_loginLogRemoteModel != null) {
            try {
                Class<?> clazz = _loginLogRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_loginLogRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    @Override
    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    @Override
    public Date getCreateDate() {
        return _createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        _createDate = createDate;

        if (_loginLogRemoteModel != null) {
            try {
                Class<?> clazz = _loginLogRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_loginLogRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIpAddress() {
        return _ipAddress;
    }

    @Override
    public void setIpAddress(String ipAddress) {
        _ipAddress = ipAddress;

        if (_loginLogRemoteModel != null) {
            try {
                Class<?> clazz = _loginLogRemoteModel.getClass();

                Method method = clazz.getMethod("setIpAddress", String.class);

                method.invoke(_loginLogRemoteModel, ipAddress);
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

        if (_loginLogRemoteModel != null) {
            try {
                Class<?> clazz = _loginLogRemoteModel.getClass();

                Method method = clazz.getMethod("setCity", String.class);

                method.invoke(_loginLogRemoteModel, city);
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

        if (_loginLogRemoteModel != null) {
            try {
                Class<?> clazz = _loginLogRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_loginLogRemoteModel, country);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEntryUrl() {
        return _entryUrl;
    }

    @Override
    public void setEntryUrl(String entryUrl) {
        _entryUrl = entryUrl;

        if (_loginLogRemoteModel != null) {
            try {
                Class<?> clazz = _loginLogRemoteModel.getClass();

                Method method = clazz.getMethod("setEntryUrl", String.class);

                method.invoke(_loginLogRemoteModel, entryUrl);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getLoginLogRemoteModel() {
        return _loginLogRemoteModel;
    }

    public void setLoginLogRemoteModel(BaseModel<?> loginLogRemoteModel) {
        _loginLogRemoteModel = loginLogRemoteModel;
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

        Class<?> remoteModelClass = _loginLogRemoteModel.getClass();

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

        Object returnValue = method.invoke(_loginLogRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            LoginLogLocalServiceUtil.addLoginLog(this);
        } else {
            LoginLogLocalServiceUtil.updateLoginLog(this);
        }
    }

    @Override
    public LoginLog toEscapedModel() {
        return (LoginLog) ProxyUtil.newProxyInstance(LoginLog.class.getClassLoader(),
            new Class[] { LoginLog.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        LoginLogClp clone = new LoginLogClp();

        clone.setPk(getPk());
        clone.setUserId(getUserId());
        clone.setCreateDate(getCreateDate());
        clone.setIpAddress(getIpAddress());
        clone.setCity(getCity());
        clone.setCountry(getCountry());
        clone.setEntryUrl(getEntryUrl());

        return clone;
    }

    @Override
    public int compareTo(LoginLog loginLog) {
        long primaryKey = loginLog.getPrimaryKey();

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

        if (!(obj instanceof LoginLogClp)) {
            return false;
        }

        LoginLogClp loginLog = (LoginLogClp) obj;

        long primaryKey = loginLog.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{pk=");
        sb.append(getPk());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", ipAddress=");
        sb.append(getIpAddress());
        sb.append(", city=");
        sb.append(getCity());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", entryUrl=");
        sb.append(getEntryUrl());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.LoginLog");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>pk</column-name><column-value><![CDATA[");
        sb.append(getPk());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ipAddress</column-name><column-value><![CDATA[");
        sb.append(getIpAddress());
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
            "<column><column-name>entryUrl</column-name><column-value><![CDATA[");
        sb.append(getEntryUrl());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
