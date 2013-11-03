package com.ext.portlet.model;

import com.ext.portlet.service.persistence.ProposalVersionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalVersionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.ProposalVersionServiceSoap
 * @generated
 */
public class ProposalVersionSoap implements Serializable {
    private long _proposalId;
    private int _version;
    private long _authorId;
    private Date _createDate;
    private String _updateType;
    private long _updateAdditionalId;

    public ProposalVersionSoap() {
    }

    public static ProposalVersionSoap toSoapModel(ProposalVersion model) {
        ProposalVersionSoap soapModel = new ProposalVersionSoap();

        soapModel.setProposalId(model.getProposalId());
        soapModel.setVersion(model.getVersion());
        soapModel.setAuthorId(model.getAuthorId());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setUpdateType(model.getUpdateType());
        soapModel.setUpdateAdditionalId(model.getUpdateAdditionalId());

        return soapModel;
    }

    public static ProposalVersionSoap[] toSoapModels(ProposalVersion[] models) {
        ProposalVersionSoap[] soapModels = new ProposalVersionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalVersionSoap[][] toSoapModels(
        ProposalVersion[][] models) {
        ProposalVersionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalVersionSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalVersionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalVersionSoap[] toSoapModels(
        List<ProposalVersion> models) {
        List<ProposalVersionSoap> soapModels = new ArrayList<ProposalVersionSoap>(models.size());

        for (ProposalVersion model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalVersionSoap[soapModels.size()]);
    }

    public ProposalVersionPK getPrimaryKey() {
        return new ProposalVersionPK(_proposalId, _version);
    }

    public void setPrimaryKey(ProposalVersionPK pk) {
        setProposalId(pk.proposalId);
        setVersion(pk.version);
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

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public String getUpdateType() {
        return _updateType;
    }

    public void setUpdateType(String updateType) {
        _updateType = updateType;
    }

    public long getUpdateAdditionalId() {
        return _updateAdditionalId;
    }

    public void setUpdateAdditionalId(long updateAdditionalId) {
        _updateAdditionalId = updateAdditionalId;
    }
}
