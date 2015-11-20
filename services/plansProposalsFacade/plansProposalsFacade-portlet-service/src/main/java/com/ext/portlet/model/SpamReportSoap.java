package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.SpamReportServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.SpamReportServiceSoap
 * @generated
 */
public class SpamReportSoap implements Serializable {
    private long _id_;
    private long _spamUserId;
    private long _reporterUserId;
    private long _discussionMessageId;
    private boolean _isAdminReport;

    public SpamReportSoap() {
    }

    public static SpamReportSoap toSoapModel(SpamReport model) {
        SpamReportSoap soapModel = new SpamReportSoap();

        soapModel.setId_(model.getId_());
        soapModel.setSpamUserId(model.getSpamUserId());
        soapModel.setReporterUserId(model.getReporterUserId());
        soapModel.setDiscussionMessageId(model.getDiscussionMessageId());
        soapModel.setIsAdminReport(model.getIsAdminReport());

        return soapModel;
    }

    public static SpamReportSoap[] toSoapModels(SpamReport[] models) {
        SpamReportSoap[] soapModels = new SpamReportSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static SpamReportSoap[][] toSoapModels(SpamReport[][] models) {
        SpamReportSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new SpamReportSoap[models.length][models[0].length];
        } else {
            soapModels = new SpamReportSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static SpamReportSoap[] toSoapModels(List<SpamReport> models) {
        List<SpamReportSoap> soapModels = new ArrayList<SpamReportSoap>(models.size());

        for (SpamReport model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new SpamReportSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _id_;
    }

    public void setPrimaryKey(long pk) {
        setId_(pk);
    }

    public long getId_() {
        return _id_;
    }

    public void setId_(long id_) {
        _id_ = id_;
    }

    public long getSpamUserId() {
        return _spamUserId;
    }

    public void setSpamUserId(long spamUserId) {
        _spamUserId = spamUserId;
    }

    public long getReporterUserId() {
        return _reporterUserId;
    }

    public void setReporterUserId(long reporterUserId) {
        _reporterUserId = reporterUserId;
    }

    public long getDiscussionMessageId() {
        return _discussionMessageId;
    }

    public void setDiscussionMessageId(long discussionMessageId) {
        _discussionMessageId = discussionMessageId;
    }

    public boolean getIsAdminReport() {
        return _isAdminReport;
    }

    public boolean isIsAdminReport() {
        return _isAdminReport;
    }

    public void setIsAdminReport(boolean isAdminReport) {
        _isAdminReport = isAdminReport;
    }
}
