package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalContestPhaseAttributeType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProposalContestPhaseAttributeType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeType
 * @generated
 */
public class ProposalContestPhaseAttributeTypeCacheModel implements CacheModel<ProposalContestPhaseAttributeType>,
    Externalizable {
    public String name;
    public boolean copyOnPromote;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{name=");
        sb.append(name);
        sb.append(", copyOnPromote=");
        sb.append(copyOnPromote);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProposalContestPhaseAttributeType toEntityModel() {
        ProposalContestPhaseAttributeTypeImpl proposalContestPhaseAttributeTypeImpl =
            new ProposalContestPhaseAttributeTypeImpl();

        if (name == null) {
            proposalContestPhaseAttributeTypeImpl.setName(StringPool.BLANK);
        } else {
            proposalContestPhaseAttributeTypeImpl.setName(name);
        }

        proposalContestPhaseAttributeTypeImpl.setCopyOnPromote(copyOnPromote);

        proposalContestPhaseAttributeTypeImpl.resetOriginalValues();

        return proposalContestPhaseAttributeTypeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        name = objectInput.readUTF();
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

        objectOutput.writeBoolean(copyOnPromote);
    }
}
