package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ProposalContestPhaseAttributeTypeServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.ProposalContestPhaseAttributeTypeServiceSoap
 * @generated
 */
public class ProposalContestPhaseAttributeTypeSoap implements Serializable {
    private long _id;
    private String _ribbon;
    private String _hoverText;
    private String _description;
    private boolean _copyOnPromote;

    public ProposalContestPhaseAttributeTypeSoap() {
    }

    public static ProposalContestPhaseAttributeTypeSoap toSoapModel(
        ProposalContestPhaseAttributeType model) {
        ProposalContestPhaseAttributeTypeSoap soapModel = new ProposalContestPhaseAttributeTypeSoap();

        soapModel.setId(model.getId());
        soapModel.setRibbon(model.getRibbon());
        soapModel.setHoverText(model.getHoverText());
        soapModel.setDescription(model.getDescription());
        soapModel.setCopyOnPromote(model.getCopyOnPromote());

        return soapModel;
    }

    public static ProposalContestPhaseAttributeTypeSoap[] toSoapModels(
        ProposalContestPhaseAttributeType[] models) {
        ProposalContestPhaseAttributeTypeSoap[] soapModels = new ProposalContestPhaseAttributeTypeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProposalContestPhaseAttributeTypeSoap[][] toSoapModels(
        ProposalContestPhaseAttributeType[][] models) {
        ProposalContestPhaseAttributeTypeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProposalContestPhaseAttributeTypeSoap[models.length][models[0].length];
        } else {
            soapModels = new ProposalContestPhaseAttributeTypeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProposalContestPhaseAttributeTypeSoap[] toSoapModels(
        List<ProposalContestPhaseAttributeType> models) {
        List<ProposalContestPhaseAttributeTypeSoap> soapModels = new ArrayList<ProposalContestPhaseAttributeTypeSoap>(models.size());

        for (ProposalContestPhaseAttributeType model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProposalContestPhaseAttributeTypeSoap[soapModels.size()]);
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

    public String getRibbon() {
        return _ribbon;
    }

    public void setRibbon(String ribbon) {
        _ribbon = ribbon;
    }

    public String getHoverText() {
        return _hoverText;
    }

    public void setHoverText(String hoverText) {
        _hoverText = hoverText;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public boolean getCopyOnPromote() {
        return _copyOnPromote;
    }

    public boolean isCopyOnPromote() {
        return _copyOnPromote;
    }

    public void setCopyOnPromote(boolean copyOnPromote) {
        _copyOnPromote = copyOnPromote;
    }
}
