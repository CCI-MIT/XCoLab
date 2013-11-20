package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalAttributeType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProposalAttributeType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttributeType
 * @generated
 */
public class ProposalAttributeTypeCacheModel implements CacheModel<ProposalAttributeType>,
    Externalizable {
    public String name;
    public boolean visibleInVersionHistory;
    public boolean copyOnPromote;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{name=");
        sb.append(name);
        sb.append(", visibleInVersionHistory=");
        sb.append(visibleInVersionHistory);
        sb.append(", copyOnPromote=");
        sb.append(copyOnPromote);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProposalAttributeType toEntityModel() {
        ProposalAttributeTypeImpl proposalAttributeTypeImpl = new ProposalAttributeTypeImpl();

        if (name == null) {
            proposalAttributeTypeImpl.setName(StringPool.BLANK);
        } else {
            proposalAttributeTypeImpl.setName(name);
        }

        proposalAttributeTypeImpl.setVisibleInVersionHistory(visibleInVersionHistory);
        proposalAttributeTypeImpl.setCopyOnPromote(copyOnPromote);

        proposalAttributeTypeImpl.resetOriginalValues();

        return proposalAttributeTypeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        name = objectInput.readUTF();
        visibleInVersionHistory = objectInput.readBoolean();
        copyOnPromote = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        objectOutput.writeBoolean(visibleInVersionHistory);
        objectOutput.writeBoolean(copyOnPromote);
    }
}
