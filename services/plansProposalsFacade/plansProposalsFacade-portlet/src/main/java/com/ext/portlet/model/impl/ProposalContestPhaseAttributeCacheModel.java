package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalContestPhaseAttribute;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ProposalContestPhaseAttribute in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttribute
 * @generated
 */
public class ProposalContestPhaseAttributeCacheModel implements CacheModel<ProposalContestPhaseAttribute>,
    Serializable {
    public long id;
    public long proposalId;
    public long contestPhaseId;
    public long typeId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(id);
        sb.append(", proposalId=");
        sb.append(proposalId);
        sb.append(", contestPhaseId=");
        sb.append(contestPhaseId);
        sb.append(", typeId=");
        sb.append(typeId);
        sb.append("}");

        return sb.toString();
    }

    public ProposalContestPhaseAttribute toEntityModel() {
        ProposalContestPhaseAttributeImpl proposalContestPhaseAttributeImpl = new ProposalContestPhaseAttributeImpl();

        proposalContestPhaseAttributeImpl.setId(id);
        proposalContestPhaseAttributeImpl.setProposalId(proposalId);
        proposalContestPhaseAttributeImpl.setContestPhaseId(contestPhaseId);
        proposalContestPhaseAttributeImpl.setTypeId(typeId);

        proposalContestPhaseAttributeImpl.resetOriginalValues();

        return proposalContestPhaseAttributeImpl;
    }
}
