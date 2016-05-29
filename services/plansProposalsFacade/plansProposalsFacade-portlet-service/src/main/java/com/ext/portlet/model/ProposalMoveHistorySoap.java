package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalMoveHistoryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ProposalMoveHistoryServiceSoap
 * @generated
 */
public class ProposalMoveHistorySoap implements Serializable {
    private long _id_;
    private long _sourceProposalId;
    private long _sourceContestId;
    private long _sourcePhaseId;
    private long _targetProposalId;
    private long _targetContestId;
    private long _targetPhaseId;
    private long _movingUserId;
    private Date _moveDate;
    private String _moveType;

    public ProposalMoveHistorySoap() {
    }

    public static ProposalMoveHistorySoap toSoapModel(ProposalMoveHistory model) {
        ProposalMoveHistorySoap soapModel = new ProposalMoveHistorySoap();

        soapModel.setId_(model.getId_());
        soapModel.setSourceProposalId(model.getSourceProposalId());
        soapModel.setSourceContestId(model.getSourceContestId());
        soapModel.setSourcePhaseId(model.getSourcePhaseId());
        soapModel.setTargetProposalId(model.getTargetProposalId());
        soapModel.setTargetContestId(model.getTargetContestId());
        soapModel.setTargetPhaseId(model.getTargetPhaseId());
        soapModel.setMovingUserId(model.getMovingUserId());
        soapModel.setMoveDate(model.getMoveDate());
        soapModel.setMoveType(model.getMoveType());

        return soapModel;
    }

    public static ProposalMoveHistorySoap[] toSoapModels(
        ProposalMoveHistory[] models) {
        ProposalMoveHistorySoap[] soapModels = new ProposalMoveHistorySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalMoveHistorySoap[][] toSoapModels(
        ProposalMoveHistory[][] models) {
        ProposalMoveHistorySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalMoveHistorySoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalMoveHistorySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalMoveHistorySoap[] toSoapModels(
        List<ProposalMoveHistory> models) {
        List<ProposalMoveHistorySoap> soapModels = new ArrayList<ProposalMoveHistorySoap>(models.size());

        for (ProposalMoveHistory model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalMoveHistorySoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _id_;
    }

    public void setPrimaryKey(long pk) {
        setId_(pk);
    }

    public long getId_() {
        return _id_;
    }

    public void setId_(long id_) {
        _id_ = id_;
    }

    public long getSourceProposalId() {
        return _sourceProposalId;
    }

    public void setSourceProposalId(long sourceProposalId) {
        _sourceProposalId = sourceProposalId;
    }

    public long getSourceContestId() {
        return _sourceContestId;
    }

    public void setSourceContestId(long sourceContestId) {
        _sourceContestId = sourceContestId;
    }

    public long getSourcePhaseId() {
        return _sourcePhaseId;
    }

    public void setSourcePhaseId(long sourcePhaseId) {
        _sourcePhaseId = sourcePhaseId;
    }

    public long getTargetProposalId() {
        return _targetProposalId;
    }

    public void setTargetProposalId(long targetProposalId) {
        _targetProposalId = targetProposalId;
    }

    public long getTargetContestId() {
        return _targetContestId;
    }

    public void setTargetContestId(long targetContestId) {
        _targetContestId = targetContestId;
    }

    public long getTargetPhaseId() {
        return _targetPhaseId;
    }

    public void setTargetPhaseId(long targetPhaseId) {
        _targetPhaseId = targetPhaseId;
    }

    public long getMovingUserId() {
        return _movingUserId;
    }

    public void setMovingUserId(long movingUserId) {
        _movingUserId = movingUserId;
    }

    public Date getMoveDate() {
        return _moveDate;
    }

    public void setMoveDate(Date moveDate) {
        _moveDate = moveDate;
    }

    public String getMoveType() {
        return _moveType;
    }

    public void setMoveType(String moveType) {
        _moveType = moveType;
    }
}
