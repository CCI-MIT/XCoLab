package com.ext.portlet.model;

import com.ext.portlet.service.persistence.ConfigurationAttributePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ConfigurationAttributeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ConfigurationAttributeServiceSoap
 * @generated
 */
public class ConfigurationAttributeSoap implements Serializable {
    private String _name;
    private long _additionalId;
    private long _numericValue;
    private String _stringValue;
    private double _realValue;

    public ConfigurationAttributeSoap() {
    }

    public static ConfigurationAttributeSoap toSoapModel(
        ConfigurationAttribute model) {
        ConfigurationAttributeSoap soapModel = new ConfigurationAttributeSoap();

        soapModel.setName(model.getName());
        soapModel.setAdditionalId(model.getAdditionalId());
        soapModel.setNumericValue(model.getNumericValue());
        soapModel.setStringValue(model.getStringValue());
        soapModel.setRealValue(model.getRealValue());

        return soapModel;
    }

    public static ConfigurationAttributeSoap[] toSoapModels(
        ConfigurationAttribute[] models) {
        ConfigurationAttributeSoap[] soapModels = new ConfigurationAttributeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ConfigurationAttributeSoap[][] toSoapModels(
        ConfigurationAttribute[][] models) {
        ConfigurationAttributeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ConfigurationAttributeSoap[models.length][models[0].length];
        } else {
            soapModels = new ConfigurationAttributeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ConfigurationAttributeSoap[] toSoapModels(
        List<ConfigurationAttribute> models) {
        List<ConfigurationAttributeSoap> soapModels = new ArrayList<ConfigurationAttributeSoap>(models.size());

        for (ConfigurationAttribute model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ConfigurationAttributeSoap[soapModels.size()]);
    }

    public ConfigurationAttributePK getPrimaryKey() {
        return new ConfigurationAttributePK(_name, _additionalId);
    }

    public void setPrimaryKey(ConfigurationAttributePK pk) {
        setName(pk.name);
        setAdditionalId(pk.additionalId);
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public long getAdditionalId() {
        return _additionalId;
    }

    public void setAdditionalId(long additionalId) {
        _additionalId = additionalId;
    }

    public long getNumericValue() {
        return _numericValue;
    }

    public void setNumericValue(long numericValue) {
        _numericValue = numericValue;
    }

    public String getStringValue() {
        return _stringValue;
    }

    public void setStringValue(String stringValue) {
        _stringValue = stringValue;
    }

    public double getRealValue() {
        return _realValue;
    }

    public void setRealValue(double realValue) {
        _realValue = realValue;
    }
}
