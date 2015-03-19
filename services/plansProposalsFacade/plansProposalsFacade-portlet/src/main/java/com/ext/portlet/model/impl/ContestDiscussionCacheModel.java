package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestDiscussion;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContestDiscussion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestDiscussion
 * @generated
 */
public class ContestDiscussionCacheModel implements CacheModel<ContestDiscussion>,
    Externalizable {
    public long DiscussionId;
    public long ContestId;
    public String Tab;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{DiscussionId=");
        sb.append(DiscussionId);
        sb.append(", ContestId=");
        sb.append(ContestId);
        sb.append(", Tab=");
        sb.append(Tab);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ContestDiscussion toEntityModel() {
        ContestDiscussionImpl contestDiscussionImpl = new ContestDiscussionImpl();

        contestDiscussionImpl.setDiscussionId(DiscussionId);
        contestDiscussionImpl.setContestId(ContestId);

        if (Tab == null) {
            contestDiscussionImpl.setTab(StringPool.BLANK);
        } else {
            contestDiscussionImpl.setTab(Tab);
        }

        contestDiscussionImpl.resetOriginalValues();

        return contestDiscussionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        DiscussionId = objectInput.readLong();
        ContestId = objectInput.readLong();
        Tab = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(DiscussionId);
        objectOutput.writeLong(ContestId);

        if (Tab == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(Tab);
        }
    }
}
