package com.ext.portlet.model.impl;

import com.ext.portlet.model.SpamReport;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SpamReport in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SpamReport
 * @generated
 */
public class SpamReportCacheModel implements CacheModel<SpamReport>,
    Externalizable {
    public long id_;
    public long spamUserId;
    public long reporterUserId;
    public long discussionMessageId;
    public boolean isAdminReport;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{id_=");
        sb.append(id_);
        sb.append(", spamUserId=");
        sb.append(spamUserId);
        sb.append(", reporterUserId=");
        sb.append(reporterUserId);
        sb.append(", discussionMessageId=");
        sb.append(discussionMessageId);
        sb.append(", isAdminReport=");
        sb.append(isAdminReport);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public SpamReport toEntityModel() {
        SpamReportImpl spamReportImpl = new SpamReportImpl();

        spamReportImpl.setId_(id_);
        spamReportImpl.setSpamUserId(spamUserId);
        spamReportImpl.setReporterUserId(reporterUserId);
        spamReportImpl.setDiscussionMessageId(discussionMessageId);
        spamReportImpl.setIsAdminReport(isAdminReport);

        spamReportImpl.resetOriginalValues();

        return spamReportImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id_ = objectInput.readLong();
        spamUserId = objectInput.readLong();
        reporterUserId = objectInput.readLong();
        discussionMessageId = objectInput.readLong();
        isAdminReport = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id_);
        objectOutput.writeLong(spamUserId);
        objectOutput.writeLong(reporterUserId);
        objectOutput.writeLong(discussionMessageId);
        objectOutput.writeBoolean(isAdminReport);
    }
}
