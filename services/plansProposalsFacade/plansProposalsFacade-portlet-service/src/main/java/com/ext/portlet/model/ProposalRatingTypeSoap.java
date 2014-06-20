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
    private long _ratingTypeId;
    private String _label;

    public ProposalRatingTypeSoap() {
    }

    public static ProposalRatingTypeSoap toSoapModel(ProposalRatingType model) {
        ProposalRatingTypeSoap soapModel = new ProposalRatingTypeSoap();

        soapModel.setRatingTypeId(model.getRatingTypeId());
        soapModel.setLabel(model.getLabel());

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
        return _ratingTypeId;
    }

    public void setPrimaryKey(long pk) {
        setRatingTypeId(pk);
    }

    public long getRatingTypeId() {
        return _ratingTypeId;
    }

    public void setRatingTypeId(long ratingTypeId) {
        _ratingTypeId = ratingTypeId;
    }

    public String getLabel() {
        return _label;
    }

    public void setLabel(String label) {
        _label = label;
    }
}
