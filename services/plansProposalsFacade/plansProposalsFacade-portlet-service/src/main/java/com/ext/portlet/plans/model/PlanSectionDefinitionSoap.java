package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanSectionDefinitionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanSectionDefinitionServiceSoap
 * @generated
 */
public class PlanSectionDefinitionSoap implements Serializable {
    private Long _id;
    private String _adminTitle;
    private String _title;
    private String _defaultText;
    private String _helpText;
    private Integer _characterLimit;
    private Long _focusAreaId;
    private Boolean _locked;

    public PlanSectionDefinitionSoap() {
    }

    public static PlanSectionDefinitionSoap toSoapModel(
        PlanSectionDefinition model) {
        PlanSectionDefinitionSoap soapModel = new PlanSectionDefinitionSoap();

        soapModel.setId(model.getId());
        soapModel.setAdminTitle(model.getAdminTitle());
        soapModel.setTitle(model.getTitle());
        soapModel.setDefaultText(model.getDefaultText());
        soapModel.setHelpText(model.getHelpText());
        soapModel.setCharacterLimit(model.getCharacterLimit());
        soapModel.setFocusAreaId(model.getFocusAreaId());
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

    public Integer getCharacterLimit() {
        return _characterLimit;
    }

    public void setCharacterLimit(Integer characterLimit) {
        _characterLimit = characterLimit;
    }

    public Long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(Long focusAreaId) {
        _focusAreaId = focusAreaId;
    }

    public Boolean getLocked() {
        return _locked;
    }

    public void setLocked(Boolean locked) {
        _locked = locked;
    }
}
