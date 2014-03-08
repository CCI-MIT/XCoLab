package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.BalloonLinkServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.BalloonLinkServiceSoap
 * @generated
 */
public class BalloonLinkSoap implements Serializable {
    private String _uuid;
    private String _targetUrl;
    private int _visits;
    private String _balloonUserUuid;
    private Date _createDate;

    public BalloonLinkSoap() {
    }

    public static BalloonLinkSoap toSoapModel(BalloonLink model) {
        BalloonLinkSoap soapModel = new BalloonLinkSoap();

        soapModel.setUuid(model.getUuid());
        soapModel.setTargetUrl(model.getTargetUrl());
        soapModel.setVisits(model.getVisits());
        soapModel.setBalloonUserUuid(model.getBalloonUserUuid());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static BalloonLinkSoap[] toSoapModels(BalloonLink[] models) {
        BalloonLinkSoap[] soapModels = new BalloonLinkSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static BalloonLinkSoap[][] toSoapModels(BalloonLink[][] models) {
        BalloonLinkSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new BalloonLinkSoap[models.length][models[0].length];
        } else {
            soapModels = new BalloonLinkSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static BalloonLinkSoap[] toSoapModels(List<BalloonLink> models) {
        List<BalloonLinkSoap> soapModels = new ArrayList<BalloonLinkSoap>(models.size());

        for (BalloonLink model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new BalloonLinkSoap[soapModels.size()]);
    }

    public String getPrimaryKey() {
        return _uuid;
    }

    public void setPrimaryKey(String pk) {
        setUuid(pk);
    }

    public String getUuid() {
        return _uuid;
    }

    public void setUuid(String uuid) {
        _uuid = uuid;
    }

    public String getTargetUrl() {
        return _targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        _targetUrl = targetUrl;
    }

    public int getVisits() {
        return _visits;
    }

    public void setVisits(int visits) {
        _visits = visits;
    }

    public String getBalloonUserUuid() {
        return _balloonUserUuid;
    }

    public void setBalloonUserUuid(String balloonUserUuid) {
        _balloonUserUuid = balloonUserUuid;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }
}
