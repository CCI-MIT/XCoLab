package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.MessagingMessageConversionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.MessagingMessageConversionServiceSoap
 * @generated
 */
public class MessagingMessageConversionSoap implements Serializable {
    private long _conversionId;
    private long _conversionTypeId;
    private long _messageId;
    private String _ipAddress;
    private String _extraData;
    private String _extraData2;
    private Date _createDate;

    public MessagingMessageConversionSoap() {
    }

    public static MessagingMessageConversionSoap toSoapModel(
        MessagingMessageConversion model) {
        MessagingMessageConversionSoap soapModel = new MessagingMessageConversionSoap();

        soapModel.setConversionId(model.getConversionId());
        soapModel.setConversionTypeId(model.getConversionTypeId());
        soapModel.setMessageId(model.getMessageId());
        soapModel.setIpAddress(model.getIpAddress());
        soapModel.setExtraData(model.getExtraData());
        soapModel.setExtraData2(model.getExtraData2());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static MessagingMessageConversionSoap[] toSoapModels(
        MessagingMessageConversion[] models) {
        MessagingMessageConversionSoap[] soapModels = new MessagingMessageConversionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageConversionSoap[][] toSoapModels(
        MessagingMessageConversion[][] models) {
        MessagingMessageConversionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingMessageConversionSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingMessageConversionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageConversionSoap[] toSoapModels(
        List<MessagingMessageConversion> models) {
        List<MessagingMessageConversionSoap> soapModels = new ArrayList<MessagingMessageConversionSoap>(models.size());

        for (MessagingMessageConversion model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingMessageConversionSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _conversionId;
    }

    public void setPrimaryKey(long pk) {
        setConversionId(pk);
    }

    public long getConversionId() {
        return _conversionId;
    }

    public void setConversionId(long conversionId) {
        _conversionId = conversionId;
    }

    public long getConversionTypeId() {
        return _conversionTypeId;
    }

    public void setConversionTypeId(long conversionTypeId) {
        _conversionTypeId = conversionTypeId;
    }

    public long getMessageId() {
        return _messageId;
    }

    public void setMessageId(long messageId) {
        _messageId = messageId;
    }

    public String getIpAddress() {
        return _ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        _ipAddress = ipAddress;
    }

    public String getExtraData() {
        return _extraData;
    }

    public void setExtraData(String extraData) {
        _extraData = extraData;
    }

    public String getExtraData2() {
        return _extraData2;
    }

    public void setExtraData2(String extraData2) {
        _extraData2 = extraData2;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }
}
