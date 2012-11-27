package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.ContestPhase;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing ContestPhase in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhase
 * @generated
 */
public class ContestPhaseCacheModel implements CacheModel<ContestPhase>,
    Serializable {
    public Long ContestPhasePK;
    public Long ContestPK;
    public String ContestPhaseName;
    public String ContestPhaseDescription;
    public String ContestPhaseStatus;
    public long PhaseStartDate;
    public long PhaseEndDate;
    public String nextStatus;
    public long created;
    public long updated;
    public Long authorId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{ContestPhasePK=");
        sb.append(ContestPhasePK);
        sb.append(", ContestPK=");
        sb.append(ContestPK);
        sb.append(", ContestPhaseName=");
        sb.append(ContestPhaseName);
        sb.append(", ContestPhaseDescription=");
        sb.append(ContestPhaseDescription);
        sb.append(", ContestPhaseStatus=");
        sb.append(ContestPhaseStatus);
        sb.append(", PhaseStartDate=");
        sb.append(PhaseStartDate);
        sb.append(", PhaseEndDate=");
        sb.append(PhaseEndDate);
        sb.append(", nextStatus=");
        sb.append(nextStatus);
        sb.append(", created=");
        sb.append(created);
        sb.append(", updated=");
        sb.append(updated);
        sb.append(", authorId=");
        sb.append(authorId);
        sb.append("}");

        return sb.toString();
    }

    public ContestPhase toEntityModel() {
        ContestPhaseImpl contestPhaseImpl = new ContestPhaseImpl();

        contestPhaseImpl.setContestPhasePK(ContestPhasePK);
        contestPhaseImpl.setContestPK(ContestPK);

        if (ContestPhaseName == null) {
            contestPhaseImpl.setContestPhaseName(StringPool.BLANK);
        } else {
            contestPhaseImpl.setContestPhaseName(ContestPhaseName);
        }

        if (ContestPhaseDescription == null) {
            contestPhaseImpl.setContestPhaseDescription(StringPool.BLANK);
        } else {
            contestPhaseImpl.setContestPhaseDescription(ContestPhaseDescription);
        }

        if (ContestPhaseStatus == null) {
            contestPhaseImpl.setContestPhaseStatus(StringPool.BLANK);
        } else {
            contestPhaseImpl.setContestPhaseStatus(ContestPhaseStatus);
        }

        if (PhaseStartDate == Long.MIN_VALUE) {
            contestPhaseImpl.setPhaseStartDate(null);
        } else {
            contestPhaseImpl.setPhaseStartDate(new Date(PhaseStartDate));
        }

        if (PhaseEndDate == Long.MIN_VALUE) {
            contestPhaseImpl.setPhaseEndDate(null);
        } else {
            contestPhaseImpl.setPhaseEndDate(new Date(PhaseEndDate));
        }

        if (nextStatus == null) {
            contestPhaseImpl.setNextStatus(StringPool.BLANK);
        } else {
            contestPhaseImpl.setNextStatus(nextStatus);
        }

        if (created == Long.MIN_VALUE) {
            contestPhaseImpl.setCreated(null);
        } else {
            contestPhaseImpl.setCreated(new Date(created));
        }

        if (updated == Long.MIN_VALUE) {
            contestPhaseImpl.setUpdated(null);
        } else {
            contestPhaseImpl.setUpdated(new Date(updated));
        }

        contestPhaseImpl.setAuthorId(authorId);

        contestPhaseImpl.resetOriginalValues();

        return contestPhaseImpl;
    }
}
