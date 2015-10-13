package com.ext.portlet.model;

import com.ext.portlet.service.persistence.ProposalReferencePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalReferenceServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ProposalReferenceServiceSoap
 * @generated
 */
public class ProposalReferenceSoap implements Serializable {
    private long _proposalId;
    private long _subProposalId;
    private long _sectionAttributeId;

    public ProposalReferenceSoap() {
    }

    public static ProposalReferenceSoap toSoapModel(ProposalReference model) {
        ProposalReferenceSoap soapModel = new ProposalReferenceSoap();

        soapModel.setProposalId(model.getProposalId());
        soapModel.setSubProposalId(model.getSubProposalId());
        soapModel.setSectionAttributeId(model.getSectionAttributeId());

        return soapModel;
    }

    public static ProposalReferenceSoap[] toSoapModels(
        ProposalReference[] models) {
        ProposalReferenceSoap[] soapModels = new ProposalReferenceSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalReferenceSoap[][] toSoapModels(
        ProposalReference[][] models) {
        ProposalReferenceSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalReferenceSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalReferenceSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalReferenceSoap[] toSoapModels(
        List<ProposalReference> models) {
        List<ProposalReferenceSoap> soapModels = new ArrayList<ProposalReferenceSoap>(models.size());

        for (ProposalReference model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalReferenceSoap[soapModels.size()]);
    }

    public ProposalReferencePK getPrimaryKey() {
        return new ProposalReferencePK(_proposalId, _subProposalId);
    }

    public void setPrimaryKey(ProposalReferencePK pk) {
        setProposalId(pk.proposalId);
        setSubProposalId(pk.subProposalId);
    }

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }

    public long getSubProposalId() {
        return _subProposalId;
    }

    public void setSubProposalId(long subProposalId) {
        _subProposalId = subProposalId;
    }

    public long getSectionAttributeId() {
        return _sectionAttributeId;
    }

    public void setSectionAttributeId(long sectionAttributeId) {
        _sectionAttributeId = sectionAttributeId;
    }
}
