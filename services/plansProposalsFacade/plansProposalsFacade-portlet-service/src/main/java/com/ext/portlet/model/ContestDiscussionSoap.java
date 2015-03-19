package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ContestDiscussionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ContestDiscussionServiceSoap
 * @generated
 */
public class ContestDiscussionSoap implements Serializable {
    private long _DiscussionId;
    private long _ContestId;
    private String _Tab;

    public ContestDiscussionSoap() {
    }

    public static ContestDiscussionSoap toSoapModel(ContestDiscussion model) {
        ContestDiscussionSoap soapModel = new ContestDiscussionSoap();

        soapModel.setDiscussionId(model.getDiscussionId());
        soapModel.setContestId(model.getContestId());
        soapModel.setTab(model.getTab());

        return soapModel;
    }

    public static ContestDiscussionSoap[] toSoapModels(
        ContestDiscussion[] models) {
        ContestDiscussionSoap[] soapModels = new ContestDiscussionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestDiscussionSoap[][] toSoapModels(
        ContestDiscussion[][] models) {
        ContestDiscussionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestDiscussionSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestDiscussionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestDiscussionSoap[] toSoapModels(
        List<ContestDiscussion> models) {
        List<ContestDiscussionSoap> soapModels = new ArrayList<ContestDiscussionSoap>(models.size());

        for (ContestDiscussion model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestDiscussionSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _DiscussionId;
    }

    public void setPrimaryKey(long pk) {
        setDiscussionId(pk);
    }

    public long getDiscussionId() {
        return _DiscussionId;
    }

    public void setDiscussionId(long DiscussionId) {
        _DiscussionId = DiscussionId;
    }

    public long getContestId() {
        return _ContestId;
    }

    public void setContestId(long ContestId) {
        _ContestId = ContestId;
    }

    public String getTab() {
        return _Tab;
    }

    public void setTab(String Tab) {
        _Tab = Tab;
    }
}
