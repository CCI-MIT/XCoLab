package com.ext.portlet.contests.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.contests.service.http.ContestDebateServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.contests.service.http.ContestDebateServiceSoap
 * @generated
 */
public class ContestDebateSoap implements Serializable {
    private Long _id;
    private Long _debateId;
    private Long _ContestPK;

    public ContestDebateSoap() {
    }

    public static ContestDebateSoap toSoapModel(ContestDebate model) {
        ContestDebateSoap soapModel = new ContestDebateSoap();

        soapModel.setId(model.getId());
        soapModel.setDebateId(model.getDebateId());
        soapModel.setContestPK(model.getContestPK());

        return soapModel;
    }

    public static ContestDebateSoap[] toSoapModels(ContestDebate[] models) {
        ContestDebateSoap[] soapModels = new ContestDebateSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestDebateSoap[][] toSoapModels(ContestDebate[][] models) {
        ContestDebateSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestDebateSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestDebateSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestDebateSoap[] toSoapModels(List<ContestDebate> models) {
        List<ContestDebateSoap> soapModels = new ArrayList<ContestDebateSoap>(models.size());

        for (ContestDebate model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestDebateSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long pk) {
        setId(pk);
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public Long getDebateId() {
        return _debateId;
    }

    public void setDebateId(Long debateId) {
        _debateId = debateId;
    }

    public Long getContestPK() {
        return _ContestPK;
    }

    public void setContestPK(Long ContestPK) {
        _ContestPK = ContestPK;
    }
}
