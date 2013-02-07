package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.MessagingMessageConversionTypeServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.MessagingMessageConversionTypeServiceSoap
 * @generated
 */
public class MessagingMessageConversionTypeSoap implements Serializable {
    private long _typeId;
    private String _name;
    private String _description;

    public MessagingMessageConversionTypeSoap() {
    }

    public static MessagingMessageConversionTypeSoap toSoapModel(
        MessagingMessageConversionType model) {
        MessagingMessageConversionTypeSoap soapModel = new MessagingMessageConversionTypeSoap();

        soapModel.setTypeId(model.getTypeId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());

        return soapModel;
    }

    public static MessagingMessageConversionTypeSoap[] toSoapModels(
        MessagingMessageConversionType[] models) {
        MessagingMessageConversionTypeSoap[] soapModels = new MessagingMessageConversionTypeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageConversionTypeSoap[][] toSoapModels(
        MessagingMessageConversionType[][] models) {
        MessagingMessageConversionTypeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingMessageConversionTypeSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingMessageConversionTypeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageConversionTypeSoap[] toSoapModels(
        List<MessagingMessageConversionType> models) {
        List<MessagingMessageConversionTypeSoap> soapModels = new ArrayList<MessagingMessageConversionTypeSoap>(models.size());

        for (MessagingMessageConversionType model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingMessageConversionTypeSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _typeId;
    }

    public void setPrimaryKey(long pk) {
        setTypeId(pk);
    }

    public long getTypeId() {
        return _typeId;
    }

    public void setTypeId(long typeId) {
        _typeId = typeId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }
}
