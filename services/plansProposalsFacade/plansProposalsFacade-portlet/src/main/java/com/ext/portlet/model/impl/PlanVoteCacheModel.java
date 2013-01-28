package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanVote;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PlanVote in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanVote
 * @generated
 */
public class PlanVoteCacheModel implements CacheModel<PlanVote>, Serializable {
    public long userId;
    public long contestId;
    public long planId;
    public long createDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{userId=");
        sb.append(userId);
        sb.append(", contestId=");
        sb.append(contestId);
        sb.append(", planId=");
        sb.append(planId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append("}");

        return sb.toString();
    }

    public PlanVote toEntityModel() {
        PlanVoteImpl planVoteImpl = new PlanVoteImpl();

        planVoteImpl.setUserId(userId);
        planVoteImpl.setContestId(contestId);
        planVoteImpl.setPlanId(planId);

        if (createDate == Long.MIN_VALUE) {
            planVoteImpl.setCreateDate(null);
        } else {
            planVoteImpl.setCreateDate(new Date(createDate));
        }

        planVoteImpl.resetOriginalValues();

        return planVoteImpl;
    }
}
