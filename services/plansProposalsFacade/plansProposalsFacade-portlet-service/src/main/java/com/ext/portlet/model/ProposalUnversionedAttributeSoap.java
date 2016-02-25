package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalUnversionedAttributeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ProposalUnversionedAttributeServiceSoap
 * @generated
 */
public class ProposalUnversionedAttributeSoap implements Serializable {
    private long _id;
    private long _proposalId;
    private long _createAuthorId;
    private long _lastAuthorId;
    private Date _createDate;
    private Date _lastUpdateDate;
    private String _name;
    private int _addtionalId;
    private long _numericValue;
    private String _stringValue;
    private double _realValue;

    public ProposalUnversionedAttributeSoap() {
    }

    public static ProposalUnversionedAttributeSoap toSoapModel(
        ProposalUnversionedAttribute model) {
        ProposalUnversionedAttributeSoap soapModel = new ProposalUnversionedAttributeSoap();

        soapModel.setId(model.getId());
        soapModel.setProposalId(model.getProposalId());
        soapModel.setCreateAuthorId(model.getCreateAuthorId());
        soapModel.setLastAuthorId(model.getLastAuthorId());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setLastUpdateDate(model.getLastUpdateDate());
        soapModel.setName(model.getName());
        soapModel.setAddtionalId(model.getAddtionalId());
        soapModel.setNumericValue(model.getNumericValue());
        soapModel.setStringValue(model.getStringValue());
        soapModel.setRealValue(model.getRealValue());

        return soapModel;
    }

    public static ProposalUnversionedAttributeSoap[] toSoapModels(
        ProposalUnversionedAttribute[] models) {
        ProposalUnversionedAttributeSoap[] soapModels = new ProposalUnversionedAttributeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalUnversionedAttributeSoap[][] toSoapModels(
        ProposalUnversionedAttribute[][] models) {
        ProposalUnversionedAttributeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalUnversionedAttributeSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalUnversionedAttributeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalUnversionedAttributeSoap[] toSoapModels(
        List<ProposalUnversionedAttribute> models) {
        List<ProposalUnversionedAttributeSoap> soapModels = new ArrayList<ProposalUnversionedAttributeSoap>(models.size());

        for (ProposalUnversionedAttribute model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalUnversionedAttributeSoap[soapModels.size()]);
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

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }

    public long getCreateAuthorId() {
        return _createAuthorId;
    }

    public void setCreateAuthorId(long createAuthorId) {
        _createAuthorId = createAuthorId;
    }

    public long getLastAuthorId() {
        return _lastAuthorId;
    }

    public void setLastAuthorId(long lastAuthorId) {
        _lastAuthorId = lastAuthorId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return _lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        _lastUpdateDate = lastUpdateDate;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public int getAddtionalId() {
        return _addtionalId;
    }

    public void setAddtionalId(int addtionalId) {
        _addtionalId = addtionalId;
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
