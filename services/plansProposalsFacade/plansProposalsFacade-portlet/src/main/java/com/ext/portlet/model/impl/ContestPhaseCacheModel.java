package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestPhase;

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
    public long ContestPhasePK;
    public long ContestPK;
    public long ContestPhaseType;
    public String contestPhaseAutopromote;
    public String ContestPhaseDescriptionOverride;
    public boolean phaseActiveOverride;
    public boolean phaseInactiveOverride;
    public long PhaseStartDate;
    public long PhaseEndDate;
    public String nextStatus;
    public long created;
    public long updated;
    public long authorId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{ContestPhasePK=");
        sb.append(ContestPhasePK);
        sb.append(", ContestPK=");
        sb.append(ContestPK);
        sb.append(", ContestPhaseType=");
        sb.append(ContestPhaseType);
        sb.append(", contestPhaseAutopromote=");
        sb.append(contestPhaseAutopromote);
        sb.append(", ContestPhaseDescriptionOverride=");
        sb.append(ContestPhaseDescriptionOverride);
        sb.append(", phaseActiveOverride=");
        sb.append(phaseActiveOverride);
        sb.append(", phaseInactiveOverride=");
        sb.append(phaseInactiveOverride);
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
        contestPhaseImpl.setContestPhaseType(ContestPhaseType);

        if (contestPhaseAutopromote == null) {
            contestPhaseImpl.setContestPhaseAutopromote(StringPool.BLANK);
        } else {
            contestPhaseImpl.setContestPhaseAutopromote(contestPhaseAutopromote);
        }

        if (ContestPhaseDescriptionOverride == null) {
            contestPhaseImpl.setContestPhaseDescriptionOverride(StringPool.BLANK);
        } else {
            contestPhaseImpl.setContestPhaseDescriptionOverride(ContestPhaseDescriptionOverride);
        }

        contestPhaseImpl.setPhaseActiveOverride(phaseActiveOverride);
        contestPhaseImpl.setPhaseInactiveOverride(phaseInactiveOverride);

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
