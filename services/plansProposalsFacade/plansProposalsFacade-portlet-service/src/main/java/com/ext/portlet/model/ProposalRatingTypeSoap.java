package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalRatingTypeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ProposalRatingTypeServiceSoap
 * @generated
 */
public class ProposalRatingTypeSoap implements Serializable {
    private long _id;
    private String _label;
    private String _description;
    private int _judgeType;
    private boolean _isActive;

    public ProposalRatingTypeSoap() {
    }

    public static ProposalRatingTypeSoap toSoapModel(ProposalRatingType model) {
        ProposalRatingTypeSoap soapModel = new ProposalRatingTypeSoap();

        soapModel.setId(model.getId());
        soapModel.setLabel(model.getLabel());
        soapModel.setDescription(model.getDescription());
        soapModel.setJudgeType(model.getJudgeType());
        soapModel.setIsActive(model.getIsActive());

        return soapModel;
    }

    public static ProposalRatingTypeSoap[] toSoapModels(
        ProposalRatingType[] models) {
        ProposalRatingTypeSoap[] soapModels = new ProposalRatingTypeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalRatingTypeSoap[][] toSoapModels(
        ProposalRatingType[][] models) {
        ProposalRatingTypeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalRatingTypeSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalRatingTypeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalRatingTypeSoap[] toSoapModels(
        List<ProposalRatingType> models) {
        List<ProposalRatingTypeSoap> soapModels = new ArrayList<ProposalRatingTypeSoap>(models.size());

        for (ProposalRatingType model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalRatingTypeSoap[soapModels.size()]);
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

    public String getLabel() {
        return _label;
    }

    public void setLabel(String label) {
        _label = label;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public int getJudgeType() {
        return _judgeType;
    }

    public void setJudgeType(int judgeType) {
        _judgeType = judgeType;
    }

    public boolean getIsActive() {
        return _isActive;
    }

    public boolean isIsActive() {
        return _isActive;
    }

    public void setIsActive(boolean isActive) {
        _isActive = isActive;
    }
}
