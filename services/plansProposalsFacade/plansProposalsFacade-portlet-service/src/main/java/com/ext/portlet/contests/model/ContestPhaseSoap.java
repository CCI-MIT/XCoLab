package com.ext.portlet.contests.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.contests.service.http.ContestPhaseServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.contests.service.http.ContestPhaseServiceSoap
 * @generated
 */
public class ContestPhaseSoap implements Serializable {
    private long _ContestPhasePK;
    private long _ContestPK;
    private String _ContestPhaseName;
    private String _ContestPhaseDescription;
    private String _ContestPhaseStatus;
    private Date _PhaseStartDate;
    private Date _PhaseEndDate;
    private String _nextStatus;
    private Date _created;
    private Date _updated;
    private long _authorId;

    public ContestPhaseSoap() {
    }

    public static ContestPhaseSoap toSoapModel(ContestPhase model) {
        ContestPhaseSoap soapModel = new ContestPhaseSoap();

        soapModel.setContestPhasePK(model.getContestPhasePK());
        soapModel.setContestPK(model.getContestPK());
        soapModel.setContestPhaseName(model.getContestPhaseName());
        soapModel.setContestPhaseDescription(model.getContestPhaseDescription());
        soapModel.setContestPhaseStatus(model.getContestPhaseStatus());
        soapModel.setPhaseStartDate(model.getPhaseStartDate());
        soapModel.setPhaseEndDate(model.getPhaseEndDate());
        soapModel.setNextStatus(model.getNextStatus());
        soapModel.setCreated(model.getCreated());
        soapModel.setUpdated(model.getUpdated());
        soapModel.setAuthorId(model.getAuthorId());

        return soapModel;
    }

    public static ContestPhaseSoap[] toSoapModels(ContestPhase[] models) {
        ContestPhaseSoap[] soapModels = new ContestPhaseSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseSoap[][] toSoapModels(ContestPhase[][] models) {
        ContestPhaseSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestPhaseSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestPhaseSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseSoap[] toSoapModels(List<ContestPhase> models) {
        List<ContestPhaseSoap> soapModels = new ArrayList<ContestPhaseSoap>(models.size());

        for (ContestPhase model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestPhaseSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _ContestPhasePK;
    }

    public void setPrimaryKey(long pk) {
        setContestPhasePK(pk);
    }

    public long getContestPhasePK() {
        return _ContestPhasePK;
    }

    public void setContestPhasePK(long ContestPhasePK) {
        _ContestPhasePK = ContestPhasePK;
    }

    public long getContestPK() {
        return _ContestPK;
    }

    public void setContestPK(long ContestPK) {
        _ContestPK = ContestPK;
    }

    public String getContestPhaseName() {
        return _ContestPhaseName;
    }

    public void setContestPhaseName(String ContestPhaseName) {
        _ContestPhaseName = ContestPhaseName;
    }

    public String getContestPhaseDescription() {
        return _ContestPhaseDescription;
    }

    public void setContestPhaseDescription(String ContestPhaseDescription) {
        _ContestPhaseDescription = ContestPhaseDescription;
    }

    public String getContestPhaseStatus() {
        return _ContestPhaseStatus;
    }

    public void setContestPhaseStatus(String ContestPhaseStatus) {
        _ContestPhaseStatus = ContestPhaseStatus;
    }

    public Date getPhaseStartDate() {
        return _PhaseStartDate;
    }

    public void setPhaseStartDate(Date PhaseStartDate) {
        _PhaseStartDate = PhaseStartDate;
    }

    public Date getPhaseEndDate() {
        return _PhaseEndDate;
    }

    public void setPhaseEndDate(Date PhaseEndDate) {
        _PhaseEndDate = PhaseEndDate;
    }

    public String getNextStatus() {
        return _nextStatus;
    }

    public void setNextStatus(String nextStatus) {
        _nextStatus = nextStatus;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }
}
