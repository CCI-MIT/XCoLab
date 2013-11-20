package com.ext.portlet.model;

import com.ext.portlet.service.persistence.ProposalSupporterPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalSupporterServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ProposalSupporterServiceSoap
 * @generated
 */
public class ProposalSupporterSoap implements Serializable {
    private long _proposalId;
    private long _userId;
    private Date _createDate;

    public ProposalSupporterSoap() {
    }

    public static ProposalSupporterSoap toSoapModel(ProposalSupporter model) {
        ProposalSupporterSoap soapModel = new ProposalSupporterSoap();

        soapModel.setProposalId(model.getProposalId());
        soapModel.setUserId(model.getUserId());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static ProposalSupporterSoap[] toSoapModels(
        ProposalSupporter[] models) {
        ProposalSupporterSoap[] soapModels = new ProposalSupporterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalSupporterSoap[][] toSoapModels(
        ProposalSupporter[][] models) {
        ProposalSupporterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalSupporterSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalSupporterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalSupporterSoap[] toSoapModels(
        List<ProposalSupporter> models) {
        List<ProposalSupporterSoap> soapModels = new ArrayList<ProposalSupporterSoap>(models.size());

        for (ProposalSupporter model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalSupporterSoap[soapModels.size()]);
    }

    public ProposalSupporterPK getPrimaryKey() {
        return new ProposalSupporterPK(_proposalId, _userId);
    }

    public void setPrimaryKey(ProposalSupporterPK pk) {
        setProposalId(pk.proposalId);
        setUserId(pk.userId);
    }

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }
}
