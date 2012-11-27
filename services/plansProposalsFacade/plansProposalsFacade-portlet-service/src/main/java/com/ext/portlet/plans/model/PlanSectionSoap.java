package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanSectionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanSectionServiceSoap
 * @generated
 */
public class PlanSectionSoap implements Serializable {
    private Long _id;
    private Long _planSectionDefinitionId;
    private Long _planId;
    private String _content;
    private Date _created;
    private Long _version;
    private Long _planVersion;
    private Long _updateAuthorId;

    public PlanSectionSoap() {
    }

    public static PlanSectionSoap toSoapModel(PlanSection model) {
        PlanSectionSoap soapModel = new PlanSectionSoap();

        soapModel.setId(model.getId());
        soapModel.setPlanSectionDefinitionId(model.getPlanSectionDefinitionId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setContent(model.getContent());
        soapModel.setCreated(model.getCreated());
        soapModel.setVersion(model.getVersion());
        soapModel.setPlanVersion(model.getPlanVersion());
        soapModel.setUpdateAuthorId(model.getUpdateAuthorId());

        return soapModel;
    }

    public static PlanSectionSoap[] toSoapModels(PlanSection[] models) {
        PlanSectionSoap[] soapModels = new PlanSectionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanSectionSoap[][] toSoapModels(PlanSection[][] models) {
        PlanSectionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanSectionSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanSectionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanSectionSoap[] toSoapModels(List<PlanSection> models) {
        List<PlanSectionSoap> soapModels = new ArrayList<PlanSectionSoap>(models.size());

        for (PlanSection model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanSectionSoap[soapModels.size()]);
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

    public Long getPlanSectionDefinitionId() {
        return _planSectionDefinitionId;
    }

    public void setPlanSectionDefinitionId(Long planSectionDefinitionId) {
        _planSectionDefinitionId = planSectionDefinitionId;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
    }

    public Long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(Long planVersion) {
        _planVersion = planVersion;
    }

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }
}
