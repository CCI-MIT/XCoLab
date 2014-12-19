package com.ext.portlet.model;

import com.ext.portlet.service.persistence.Proposal2PhasePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.Proposal2PhaseServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.Proposal2PhaseServiceSoap
 * @generated
 */
public class Proposal2PhaseSoap implements Serializable {
    private long _proposalId;
    private long _contestPhaseId;
    private int _versionFrom;
    private int _versionTo;
    private int _sortWeight;
    private boolean _autopromoteCandidate;

    public Proposal2PhaseSoap() {
    }

    public static Proposal2PhaseSoap toSoapModel(Proposal2Phase model) {
        Proposal2PhaseSoap soapModel = new Proposal2PhaseSoap();

        soapModel.setProposalId(model.getProposalId());
        soapModel.setContestPhaseId(model.getContestPhaseId());
        soapModel.setVersionFrom(model.getVersionFrom());
        soapModel.setVersionTo(model.getVersionTo());
        soapModel.setSortWeight(model.getSortWeight());
        soapModel.setAutopromoteCandidate(model.getAutopromoteCandidate());

        return soapModel;
    }

    public static Proposal2PhaseSoap[] toSoapModels(Proposal2Phase[] models) {
        Proposal2PhaseSoap[] soapModels = new Proposal2PhaseSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static Proposal2PhaseSoap[][] toSoapModels(Proposal2Phase[][] models) {
        Proposal2PhaseSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new Proposal2PhaseSoap[models.length][models[0].length];
        } else {
            soapModels = new Proposal2PhaseSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static Proposal2PhaseSoap[] toSoapModels(List<Proposal2Phase> models) {
        List<Proposal2PhaseSoap> soapModels = new ArrayList<Proposal2PhaseSoap>(models.size());

        for (Proposal2Phase model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new Proposal2PhaseSoap[soapModels.size()]);
    }

    public Proposal2PhasePK getPrimaryKey() {
        return new Proposal2PhasePK(_proposalId, _contestPhaseId);
    }

    public void setPrimaryKey(Proposal2PhasePK pk) {
        setProposalId(pk.proposalId);
        setContestPhaseId(pk.contestPhaseId);
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

    public int getVersionFrom() {
        return _versionFrom;
    }

    public void setVersionFrom(int versionFrom) {
        _versionFrom = versionFrom;
    }

    public int getVersionTo() {
        return _versionTo;
    }

    public void setVersionTo(int versionTo) {
        _versionTo = versionTo;
    }

    public int getSortWeight() {
        return _sortWeight;
    }

    public void setSortWeight(int sortWeight) {
        _sortWeight = sortWeight;
    }

    public boolean getAutopromoteCandidate() {
        return _autopromoteCandidate;
    }

    public boolean isAutopromoteCandidate() {
        return _autopromoteCandidate;
    }

    public void setAutopromoteCandidate(boolean autopromoteCandidate) {
        _autopromoteCandidate = autopromoteCandidate;
    }
}
