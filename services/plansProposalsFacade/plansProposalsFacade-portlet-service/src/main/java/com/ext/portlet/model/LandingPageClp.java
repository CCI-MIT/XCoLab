package com.ext.portlet.model;

import com.ext.portlet.service.LandingPageLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class LandingPageClp extends BaseModelImpl<LandingPage>
    implements LandingPage {
    private long _id;
    private String _baseUrl;
    private String _targetUrl;
    private Date _updated;

    public LandingPageClp() {
    }

    public Class<?> getModelClass() {
        return LandingPage.class;
    }

    public String getModelClassName() {
        return LandingPage.class.getName();
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

    public String getBaseUrl() {
        return _baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        _baseUrl = baseUrl;
    }

    public String getTargetUrl() {
        return _targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        _targetUrl = targetUrl;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            LandingPageLocalServiceUtil.addLandingPage(this);
        } else {
            LandingPageLocalServiceUtil.updateLandingPage(this);
        }
    }

    @Override
    public LandingPage toEscapedModel() {
        return (LandingPage) Proxy.newProxyInstance(LandingPage.class.getClassLoader(),
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
        if (obj == null) {
            return false;
        }

        LandingPageClp landingPage = null;

        try {
            landingPage = (LandingPageClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = landingPage.getPrimaryKey();

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
