package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ContestPhaseServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ContestPhaseServiceSoap
 * @generated
 */
public class ContestPhaseSoap implements Serializable {
    private long _ContestPhasePK;
    private long _ContestPK;
    private long _ContestPhaseType;
    private long _contestScheduleId;
    private boolean _fellowScreeningActive;
    private String _contestPhaseAutopromote;
    private String _ContestPhaseDescriptionOverride;
    private boolean _phaseActiveOverride;
    private boolean _phaseInactiveOverride;
    private Date _PhaseStartDate;
    private Date _PhaseEndDate;
    private Date _PhaseBufferEndDated;
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
        soapModel.setContestPhaseType(model.getContestPhaseType());
        soapModel.setContestScheduleId(model.getContestScheduleId());
        soapModel.setFellowScreeningActive(model.getFellowScreeningActive());
        soapModel.setContestPhaseAutopromote(model.getContestPhaseAutopromote());
        soapModel.setContestPhaseDescriptionOverride(model.getContestPhaseDescriptionOverride());
        soapModel.setPhaseActiveOverride(model.getPhaseActiveOverride());
        soapModel.setPhaseInactiveOverride(model.getPhaseInactiveOverride());
        soapModel.setPhaseStartDate(model.getPhaseStartDate());
        soapModel.setPhaseEndDate(model.getPhaseEndDate());
        soapModel.setPhaseBufferEndDated(model.getPhaseBufferEndDated());
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

    public long getContestPhaseType() {
        return _ContestPhaseType;
    }

    public void setContestPhaseType(long ContestPhaseType) {
        _ContestPhaseType = ContestPhaseType;
    }

    public long getContestScheduleId() {
        return _contestScheduleId;
    }

    public void setContestScheduleId(long contestScheduleId) {
        _contestScheduleId = contestScheduleId;
    }

    public boolean getFellowScreeningActive() {
        return _fellowScreeningActive;
    }

    public boolean isFellowScreeningActive() {
        return _fellowScreeningActive;
    }

    public void setFellowScreeningActive(boolean fellowScreeningActive) {
        _fellowScreeningActive = fellowScreeningActive;
    }

    public String getContestPhaseAutopromote() {
        return _contestPhaseAutopromote;
    }

    public void setContestPhaseAutopromote(String contestPhaseAutopromote) {
        _contestPhaseAutopromote = contestPhaseAutopromote;
    }

    public String getContestPhaseDescriptionOverride() {
        return _ContestPhaseDescriptionOverride;
    }

    public void setContestPhaseDescriptionOverride(
        String ContestPhaseDescriptionOverride) {
        _ContestPhaseDescriptionOverride = ContestPhaseDescriptionOverride;
    }

    public boolean getPhaseActiveOverride() {
        return _phaseActiveOverride;
    }

    public boolean isPhaseActiveOverride() {
        return _phaseActiveOverride;
    }

    public void setPhaseActiveOverride(boolean phaseActiveOverride) {
        _phaseActiveOverride = phaseActiveOverride;
    }

    public boolean getPhaseInactiveOverride() {
        return _phaseInactiveOverride;
    }

    public boolean isPhaseInactiveOverride() {
        return _phaseInactiveOverride;
    }

    public void setPhaseInactiveOverride(boolean phaseInactiveOverride) {
        _phaseInactiveOverride = phaseInactiveOverride;
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

    public Date getPhaseBufferEndDated() {
        return _PhaseBufferEndDated;
    }

    public void setPhaseBufferEndDated(Date PhaseBufferEndDated) {
        _PhaseBufferEndDated = PhaseBufferEndDated;
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
