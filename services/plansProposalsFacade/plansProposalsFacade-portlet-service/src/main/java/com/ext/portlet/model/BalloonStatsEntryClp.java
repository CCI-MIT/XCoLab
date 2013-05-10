package com.ext.portlet.model;

import com.ext.portlet.service.BalloonStatsEntryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class BalloonStatsEntryClp extends BaseModelImpl<BalloonStatsEntry>
    implements BalloonStatsEntry {
    private long _id;
    private long _firstContestId;
    private long _secondContestId;
    private int _choosenContest;
    private String _cookie;
    private String _ip;
    private String _extraData;

    public BalloonStatsEntryClp() {
    }

    public Class<?> getModelClass() {
        return BalloonStatsEntry.class;
    }

    public String getModelClassName() {
        return BalloonStatsEntry.class.getName();
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public long getFirstContestId() {
        return _firstContestId;
    }

    public void setFirstContestId(long firstContestId) {
        _firstContestId = firstContestId;
    }

    public long getSecondContestId() {
        return _secondContestId;
    }

    public void setSecondContestId(long secondContestId) {
        _secondContestId = secondContestId;
    }

    public int getChoosenContest() {
        return _choosenContest;
    }

    public void setChoosenContest(int choosenContest) {
        _choosenContest = choosenContest;
    }

    public String getCookie() {
        return _cookie;
    }

    public void setCookie(String cookie) {
        _cookie = cookie;
    }

    public String getIp() {
        return _ip;
    }

    public void setIp(String ip) {
        _ip = ip;
    }

    public String getExtraData() {
        return _extraData;
    }

    public void setExtraData(String extraData) {
        _extraData = extraData;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            BalloonStatsEntryLocalServiceUtil.addBalloonStatsEntry(this);
        } else {
            BalloonStatsEntryLocalServiceUtil.updateBalloonStatsEntry(this);
        }
    }

    @Override
    public BalloonStatsEntry toEscapedModel() {
        return (BalloonStatsEntry) Proxy.newProxyInstance(BalloonStatsEntry.class.getClassLoader(),
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
        if (obj == null) {
            return false;
        }

        BalloonStatsEntryClp balloonStatsEntry = null;

        try {
            balloonStatsEntry = (BalloonStatsEntryClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = balloonStatsEntry.getPrimaryKey();

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
