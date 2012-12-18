package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.ContestDebate;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ContestDebate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestDebate
 * @generated
 */
public class ContestDebateCacheModel implements CacheModel<ContestDebate>,
    Serializable {
    public long id;
    public long debateId;
    public long ContestPK;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{id=");
        sb.append(id);
        sb.append(", debateId=");
        sb.append(debateId);
        sb.append(", ContestPK=");
        sb.append(ContestPK);
        sb.append("}");

        return sb.toString();
    }

    public ContestDebate toEntityModel() {
        ContestDebateImpl contestDebateImpl = new ContestDebateImpl();

        contestDebateImpl.setId(id);
        contestDebateImpl.setDebateId(debateId);
        contestDebateImpl.setContestPK(ContestPK);

        contestDebateImpl.resetOriginalValues();

        return contestDebateImpl;
    }
}
