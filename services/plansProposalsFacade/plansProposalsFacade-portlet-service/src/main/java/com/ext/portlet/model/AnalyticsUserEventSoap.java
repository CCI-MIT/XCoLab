package com.ext.portlet.model;

import com.ext.portlet.service.persistence.AnalyticsUserEventPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.AnalyticsUserEventServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.AnalyticsUserEventServiceSoap
 * @generated
 */
public class AnalyticsUserEventSoap implements Serializable {
    private long _userId;
    private String _idString;
    private String _category;
    private String _action;
    private String _label;
    private int _value;
    private Date _created;

    public AnalyticsUserEventSoap() {
    }

    public static AnalyticsUserEventSoap toSoapModel(AnalyticsUserEvent model) {
        AnalyticsUserEventSoap soapModel = new AnalyticsUserEventSoap();

        soapModel.setUserId(model.getUserId());
        soapModel.setIdString(model.getIdString());
        soapModel.setCategory(model.getCategory());
        soapModel.setAction(model.getAction());
        soapModel.setLabel(model.getLabel());
        soapModel.setValue(model.getValue());
        soapModel.setCreated(model.getCreated());

        return soapModel;
    }

    public static AnalyticsUserEventSoap[] toSoapModels(
        AnalyticsUserEvent[] models) {
        AnalyticsUserEventSoap[] soapModels = new AnalyticsUserEventSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static AnalyticsUserEventSoap[][] toSoapModels(
        AnalyticsUserEvent[][] models) {
        AnalyticsUserEventSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new AnalyticsUserEventSoap[models.length][models[0].length];
        } else {
            soapModels = new AnalyticsUserEventSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static AnalyticsUserEventSoap[] toSoapModels(
        List<AnalyticsUserEvent> models) {
        List<AnalyticsUserEventSoap> soapModels = new ArrayList<AnalyticsUserEventSoap>(models.size());

        for (AnalyticsUserEvent model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new AnalyticsUserEventSoap[soapModels.size()]);
    }

    public AnalyticsUserEventPK getPrimaryKey() {
        return new AnalyticsUserEventPK(_userId, _idString);
    }

    public void setPrimaryKey(AnalyticsUserEventPK pk) {
        setUserId(pk.userId);
        setIdString(pk.idString);
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getIdString() {
        return _idString;
    }

    public void setIdString(String idString) {
        _idString = idString;
    }

    public String getCategory() {
        return _category;
    }

    public void setCategory(String category) {
        _category = category;
    }

    public String getAction() {
        return _action;
    }

    public void setAction(String action) {
        _action = action;
    }

    public String getLabel() {
        return _label;
    }

    public void setLabel(String label) {
        _label = label;
    }

    public int getValue() {
        return _value;
    }

    public void setValue(int value) {
        _value = value;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }
}
