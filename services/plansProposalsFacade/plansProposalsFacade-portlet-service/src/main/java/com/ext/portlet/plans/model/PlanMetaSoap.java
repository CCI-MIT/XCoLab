package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanMetaServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanMetaServiceSoap
 * @generated
 */
public class PlanMetaSoap implements Serializable {
    private Long _id;
    private Long _planId;
    private Long _planTypeId;
    private Long _planCreated;
    private Long _authorId;
    private Integer _votes;
    private Long _planGroupId;
    private Boolean _open;
    private String _status;
    private Long _mbCategoryId;
    private Long _categoryGroupId;
    private Long _version;
    private Long _planVersion;
    private Date _created;
    private Long _updateAuthorId;
    private Long _modelId;
    private Boolean _promoted;
    private Long _previousContestPhase;
    private Long _contestPhase;

    public PlanMetaSoap() {
    }

    public static PlanMetaSoap toSoapModel(PlanMeta model) {
        PlanMetaSoap soapModel = new PlanMetaSoap();

        soapModel.setId(model.getId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setPlanTypeId(model.getPlanTypeId());
        soapModel.setPlanCreated(model.getPlanCreated());
        soapModel.setAuthorId(model.getAuthorId());
        soapModel.setVotes(model.getVotes());
        soapModel.setPlanGroupId(model.getPlanGroupId());
        soapModel.setOpen(model.getOpen());
        soapModel.setStatus(model.getStatus());
        soapModel.setMbCategoryId(model.getMbCategoryId());
        soapModel.setCategoryGroupId(model.getCategoryGroupId());
        soapModel.setVersion(model.getVersion());
        soapModel.setPlanVersion(model.getPlanVersion());
        soapModel.setCreated(model.getCreated());
        soapModel.setUpdateAuthorId(model.getUpdateAuthorId());
        soapModel.setModelId(model.getModelId());
        soapModel.setPromoted(model.getPromoted());
        soapModel.setPreviousContestPhase(model.getPreviousContestPhase());
        soapModel.setContestPhase(model.getContestPhase());

        return soapModel;
    }

    public static PlanMetaSoap[] toSoapModels(PlanMeta[] models) {
        PlanMetaSoap[] soapModels = new PlanMetaSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanMetaSoap[][] toSoapModels(PlanMeta[][] models) {
        PlanMetaSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanMetaSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanMetaSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanMetaSoap[] toSoapModels(List<PlanMeta> models) {
        List<PlanMetaSoap> soapModels = new ArrayList<PlanMetaSoap>(models.size());

        for (PlanMeta model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanMetaSoap[soapModels.size()]);
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

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public Long getPlanCreated() {
        return _planCreated;
    }

    public void setPlanCreated(Long planCreated) {
        _planCreated = planCreated;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public Integer getVotes() {
        return _votes;
    }

    public void setVotes(Integer votes) {
        _votes = votes;
    }

    public Long getPlanGroupId() {
        return _planGroupId;
    }

    public void setPlanGroupId(Long planGroupId) {
        _planGroupId = planGroupId;
    }

    public Boolean getOpen() {
        return _open;
    }

    public void setOpen(Boolean open) {
        _open = open;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }

    public Long getMbCategoryId() {
        return _mbCategoryId;
    }

    public void setMbCategoryId(Long mbCategoryId) {
        _mbCategoryId = mbCategoryId;
    }

    public Long getCategoryGroupId() {
        return _categoryGroupId;
    }

    public void setCategoryGroupId(Long categoryGroupId) {
        _categoryGroupId = categoryGroupId;
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

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Boolean getPromoted() {
        return _promoted;
    }

    public void setPromoted(Boolean promoted) {
        _promoted = promoted;
    }

    public Long getPreviousContestPhase() {
        return _previousContestPhase;
    }

    public void setPreviousContestPhase(Long previousContestPhase) {
        _previousContestPhase = previousContestPhase;
    }

    public Long getContestPhase() {
        return _contestPhase;
    }

    public void setContestPhase(Long contestPhase) {
        _contestPhase = contestPhase;
    }
}
