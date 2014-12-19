package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.TrackedVisitServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.TrackedVisitServiceSoap
 * @generated
 */
public class TrackedVisitSoap implements Serializable {
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

    public TrackedVisitSoap() {
    }

    public static TrackedVisitSoap toSoapModel(TrackedVisit model) {
        TrackedVisitSoap soapModel = new TrackedVisitSoap();

        soapModel.setId(model.getId());
        soapModel.setUuid(model.getUuid());
        soapModel.setIp(model.getIp());
        soapModel.setCity(model.getCity());
        soapModel.setCountry(model.getCountry());
        soapModel.setUrl(model.getUrl());
        soapModel.setBrowser(model.getBrowser());
        soapModel.setHeaders(model.getHeaders());
        soapModel.setReferer(model.getReferer());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static TrackedVisitSoap[] toSoapModels(TrackedVisit[] models) {
        TrackedVisitSoap[] soapModels = new TrackedVisitSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static TrackedVisitSoap[][] toSoapModels(TrackedVisit[][] models) {
        TrackedVisitSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new TrackedVisitSoap[models.length][models[0].length];
        } else {
            soapModels = new TrackedVisitSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static TrackedVisitSoap[] toSoapModels(List<TrackedVisit> models) {
        List<TrackedVisitSoap> soapModels = new ArrayList<TrackedVisitSoap>(models.size());

        for (TrackedVisit model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new TrackedVisitSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long pk) {
        setId(pk);
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public String getUuid() {
        return _uuid;
    }

    public void setUuid(String uuid) {
        _uuid = uuid;
    }

    public String getIp() {
        return _ip;
    }

    public void setIp(String ip) {
        _ip = ip;
    }

    public String getCity() {
        return _city;
    }

    public void setCity(String city) {
        _city = city;
    }

    public String getCountry() {
        return _country;
    }

    public void setCountry(String country) {
        _country = country;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

    public String getBrowser() {
        return _browser;
    }

    public void setBrowser(String browser) {
        _browser = browser;
    }

    public String getHeaders() {
        return _headers;
    }

    public void setHeaders(String headers) {
        _headers = headers;
    }

    public String getReferer() {
        return _referer;
    }

    public void setReferer(String referer) {
        _referer = referer;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }
}
