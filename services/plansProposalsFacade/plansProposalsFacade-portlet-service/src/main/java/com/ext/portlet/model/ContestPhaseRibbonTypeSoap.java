package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ContestPhaseRibbonTypeServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.ContestPhaseRibbonTypeServiceSoap
 * @generated
 */
public class ContestPhaseRibbonTypeSoap implements Serializable {
    private long _id;
    private int _ribbon;
    private String _hoverText;
    private String _description;
    private boolean _copyOnPromote;

    public ContestPhaseRibbonTypeSoap() {
    }

    public static ContestPhaseRibbonTypeSoap toSoapModel(
        ContestPhaseRibbonType model) {
        ContestPhaseRibbonTypeSoap soapModel = new ContestPhaseRibbonTypeSoap();

        soapModel.setId(model.getId());
        soapModel.setRibbon(model.getRibbon());
        soapModel.setHoverText(model.getHoverText());
        soapModel.setDescription(model.getDescription());
        soapModel.setCopyOnPromote(model.getCopyOnPromote());

        return soapModel;
    }

    public static ContestPhaseRibbonTypeSoap[] toSoapModels(
        ContestPhaseRibbonType[] models) {
        ContestPhaseRibbonTypeSoap[] soapModels = new ContestPhaseRibbonTypeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseRibbonTypeSoap[][] toSoapModels(
        ContestPhaseRibbonType[][] models) {
        ContestPhaseRibbonTypeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestPhaseRibbonTypeSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestPhaseRibbonTypeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestPhaseRibbonTypeSoap[] toSoapModels(
        List<ContestPhaseRibbonType> models) {
        List<ContestPhaseRibbonTypeSoap> soapModels = new ArrayList<ContestPhaseRibbonTypeSoap>(models.size());

        for (ContestPhaseRibbonType model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestPhaseRibbonTypeSoap[soapModels.size()]);
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

    public int getRibbon() {
        return _ribbon;
    }

    public void setRibbon(int ribbon) {
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
