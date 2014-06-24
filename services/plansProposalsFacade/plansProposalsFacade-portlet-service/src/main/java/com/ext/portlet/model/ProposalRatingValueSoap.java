package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalRatingValueServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ProposalRatingValueServiceSoap
 * @generated
 */
public class ProposalRatingValueSoap implements Serializable {
    private long _id;
    private long _ratingTypeId;
    private long _value;
    private String _name;
    private String _description;

    public ProposalRatingValueSoap() {
    }

    public static ProposalRatingValueSoap toSoapModel(ProposalRatingValue model) {
        ProposalRatingValueSoap soapModel = new ProposalRatingValueSoap();

        soapModel.setId(model.getId());
        soapModel.setRatingTypeId(model.getRatingTypeId());
        soapModel.setValue(model.getValue());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());

        return soapModel;
    }

    public static ProposalRatingValueSoap[] toSoapModels(
        ProposalRatingValue[] models) {
        ProposalRatingValueSoap[] soapModels = new ProposalRatingValueSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalRatingValueSoap[][] toSoapModels(
        ProposalRatingValue[][] models) {
        ProposalRatingValueSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalRatingValueSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalRatingValueSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalRatingValueSoap[] toSoapModels(
        List<ProposalRatingValue> models) {
        List<ProposalRatingValueSoap> soapModels = new ArrayList<ProposalRatingValueSoap>(models.size());

        for (ProposalRatingValue model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalRatingValueSoap[soapModels.size()]);
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

    public long getRatingTypeId() {
        return _ratingTypeId;
    }

    public void setRatingTypeId(long ratingTypeId) {
        _ratingTypeId = ratingTypeId;
    }

    public long getValue() {
        return _value;
    }

    public void setValue(long value) {
        _value = value;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }
}
