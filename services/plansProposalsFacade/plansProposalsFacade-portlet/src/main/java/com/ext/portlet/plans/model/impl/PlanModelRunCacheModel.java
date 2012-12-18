package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanModelRun;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PlanModelRun in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanModelRun
 * @generated
 */
public class PlanModelRunCacheModel implements CacheModel<PlanModelRun>,
    Serializable {
    public long id;
    public long planId;
    public long scenarioId;
    public long planVersion;
    public long version;
    public long created;
    public long updateAuthorId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(id);
        sb.append(", planId=");
        sb.append(planId);
        sb.append(", scenarioId=");
        sb.append(scenarioId);
        sb.append(", planVersion=");
        sb.append(planVersion);
        sb.append(", version=");
        sb.append(version);
        sb.append(", created=");
        sb.append(created);
        sb.append(", updateAuthorId=");
        sb.append(updateAuthorId);
        sb.append("}");

        return sb.toString();
    }

    public PlanModelRun toEntityModel() {
        PlanModelRunImpl planModelRunImpl = new PlanModelRunImpl();

        planModelRunImpl.setId(id);
        planModelRunImpl.setPlanId(planId);
        planModelRunImpl.setScenarioId(scenarioId);
        planModelRunImpl.setPlanVersion(planVersion);
        planModelRunImpl.setVersion(version);

        if (created == Long.MIN_VALUE) {
            planModelRunImpl.setCreated(null);
        } else {
            planModelRunImpl.setCreated(new Date(created));
        }

        planModelRunImpl.setUpdateAuthorId(updateAuthorId);

        planModelRunImpl.resetOriginalValues();

        return planModelRunImpl;
    }
}
