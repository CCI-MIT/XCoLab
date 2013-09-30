package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalContestPhaseAttributeServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.ProposalContestPhaseAttributeServiceSoap
 * @generated
 */
public class ProposalContestPhaseAttributeSoap implements Serializable {
    private long _id;
    private long _proposalId;
    private long _contestPhaseId;
    private long _typeId;

    public ProposalContestPhaseAttributeSoap() {
    }

    public static ProposalContestPhaseAttributeSoap toSoapModel(
        ProposalContestPhaseAttribute model) {
        ProposalContestPhaseAttributeSoap soapModel = new ProposalContestPhaseAttributeSoap();

        soapModel.setId(model.getId());
        soapModel.setProposalId(model.getProposalId());
        soapModel.setContestPhaseId(model.getContestPhaseId());
        soapModel.setTypeId(model.getTypeId());

        return soapModel;
    }

    public static ProposalContestPhaseAttributeSoap[] toSoapModels(
        ProposalContestPhaseAttribute[] models) {
        ProposalContestPhaseAttributeSoap[] soapModels = new ProposalContestPhaseAttributeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalContestPhaseAttributeSoap[][] toSoapModels(
        ProposalContestPhaseAttribute[][] models) {
        ProposalContestPhaseAttributeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalContestPhaseAttributeSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalContestPhaseAttributeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalContestPhaseAttributeSoap[] toSoapModels(
        List<ProposalContestPhaseAttribute> models) {
        List<ProposalContestPhaseAttributeSoap> soapModels = new ArrayList<ProposalContestPhaseAttributeSoap>(models.size());

        for (ProposalContestPhaseAttribute model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalContestPhaseAttributeSoap[soapModels.size()]);
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

    public long getContestPhaseId() {
        return _contestPhaseId;
    }

    public void setContestPhaseId(long contestPhaseId) {
        _contestPhaseId = contestPhaseId;
    }

    public long getTypeId() {
        return _typeId;
    }

    public void setTypeId(long typeId) {
        _typeId = typeId;
    }
}
