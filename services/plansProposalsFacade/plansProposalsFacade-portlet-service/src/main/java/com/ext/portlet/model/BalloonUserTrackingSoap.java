package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.BalloonUserTrackingServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.BalloonUserTrackingServiceSoap
 * @generated
 */
public class BalloonUserTrackingSoap implements Serializable {
    private String _uuid;
    private String _email;
    private String _parent;
    private String _ip;
    private Date _createDate;
    private Date _registrationDate;
    private Date _formFiledDate;
    private long _userId;
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

    public BalloonUserTrackingSoap() {
    }

    public static BalloonUserTrackingSoap toSoapModel(BalloonUserTracking model) {
        BalloonUserTrackingSoap soapModel = new BalloonUserTrackingSoap();

        soapModel.setUuid(model.getUuid());
        soapModel.setEmail(model.getEmail());
        soapModel.setParent(model.getParent());
        soapModel.setIp(model.getIp());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setRegistrationDate(model.getRegistrationDate());
        soapModel.setFormFiledDate(model.getFormFiledDate());
        soapModel.setUserId(model.getUserId());
        soapModel.setBalloonTextId(model.getBalloonTextId());
        soapModel.setReferrer(model.getReferrer());
        soapModel.setLatitude(model.getLatitude());
        soapModel.setLongitude(model.getLongitude());
        soapModel.setCity(model.getCity());
        soapModel.setCountry(model.getCountry());
        soapModel.setExtraData(model.getExtraData());
        soapModel.setBalloonLinkUuid(model.getBalloonLinkUuid());
        soapModel.setBalloonLinkContext(model.getBalloonLinkContext());
        soapModel.setUserAgent(model.getUserAgent());

        return soapModel;
    }

    public static BalloonUserTrackingSoap[] toSoapModels(
        BalloonUserTracking[] models) {
        BalloonUserTrackingSoap[] soapModels = new BalloonUserTrackingSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static BalloonUserTrackingSoap[][] toSoapModels(
        BalloonUserTracking[][] models) {
        BalloonUserTrackingSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new BalloonUserTrackingSoap[models.length][models[0].length];
        } else {
            soapModels = new BalloonUserTrackingSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static BalloonUserTrackingSoap[] toSoapModels(
        List<BalloonUserTracking> models) {
        List<BalloonUserTrackingSoap> soapModels = new ArrayList<BalloonUserTrackingSoap>(models.size());

        for (BalloonUserTracking model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new BalloonUserTrackingSoap[soapModels.size()]);
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

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getParent() {
        return _parent;
    }

    public void setParent(String parent) {
        _parent = parent;
    }

    public String getIp() {
        return _ip;
    }

    public void setIp(String ip) {
        _ip = ip;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getRegistrationDate() {
        return _registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        _registrationDate = registrationDate;
    }

    public Date getFormFiledDate() {
        return _formFiledDate;
    }

    public void setFormFiledDate(Date formFiledDate) {
        _formFiledDate = formFiledDate;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public long getBalloonTextId() {
        return _balloonTextId;
    }

    public void setBalloonTextId(long balloonTextId) {
        _balloonTextId = balloonTextId;
    }

    public String getReferrer() {
        return _referrer;
    }

    public void setReferrer(String referrer) {
        _referrer = referrer;
    }

    public double getLatitude() {
        return _latitude;
    }

    public void setLatitude(double latitude) {
        _latitude = latitude;
    }

    public double getLongitude() {
        return _longitude;
    }

    public void setLongitude(double longitude) {
        _longitude = longitude;
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

    public String getExtraData() {
        return _extraData;
    }

    public void setExtraData(String extraData) {
        _extraData = extraData;
    }

    public String getBalloonLinkUuid() {
        return _balloonLinkUuid;
    }

    public void setBalloonLinkUuid(String balloonLinkUuid) {
        _balloonLinkUuid = balloonLinkUuid;
    }

    public String getBalloonLinkContext() {
        return _balloonLinkContext;
    }

    public void setBalloonLinkContext(String balloonLinkContext) {
        _balloonLinkContext = balloonLinkContext;
    }

    public String getUserAgent() {
        return _userAgent;
    }

    public void setUserAgent(String userAgent) {
        _userAgent = userAgent;
    }
}
