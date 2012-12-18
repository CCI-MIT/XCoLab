package com.ext.portlet.discussions.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.discussions.service.http.DiscussionCategoryGroupServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.discussions.service.http.DiscussionCategoryGroupServiceSoap
 * @generated
 */
public class DiscussionCategoryGroupSoap implements Serializable {
    private long _id;
    private String _description;
    private String _url;
    private long _commentsThread;

    public DiscussionCategoryGroupSoap() {
    }

    public static DiscussionCategoryGroupSoap toSoapModel(
        DiscussionCategoryGroup model) {
        DiscussionCategoryGroupSoap soapModel = new DiscussionCategoryGroupSoap();

        soapModel.setId(model.getId());
        soapModel.setDescription(model.getDescription());
        soapModel.setUrl(model.getUrl());
        soapModel.setCommentsThread(model.getCommentsThread());

        return soapModel;
    }

    public static DiscussionCategoryGroupSoap[] toSoapModels(
        DiscussionCategoryGroup[] models) {
        DiscussionCategoryGroupSoap[] soapModels = new DiscussionCategoryGroupSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DiscussionCategoryGroupSoap[][] toSoapModels(
        DiscussionCategoryGroup[][] models) {
        DiscussionCategoryGroupSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DiscussionCategoryGroupSoap[models.length][models[0].length];
        } else {
            soapModels = new DiscussionCategoryGroupSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DiscussionCategoryGroupSoap[] toSoapModels(
        List<DiscussionCategoryGroup> models) {
        List<DiscussionCategoryGroupSoap> soapModels = new ArrayList<DiscussionCategoryGroupSoap>(models.size());

        for (DiscussionCategoryGroup model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DiscussionCategoryGroupSoap[soapModels.size()]);
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

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

    public long getCommentsThread() {
        return _commentsThread;
    }

    public void setCommentsThread(long commentsThread) {
        _commentsThread = commentsThread;
    }
}
