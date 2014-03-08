package com.ext.portlet.model;

import com.ext.portlet.service.BalloonUserTrackingLocalServiceUtil;
import com.ext.portlet.service.ClpSerializer;

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


public class BalloonUserTrackingClp extends BaseModelImpl<BalloonUserTracking>
    implements BalloonUserTracking {
    private String _uuid;
    private String _email;
    private String _parent;
    private String _ip;
    private Date _createDate;
    private Date _registrationDate;
    private Date _formFiledDate;
    private long _userId;
    private String _userUuid;
    private long _balloonTextId;
    private String _referrer;
    private double _latitude;
    private double _longitude;
    private String _city;
    private String _country;
    private String _extraData;
    private String _balloonLinkUuid;
    private String _balloonLinkContext;
    private String _userAgent;
    private BaseModel<?> _balloonUserTrackingRemoteModel;

    public BalloonUserTrackingClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return BalloonUserTracking.class;
    }

    @Override
    public String getModelClassName() {
        return BalloonUserTracking.class.getName();
    }

    @Override
    public String getPrimaryKey() {
        return _uuid;
    }

    @Override
    public void setPrimaryKey(String primaryKey) {
        setUuid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _uuid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((String) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("uuid", getUuid());
        attributes.put("email", getEmail());
        attributes.put("parent", getParent());
        attributes.put("ip", getIp());
        attributes.put("createDate", getCreateDate());
        attributes.put("registrationDate", getRegistrationDate());
        attributes.put("formFiledDate", getFormFiledDate());
        attributes.put("userId", getUserId());
        attributes.put("balloonTextId", getBalloonTextId());
        attributes.put("referrer", getReferrer());
        attributes.put("latitude", getLatitude());
        attributes.put("longitude", getLongitude());
        attributes.put("city", getCity());
        attributes.put("country", getCountry());
        attributes.put("extraData", getExtraData());
        attributes.put("balloonLinkUuid", getBalloonLinkUuid());
        attributes.put("balloonLinkContext", getBalloonLinkContext());
        attributes.put("userAgent", getUserAgent());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
        }

        String email = (String) attributes.get("email");

        if (email != null) {
            setEmail(email);
        }

        String parent = (String) attributes.get("parent");

        if (parent != null) {
            setParent(parent);
        }

        String ip = (String) attributes.get("ip");

        if (ip != null) {
            setIp(ip);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date registrationDate = (Date) attributes.get("registrationDate");

        if (registrationDate != null) {
            setRegistrationDate(registrationDate);
        }

        Date formFiledDate = (Date) attributes.get("formFiledDate");

        if (formFiledDate != null) {
            setFormFiledDate(formFiledDate);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long balloonTextId = (Long) attributes.get("balloonTextId");

        if (balloonTextId != null) {
            setBalloonTextId(balloonTextId);
        }

        String referrer = (String) attributes.get("referrer");

        if (referrer != null) {
            setReferrer(referrer);
        }

        Double latitude = (Double) attributes.get("latitude");

        if (latitude != null) {
            setLatitude(latitude);
        }

        Double longitude = (Double) attributes.get("longitude");

        if (longitude != null) {
            setLongitude(longitude);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String extraData = (String) attributes.get("extraData");

        if (extraData != null) {
            setExtraData(extraData);
        }

        String balloonLinkUuid = (String) attributes.get("balloonLinkUuid");

        if (balloonLinkUuid != null) {
            setBalloonLinkUuid(balloonLinkUuid);
        }

        String balloonLinkContext = (String) attributes.get(
                "balloonLinkContext");

        if (balloonLinkContext != null) {
            setBalloonLinkContext(balloonLinkContext);
        }

        String userAgent = (String) attributes.get("userAgent");

        if (userAgent != null) {
            setUserAgent(userAgent);
        }
    }

    @Override
    public String getUuid() {
        return _uuid;
    }

    @Override
    public void setUuid(String uuid) {
        _uuid = uuid;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setUuid", String.class);

                method.invoke(_balloonUserTrackingRemoteModel, uuid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEmail() {
        return _email;
    }

    @Override
    public void setEmail(String email) {
        _email = email;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setEmail", String.class);

                method.invoke(_balloonUserTrackingRemoteModel, email);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParent() {
        return _parent;
    }

    @Override
    public void setParent(String parent) {
        _parent = parent;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setParent", String.class);

                method.invoke(_balloonUserTrackingRemoteModel, parent);
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

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setIp", String.class);

                method.invoke(_balloonUserTrackingRemoteModel, ip);
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

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_balloonUserTrackingRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRegistrationDate() {
        return _registrationDate;
    }

    @Override
    public void setRegistrationDate(Date registrationDate) {
        _registrationDate = registrationDate;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setRegistrationDate",
                        Date.class);

                method.invoke(_balloonUserTrackingRemoteModel, registrationDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getFormFiledDate() {
        return _formFiledDate;
    }

    @Override
    public void setFormFiledDate(Date formFiledDate) {
        _formFiledDate = formFiledDate;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setFormFiledDate", Date.class);

                method.invoke(_balloonUserTrackingRemoteModel, formFiledDate);
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

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_balloonUserTrackingRemoteModel, userId);
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
    public long getBalloonTextId() {
        return _balloonTextId;
    }

    @Override
    public void setBalloonTextId(long balloonTextId) {
        _balloonTextId = balloonTextId;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setBalloonTextId", long.class);

                method.invoke(_balloonUserTrackingRemoteModel, balloonTextId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReferrer() {
        return _referrer;
    }

    @Override
    public void setReferrer(String referrer) {
        _referrer = referrer;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setReferrer", String.class);

                method.invoke(_balloonUserTrackingRemoteModel, referrer);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getLatitude() {
        return _latitude;
    }

    @Override
    public void setLatitude(double latitude) {
        _latitude = latitude;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setLatitude", double.class);

                method.invoke(_balloonUserTrackingRemoteModel, latitude);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getLongitude() {
        return _longitude;
    }

    @Override
    public void setLongitude(double longitude) {
        _longitude = longitude;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setLongitude", double.class);

                method.invoke(_balloonUserTrackingRemoteModel, longitude);
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

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setCity", String.class);

                method.invoke(_balloonUserTrackingRemoteModel, city);
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

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_balloonUserTrackingRemoteModel, country);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getExtraData() {
        return _extraData;
    }

    @Override
    public void setExtraData(String extraData) {
        _extraData = extraData;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setExtraData", String.class);

                method.invoke(_balloonUserTrackingRemoteModel, extraData);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBalloonLinkUuid() {
        return _balloonLinkUuid;
    }

    @Override
    public void setBalloonLinkUuid(String balloonLinkUuid) {
        _balloonLinkUuid = balloonLinkUuid;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setBalloonLinkUuid",
                        String.class);

                method.invoke(_balloonUserTrackingRemoteModel, balloonLinkUuid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBalloonLinkContext() {
        return _balloonLinkContext;
    }

    @Override
    public void setBalloonLinkContext(String balloonLinkContext) {
        _balloonLinkContext = balloonLinkContext;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setBalloonLinkContext",
                        String.class);

                method.invoke(_balloonUserTrackingRemoteModel,
                    balloonLinkContext);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserAgent() {
        return _userAgent;
    }

    @Override
    public void setUserAgent(String userAgent) {
        _userAgent = userAgent;

        if (_balloonUserTrackingRemoteModel != null) {
            try {
                Class<?> clazz = _balloonUserTrackingRemoteModel.getClass();

                Method method = clazz.getMethod("setUserAgent", String.class);

                method.invoke(_balloonUserTrackingRemoteModel, userAgent);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getBalloonUserTrackingRemoteModel() {
        return _balloonUserTrackingRemoteModel;
    }

    public void setBalloonUserTrackingRemoteModel(
        BaseModel<?> balloonUserTrackingRemoteModel) {
        _balloonUserTrackingRemoteModel = balloonUserTrackingRemoteModel;
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

        Class<?> remoteModelClass = _balloonUserTrackingRemoteModel.getClass();

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

        Object returnValue = method.invoke(_balloonUserTrackingRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            BalloonUserTrackingLocalServiceUtil.addBalloonUserTracking(this);
        } else {
            BalloonUserTrackingLocalServiceUtil.updateBalloonUserTracking(this);
        }
    }

    @Override
    public BalloonUserTracking toEscapedModel() {
        return (BalloonUserTracking) ProxyUtil.newProxyInstance(BalloonUserTracking.class.getClassLoader(),
            new Class[] { BalloonUserTracking.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        BalloonUserTrackingClp clone = new BalloonUserTrackingClp();

        clone.setUuid(getUuid());
        clone.setEmail(getEmail());
        clone.setParent(getParent());
        clone.setIp(getIp());
        clone.setCreateDate(getCreateDate());
        clone.setRegistrationDate(getRegistrationDate());
        clone.setFormFiledDate(getFormFiledDate());
        clone.setUserId(getUserId());
        clone.setBalloonTextId(getBalloonTextId());
        clone.setReferrer(getReferrer());
        clone.setLatitude(getLatitude());
        clone.setLongitude(getLongitude());
        clone.setCity(getCity());
        clone.setCountry(getCountry());
        clone.setExtraData(getExtraData());
        clone.setBalloonLinkUuid(getBalloonLinkUuid());
        clone.setBalloonLinkContext(getBalloonLinkContext());
        clone.setUserAgent(getUserAgent());

        return clone;
    }

    @Override
    public int compareTo(BalloonUserTracking balloonUserTracking) {
        String primaryKey = balloonUserTracking.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BalloonUserTrackingClp)) {
            return false;
        }

        BalloonUserTrackingClp balloonUserTracking = (BalloonUserTrackingClp) obj;

        String primaryKey = balloonUserTracking.getPrimaryKey();

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
        StringBundler sb = new StringBundler(37);

        sb.append("{uuid=");
        sb.append(getUuid());
        sb.append(", email=");
        sb.append(getEmail());
        sb.append(", parent=");
        sb.append(getParent());
        sb.append(", ip=");
        sb.append(getIp());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", registrationDate=");
        sb.append(getRegistrationDate());
        sb.append(", formFiledDate=");
        sb.append(getFormFiledDate());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", balloonTextId=");
        sb.append(getBalloonTextId());
        sb.append(", referrer=");
        sb.append(getReferrer());
        sb.append(", latitude=");
        sb.append(getLatitude());
        sb.append(", longitude=");
        sb.append(getLongitude());
        sb.append(", city=");
        sb.append(getCity());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", extraData=");
        sb.append(getExtraData());
        sb.append(", balloonLinkUuid=");
        sb.append(getBalloonLinkUuid());
        sb.append(", balloonLinkContext=");
        sb.append(getBalloonLinkContext());
        sb.append(", userAgent=");
        sb.append(getUserAgent());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(58);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.BalloonUserTracking");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>uuid</column-name><column-value><![CDATA[");
        sb.append(getUuid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>email</column-name><column-value><![CDATA[");
        sb.append(getEmail());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parent</column-name><column-value><![CDATA[");
        sb.append(getParent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ip</column-name><column-value><![CDATA[");
        sb.append(getIp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>registrationDate</column-name><column-value><![CDATA[");
        sb.append(getRegistrationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formFiledDate</column-name><column-value><![CDATA[");
        sb.append(getFormFiledDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>balloonTextId</column-name><column-value><![CDATA[");
        sb.append(getBalloonTextId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>referrer</column-name><column-value><![CDATA[");
        sb.append(getReferrer());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>latitude</column-name><column-value><![CDATA[");
        sb.append(getLatitude());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>longitude</column-name><column-value><![CDATA[");
        sb.append(getLongitude());
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
            "<column><column-name>extraData</column-name><column-value><![CDATA[");
        sb.append(getExtraData());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>balloonLinkUuid</column-name><column-value><![CDATA[");
        sb.append(getBalloonLinkUuid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>balloonLinkContext</column-name><column-value><![CDATA[");
        sb.append(getBalloonLinkContext());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userAgent</column-name><column-value><![CDATA[");
        sb.append(getUserAgent());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
