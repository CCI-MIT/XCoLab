package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanMetaServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.PlanMetaServiceSoap
 * @generated
 */
public class PlanMetaSoap implements Serializable {
    private long _id;
    private long _planId;
    private long _planTypeId;
    private long _planCreated;
    private long _authorId;
    private int _votes;
    private long _planGroupId;
    private boolean _open;
    private String _status;
    private long _mbCategoryId;
    private long _categoryGroupId;
    private long _version;
    private long _planVersion;
    private Date _created;
    private long _updateAuthorId;
    private long _modelId;
    private boolean _promoted;
    private long _previousContestPhase;
    private long _contestPhase;

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

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(long planTypeId) {
        _planTypeId = planTypeId;
    }

    public long getPlanCreated() {
        return _planCreated;
    }

    public void setPlanCreated(long planCreated) {
        _planCreated = planCreated;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }

    public int getVotes() {
        return _votes;
    }

    public void setVotes(int votes) {
        _votes = votes;
    }

    public long getPlanGroupId() {
        return _planGroupId;
    }

    public void setPlanGroupId(long planGroupId) {
        _planGroupId = planGroupId;
    }

    public boolean getOpen() {
        return _open;
    }

    public boolean isOpen() {
        return _open;
    }

    public void setOpen(boolean open) {
        _open = open;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }

    public long getMbCategoryId() {
        return _mbCategoryId;
    }

    public void setMbCategoryId(long mbCategoryId) {
        _mbCategoryId = mbCategoryId;
    }

    public long getCategoryGroupId() {
        return _categoryGroupId;
    }

    public void setCategoryGroupId(long categoryGroupId) {
        _categoryGroupId = categoryGroupId;
    }

    public long getVersion() {
        return _version;
    }

    public void setVersion(long version) {
        _version = version;
    }

    public long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(long planVersion) {
        _planVersion = planVersion;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public long getModelId() {
        return _modelId;
    }

    public void setModelId(long modelId) {
        _modelId = modelId;
    }

    public boolean getPromoted() {
        return _promoted;
    }

    public boolean isPromoted() {
        return _promoted;
    }

    public void setPromoted(boolean promoted) {
        _promoted = promoted;
    }

    public long getPreviousContestPhase() {
        return _previousContestPhase;
    }

    public void setPreviousContestPhase(long previousContestPhase) {
        _previousContestPhase = previousContestPhase;
    }

    public long getContestPhase() {
        return _contestPhase;
    }

    public void setContestPhase(long contestPhase) {
        _contestPhase = contestPhase;
    }
}
