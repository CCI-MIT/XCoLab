package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalVersion;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing ProposalVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVersion
 * @generated
 */
public class ProposalVersionCacheModel implements CacheModel<ProposalVersion>,
    Serializable {
    public long proposalId;
    public int version;
    public long authorId;
    public long createDate;
    public String updateType;
    public long updateAdditionalId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{proposalId=");
        sb.append(proposalId);
        sb.append(", version=");
        sb.append(version);
        sb.append(", authorId=");
        sb.append(authorId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", updateType=");
        sb.append(updateType);
        sb.append(", updateAdditionalId=");
        sb.append(updateAdditionalId);
        sb.append("}");

        return sb.toString();
    }

    public ProposalVersion toEntityModel() {
        ProposalVersionImpl proposalVersionImpl = new ProposalVersionImpl();

        proposalVersionImpl.setProposalId(proposalId);
        proposalVersionImpl.setVersion(version);
        proposalVersionImpl.setAuthorId(authorId);

        if (createDate == Long.MIN_VALUE) {
            proposalVersionImpl.setCreateDate(null);
        } else {
            proposalVersionImpl.setCreateDate(new Date(createDate));
        }

        if (updateType == null) {
            proposalVersionImpl.setUpdateType(StringPool.BLANK);
        } else {
            proposalVersionImpl.setUpdateType(updateType);
        }

        proposalVersionImpl.setUpdateAdditionalId(updateAdditionalId);

        proposalVersionImpl.resetOriginalValues();

        return proposalVersionImpl;
    }
}
