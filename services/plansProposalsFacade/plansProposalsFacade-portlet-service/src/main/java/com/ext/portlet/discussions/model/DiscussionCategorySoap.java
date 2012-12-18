package com.ext.portlet.discussions.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.discussions.service.http.DiscussionCategoryServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.discussions.service.http.DiscussionCategoryServiceSoap
 * @generated
 */
public class DiscussionCategorySoap implements Serializable {
    private long _pk;
    private long _categoryId;
    private long _categoryGroupId;
    private long _authorId;
    private String _name;
    private String _description;
    private Date _createDate;
    private Date _deleted;
    private int _threadsCount;
    private Date _lastActivityDate;
    private long _lastActivityAuthorId;

    public DiscussionCategorySoap() {
    }

    public static DiscussionCategorySoap toSoapModel(DiscussionCategory model) {
        DiscussionCategorySoap soapModel = new DiscussionCategorySoap();

        soapModel.setPk(model.getPk());
        soapModel.setCategoryId(model.getCategoryId());
        soapModel.setCategoryGroupId(model.getCategoryGroupId());
        soapModel.setAuthorId(model.getAuthorId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setDeleted(model.getDeleted());
        soapModel.setThreadsCount(model.getThreadsCount());
        soapModel.setLastActivityDate(model.getLastActivityDate());
        soapModel.setLastActivityAuthorId(model.getLastActivityAuthorId());

        return soapModel;
    }

    public static DiscussionCategorySoap[] toSoapModels(
        DiscussionCategory[] models) {
        DiscussionCategorySoap[] soapModels = new DiscussionCategorySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DiscussionCategorySoap[][] toSoapModels(
        DiscussionCategory[][] models) {
        DiscussionCategorySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DiscussionCategorySoap[models.length][models[0].length];
        } else {
            soapModels = new DiscussionCategorySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DiscussionCategorySoap[] toSoapModels(
        List<DiscussionCategory> models) {
        List<DiscussionCategorySoap> soapModels = new ArrayList<DiscussionCategorySoap>(models.size());

        for (DiscussionCategory model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DiscussionCategorySoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _pk;
    }

    public void setPrimaryKey(long pk) {
        setPk(pk);
    }

    public long getPk() {
        return _pk;
    }

    public void setPk(long pk) {
        _pk = pk;
    }

    public long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(long categoryId) {
        _categoryId = categoryId;
    }

    public long getCategoryGroupId() {
        return _categoryGroupId;
    }

    public void setCategoryGroupId(long categoryGroupId) {
        _categoryGroupId = categoryGroupId;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
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

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date deleted) {
        _deleted = deleted;
    }

    public int getThreadsCount() {
        return _threadsCount;
    }

    public void setThreadsCount(int threadsCount) {
        _threadsCount = threadsCount;
    }

    public Date getLastActivityDate() {
        return _lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        _lastActivityDate = lastActivityDate;
    }

    public long getLastActivityAuthorId() {
        return _lastActivityAuthorId;
    }

    public void setLastActivityAuthorId(long lastActivityAuthorId) {
        _lastActivityAuthorId = lastActivityAuthorId;
    }
}
