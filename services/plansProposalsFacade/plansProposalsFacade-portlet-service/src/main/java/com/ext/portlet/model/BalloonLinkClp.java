package com.ext.portlet.model;

import com.ext.portlet.service.BalloonLinkLocalServiceUtil;
import com.ext.portlet.service.ClpSerializer;

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


public class BalloonLinkClp extends BaseModelImpl<BalloonLink>
    implements BalloonLink {
    private String _uuid;
    private String _targetUrl;
    private int _visits;
    private String _balloonUserUuid;
    private Date _createDate;
    private BaseModel<?> _balloonLinkRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public BalloonLinkClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return BalloonLink.class;
    }

    @Override
    public String getModelClassName() {
        return BalloonLink.class.getName();
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
        attributes.put("targetUrl", getTargetUrl());
        attributes.put("visits", getVisits());
        attributes.put("balloonUserUuid", getBalloonUserUuid());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
        }

        String targetUrl = (String) attributes.get("targetUrl");

        if (targetUrl != null) {
            setTargetUrl(targetUrl);
        }

        Integer visits = (Integer) attributes.get("visits");

        if (visits != null) {
            setVisits(visits);
        }

        String balloonUserUuid = (String) attributes.get("balloonUserUuid");

        if (balloonUserUuid != null) {
            setBalloonUserUuid(balloonUserUuid);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    @Override
    public String getUuid() {
        return _uuid;
    }

    @Override
    public void setUuid(String uuid) {
        _uuid = uuid;

        if (_balloonLinkRemoteModel != null) {
            try {
                Class<?> clazz = _balloonLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setUuid", String.class);

                method.invoke(_balloonLinkRemoteModel, uuid);
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

        if (_balloonLinkRemoteModel != null) {
            try {
                Class<?> clazz = _balloonLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setTargetUrl", String.class);

                method.invoke(_balloonLinkRemoteModel, targetUrl);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getVisits() {
        return _visits;
    }

    @Override
    public void setVisits(int visits) {
        _visits = visits;

        if (_balloonLinkRemoteModel != null) {
            try {
                Class<?> clazz = _balloonLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setVisits", int.class);

                method.invoke(_balloonLinkRemoteModel, visits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBalloonUserUuid() {
        return _balloonUserUuid;
    }

    @Override
    public void setBalloonUserUuid(String balloonUserUuid) {
        _balloonUserUuid = balloonUserUuid;

        if (_balloonLinkRemoteModel != null) {
            try {
                Class<?> clazz = _balloonLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setBalloonUserUuid",
                        String.class);

                method.invoke(_balloonLinkRemoteModel, balloonUserUuid);
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

        if (_balloonLinkRemoteModel != null) {
            try {
                Class<?> clazz = _balloonLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_balloonLinkRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getBalloonLinkRemoteModel() {
        return _balloonLinkRemoteModel;
    }

    public void setBalloonLinkRemoteModel(BaseModel<?> balloonLinkRemoteModel) {
        _balloonLinkRemoteModel = balloonLinkRemoteModel;
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

        Class<?> remoteModelClass = _balloonLinkRemoteModel.getClass();

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

        Object returnValue = method.invoke(_balloonLinkRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            BalloonLinkLocalServiceUtil.addBalloonLink(this);
        } else {
            BalloonLinkLocalServiceUtil.updateBalloonLink(this);
        }
    }

    @Override
    public BalloonLink toEscapedModel() {
        return (BalloonLink) ProxyUtil.newProxyInstance(BalloonLink.class.getClassLoader(),
            new Class[] { BalloonLink.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        BalloonLinkClp clone = new BalloonLinkClp();

        clone.setUuid(getUuid());
        clone.setTargetUrl(getTargetUrl());
        clone.setVisits(getVisits());
        clone.setBalloonUserUuid(getBalloonUserUuid());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    @Override
    public int compareTo(BalloonLink balloonLink) {
        String primaryKey = balloonLink.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BalloonLinkClp)) {
            return false;
        }

        BalloonLinkClp balloonLink = (BalloonLinkClp) obj;

        String primaryKey = balloonLink.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
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
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{uuid=");
        sb.append(getUuid());
        sb.append(", targetUrl=");
        sb.append(getTargetUrl());
        sb.append(", visits=");
        sb.append(getVisits());
        sb.append(", balloonUserUuid=");
        sb.append(getBalloonUserUuid());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.BalloonLink");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>uuid</column-name><column-value><![CDATA[");
        sb.append(getUuid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>targetUrl</column-name><column-value><![CDATA[");
        sb.append(getTargetUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visits</column-name><column-value><![CDATA[");
        sb.append(getVisits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>balloonUserUuid</column-name><column-value><![CDATA[");
        sb.append(getBalloonUserUuid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
