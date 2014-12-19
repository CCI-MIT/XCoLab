package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestDebate;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContestDebate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestDebate
 * @generated
 */
public class ContestDebateCacheModel implements CacheModel<ContestDebate>,
    Externalizable {
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

    @Override
    public ContestDebate toEntityModel() {
        ContestDebateImpl contestDebateImpl = new ContestDebateImpl();

        contestDebateImpl.setId(id);
        contestDebateImpl.setDebateId(debateId);
        contestDebateImpl.setContestPK(ContestPK);

        contestDebateImpl.resetOriginalValues();

        return contestDebateImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        debateId = objectInput.readLong();
        ContestPK = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(debateId);
        objectOutput.writeLong(ContestPK);
    }
}
