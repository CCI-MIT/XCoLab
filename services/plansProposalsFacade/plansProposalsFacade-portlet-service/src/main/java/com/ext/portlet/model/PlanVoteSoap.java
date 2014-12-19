package com.ext.portlet.model;

import com.ext.portlet.service.persistence.PlanVotePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanVoteServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PlanVoteServiceSoap
 * @generated
 */
public class PlanVoteSoap implements Serializable {
    private long _userId;
    private long _contestId;
    private long _planId;
    private Date _createDate;

    public PlanVoteSoap() {
    }

    public static PlanVoteSoap toSoapModel(PlanVote model) {
        PlanVoteSoap soapModel = new PlanVoteSoap();

        soapModel.setUserId(model.getUserId());
        soapModel.setContestId(model.getContestId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static PlanVoteSoap[] toSoapModels(PlanVote[] models) {
        PlanVoteSoap[] soapModels = new PlanVoteSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanVoteSoap[][] toSoapModels(PlanVote[][] models) {
        PlanVoteSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanVoteSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanVoteSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanVoteSoap[] toSoapModels(List<PlanVote> models) {
        List<PlanVoteSoap> soapModels = new ArrayList<PlanVoteSoap>(models.size());

        for (PlanVote model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanVoteSoap[soapModels.size()]);
    }

    public PlanVotePK getPrimaryKey() {
        return new PlanVotePK(_userId, _contestId);
    }

    public void setPrimaryKey(PlanVotePK pk) {
        setUserId(pk.userId);
        setContestId(pk.contestId);
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public long getContestId() {
        return _contestId;
    }

    public void setContestId(long contestId) {
        _contestId = contestId;
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }
}
