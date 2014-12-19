package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.LandingPageServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.LandingPageServiceSoap
 * @generated
 */
public class LandingPageSoap implements Serializable {
    private long _id;
    private String _baseUrl;
    private String _targetUrl;
    private Date _updated;

    public LandingPageSoap() {
    }

    public static LandingPageSoap toSoapModel(LandingPage model) {
        LandingPageSoap soapModel = new LandingPageSoap();

        soapModel.setId(model.getId());
        soapModel.setBaseUrl(model.getBaseUrl());
        soapModel.setTargetUrl(model.getTargetUrl());
        soapModel.setUpdated(model.getUpdated());

        return soapModel;
    }

    public static LandingPageSoap[] toSoapModels(LandingPage[] models) {
        LandingPageSoap[] soapModels = new LandingPageSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static LandingPageSoap[][] toSoapModels(LandingPage[][] models) {
        LandingPageSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new LandingPageSoap[models.length][models[0].length];
        } else {
            soapModels = new LandingPageSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static LandingPageSoap[] toSoapModels(List<LandingPage> models) {
        List<LandingPageSoap> soapModels = new ArrayList<LandingPageSoap>(models.size());

        for (LandingPage model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new LandingPageSoap[soapModels.size()]);
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
}
