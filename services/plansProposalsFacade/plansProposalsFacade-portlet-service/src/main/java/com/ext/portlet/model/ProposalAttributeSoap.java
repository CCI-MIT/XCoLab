package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalAttributeServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.ProposalAttributeServiceSoap
 * @generated
 */
public class ProposalAttributeSoap implements Serializable {
    private long _id;
    private long _proposalId;
    private int _version;
    private int _versionWhenCreated;
    private String _name;
    private long _additionalId;
    private long _numericValue;
    private String _stringValue;
    private double _realValue;

    public ProposalAttributeSoap() {
    }

    public static ProposalAttributeSoap toSoapModel(ProposalAttribute model) {
        ProposalAttributeSoap soapModel = new ProposalAttributeSoap();

        soapModel.setId(model.getId());
        soapModel.setProposalId(model.getProposalId());
        soapModel.setVersion(model.getVersion());
        soapModel.setVersionWhenCreated(model.getVersionWhenCreated());
        soapModel.setName(model.getName());
        soapModel.setAdditionalId(model.getAdditionalId());
        soapModel.setNumericValue(model.getNumericValue());
        soapModel.setStringValue(model.getStringValue());
        soapModel.setRealValue(model.getRealValue());

        return soapModel;
    }

    public static ProposalAttributeSoap[] toSoapModels(
        ProposalAttribute[] models) {
        ProposalAttributeSoap[] soapModels = new ProposalAttributeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalAttributeSoap[][] toSoapModels(
        ProposalAttribute[][] models) {
        ProposalAttributeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalAttributeSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalAttributeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalAttributeSoap[] toSoapModels(
        List<ProposalAttribute> models) {
        List<ProposalAttributeSoap> soapModels = new ArrayList<ProposalAttributeSoap>(models.size());

        for (ProposalAttribute model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalAttributeSoap[soapModels.size()]);
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

    public int getVersion() {
        return _version;
    }

    public void setVersion(int version) {
        _version = version;
    }

    public int getVersionWhenCreated() {
        return _versionWhenCreated;
    }

    public void setVersionWhenCreated(int versionWhenCreated) {
        _versionWhenCreated = versionWhenCreated;
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
