package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanSectionDefinitionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PlanSectionDefinitionServiceSoap
 * @generated
 */
public class PlanSectionDefinitionSoap implements Serializable {
    private long _id;
    private String _type;
    private String _adminTitle;
    private String _title;
    private String _defaultText;
    private String _helpText;
    private int _characterLimit;
    private long _focusAreaId;
    private long _tier;
    private boolean _locked;

    public PlanSectionDefinitionSoap() {
    }

    public static PlanSectionDefinitionSoap toSoapModel(
        PlanSectionDefinition model) {
        PlanSectionDefinitionSoap soapModel = new PlanSectionDefinitionSoap();

        soapModel.setId(model.getId());
        soapModel.setType(model.getType());
        soapModel.setAdminTitle(model.getAdminTitle());
        soapModel.setTitle(model.getTitle());
        soapModel.setDefaultText(model.getDefaultText());
        soapModel.setHelpText(model.getHelpText());
        soapModel.setCharacterLimit(model.getCharacterLimit());
        soapModel.setFocusAreaId(model.getFocusAreaId());
        soapModel.setTier(model.getTier());
        soapModel.setLocked(model.getLocked());

        return soapModel;
    }

    public static PlanSectionDefinitionSoap[] toSoapModels(
        PlanSectionDefinition[] models) {
        PlanSectionDefinitionSoap[] soapModels = new PlanSectionDefinitionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanSectionDefinitionSoap[][] toSoapModels(
        PlanSectionDefinition[][] models) {
        PlanSectionDefinitionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanSectionDefinitionSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanSectionDefinitionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanSectionDefinitionSoap[] toSoapModels(
        List<PlanSectionDefinition> models) {
        List<PlanSectionDefinitionSoap> soapModels = new ArrayList<PlanSectionDefinitionSoap>(models.size());

        for (PlanSectionDefinition model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanSectionDefinitionSoap[soapModels.size()]);
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

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }

    public String getAdminTitle() {
        return _adminTitle;
    }

    public void setAdminTitle(String adminTitle) {
        _adminTitle = adminTitle;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getDefaultText() {
        return _defaultText;
    }

    public void setDefaultText(String defaultText) {
        _defaultText = defaultText;
    }

    public String getHelpText() {
        return _helpText;
    }

    public void setHelpText(String helpText) {
        _helpText = helpText;
    }

    public int getCharacterLimit() {
        return _characterLimit;
    }

    public void setCharacterLimit(int characterLimit) {
        _characterLimit = characterLimit;
    }

    public long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(long focusAreaId) {
        _focusAreaId = focusAreaId;
    }

    public long getTier() {
        return _tier;
    }

    public void setTier(long tier) {
        _tier = tier;
    }

    public boolean getLocked() {
        return _locked;
    }

    public boolean isLocked() {
        return _locked;
    }

    public void setLocked(boolean locked) {
        _locked = locked;
    }
}
