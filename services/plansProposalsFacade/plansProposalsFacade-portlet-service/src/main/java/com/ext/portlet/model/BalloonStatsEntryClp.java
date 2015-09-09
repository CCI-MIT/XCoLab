package com.ext.portlet.model;

import com.ext.portlet.service.BalloonStatsEntryLocalServiceUtil;
import com.ext.portlet.service.ClpSerializer;

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


public class BalloonStatsEntryClp extends BaseModelImpl<BalloonStatsEntry>
    implements BalloonStatsEntry {
    private long _id;
    private long _firstContestId;
    private long _secondContestId;
    private int _choosenContest;
    private String _cookie;
    private String _ip;
    private String _extraData;
    private BaseModel<?> _balloonStatsEntryRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public BalloonStatsEntryClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return BalloonStatsEntry.class;
    }

    @Override
    public String getModelClassName() {
        return BalloonStatsEntry.class.getName();
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
        attributes.put("firstContestId", getFirstContestId());
        attributes.put("secondContestId", getSecondContestId());
        attributes.put("choosenContest", getChoosenContest());
        attributes.put("cookie", getCookie());
        attributes.put("ip", getIp());
        attributes.put("extraData", getExtraData());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long firstContestId = (Long) attributes.get("firstContestId");

        if (firstContestId != null) {
            setFirstContestId(firstContestId);
        }

        Long secondContestId = (Long) attributes.get("secondContestId");

        if (secondContestId != null) {
            setSecondContestId(secondContestId);
        }

        Integer choosenContest = (Integer) attributes.get("choosenContest");

        if (choosenContest != null) {
            setChoosenContest(choosenContest);
        }

        String cookie = (String) attributes.get("cookie");

        if (cookie != null) {
            setCookie(cookie);
        }

        String ip = (String) attributes.get("ip");

        if (ip != null) {
            setIp(ip);
        }

        String extraData = (String) attributes.get("extraData");

        if (extraData != null) {
            setExtraData(extraData);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_balloonStatsEntryRemoteModel != null) {
            try {
                Class<?> clazz = _balloonStatsEntryRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_balloonStatsEntryRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getFirstContestId() {
        return _firstContestId;
    }

    @Override
    public void setFirstContestId(long firstContestId) {
        _firstContestId = firstContestId;

        if (_balloonStatsEntryRemoteModel != null) {
            try {
                Class<?> clazz = _balloonStatsEntryRemoteModel.getClass();

                Method method = clazz.getMethod("setFirstContestId", long.class);

                method.invoke(_balloonStatsEntryRemoteModel, firstContestId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getSecondContestId() {
        return _secondContestId;
    }

    @Override
    public void setSecondContestId(long secondContestId) {
        _secondContestId = secondContestId;

        if (_balloonStatsEntryRemoteModel != null) {
            try {
                Class<?> clazz = _balloonStatsEntryRemoteModel.getClass();

                Method method = clazz.getMethod("setSecondContestId", long.class);

                method.invoke(_balloonStatsEntryRemoteModel, secondContestId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getChoosenContest() {
        return _choosenContest;
    }

    @Override
    public void setChoosenContest(int choosenContest) {
        _choosenContest = choosenContest;

        if (_balloonStatsEntryRemoteModel != null) {
            try {
                Class<?> clazz = _balloonStatsEntryRemoteModel.getClass();

                Method method = clazz.getMethod("setChoosenContest", int.class);

                method.invoke(_balloonStatsEntryRemoteModel, choosenContest);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCookie() {
        return _cookie;
    }

    @Override
    public void setCookie(String cookie) {
        _cookie = cookie;

        if (_balloonStatsEntryRemoteModel != null) {
            try {
                Class<?> clazz = _balloonStatsEntryRemoteModel.getClass();

                Method method = clazz.getMethod("setCookie", String.class);

                method.invoke(_balloonStatsEntryRemoteModel, cookie);
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

        if (_balloonStatsEntryRemoteModel != null) {
            try {
                Class<?> clazz = _balloonStatsEntryRemoteModel.getClass();

                Method method = clazz.getMethod("setIp", String.class);

                method.invoke(_balloonStatsEntryRemoteModel, ip);
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

        if (_balloonStatsEntryRemoteModel != null) {
            try {
                Class<?> clazz = _balloonStatsEntryRemoteModel.getClass();

                Method method = clazz.getMethod("setExtraData", String.class);

                method.invoke(_balloonStatsEntryRemoteModel, extraData);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getBalloonStatsEntryRemoteModel() {
        return _balloonStatsEntryRemoteModel;
    }

    public void setBalloonStatsEntryRemoteModel(
        BaseModel<?> balloonStatsEntryRemoteModel) {
        _balloonStatsEntryRemoteModel = balloonStatsEntryRemoteModel;
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

        Class<?> remoteModelClass = _balloonStatsEntryRemoteModel.getClass();

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

        Object returnValue = method.invoke(_balloonStatsEntryRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            BalloonStatsEntryLocalServiceUtil.addBalloonStatsEntry(this);
        } else {
            BalloonStatsEntryLocalServiceUtil.updateBalloonStatsEntry(this);
        }
    }

    @Override
    public BalloonStatsEntry toEscapedModel() {
        return (BalloonStatsEntry) ProxyUtil.newProxyInstance(BalloonStatsEntry.class.getClassLoader(),
            new Class[] { BalloonStatsEntry.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        BalloonStatsEntryClp clone = new BalloonStatsEntryClp();

        clone.setId(getId());
        clone.setFirstContestId(getFirstContestId());
        clone.setSecondContestId(getSecondContestId());
        clone.setChoosenContest(getChoosenContest());
        clone.setCookie(getCookie());
        clone.setIp(getIp());
        clone.setExtraData(getExtraData());

        return clone;
    }

    @Override
    public int compareTo(BalloonStatsEntry balloonStatsEntry) {
        long primaryKey = balloonStatsEntry.getPrimaryKey();

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

        if (!(obj instanceof BalloonStatsEntryClp)) {
            return false;
        }

        BalloonStatsEntryClp balloonStatsEntry = (BalloonStatsEntryClp) obj;

        long primaryKey = balloonStatsEntry.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", firstContestId=");
        sb.append(getFirstContestId());
        sb.append(", secondContestId=");
        sb.append(getSecondContestId());
        sb.append(", choosenContest=");
        sb.append(getChoosenContest());
        sb.append(", cookie=");
        sb.append(getCookie());
        sb.append(", ip=");
        sb.append(getIp());
        sb.append(", extraData=");
        sb.append(getExtraData());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.BalloonStatsEntry");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>firstContestId</column-name><column-value><![CDATA[");
        sb.append(getFirstContestId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>secondContestId</column-name><column-value><![CDATA[");
        sb.append(getSecondContestId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>choosenContest</column-name><column-value><![CDATA[");
        sb.append(getChoosenContest());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cookie</column-name><column-value><![CDATA[");
        sb.append(getCookie());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ip</column-name><column-value><![CDATA[");
        sb.append(getIp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>extraData</column-name><column-value><![CDATA[");
        sb.append(getExtraData());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
