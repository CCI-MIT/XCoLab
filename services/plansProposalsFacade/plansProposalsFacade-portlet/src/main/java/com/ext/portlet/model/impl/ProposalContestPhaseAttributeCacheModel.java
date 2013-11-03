package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalContestPhaseAttribute;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
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
    public String name;
    public long additionalId;
    public long numericValue;
    public String stringValue;
    public double realValue;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{id=");
        sb.append(id);
        sb.append(", proposalId=");
        sb.append(proposalId);
        sb.append(", contestPhaseId=");
        sb.append(contestPhaseId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", additionalId=");
        sb.append(additionalId);
        sb.append(", numericValue=");
        sb.append(numericValue);
        sb.append(", stringValue=");
        sb.append(stringValue);
        sb.append(", realValue=");
        sb.append(realValue);
        sb.append("}");

        return sb.toString();
    }

    public ProposalContestPhaseAttribute toEntityModel() {
        ProposalContestPhaseAttributeImpl proposalContestPhaseAttributeImpl = new ProposalContestPhaseAttributeImpl();

        proposalContestPhaseAttributeImpl.setId(id);
        proposalContestPhaseAttributeImpl.setProposalId(proposalId);
        proposalContestPhaseAttributeImpl.setContestPhaseId(contestPhaseId);

        if (name == null) {
            proposalContestPhaseAttributeImpl.setName(StringPool.BLANK);
        } else {
            proposalContestPhaseAttributeImpl.setName(name);
        }

        proposalContestPhaseAttributeImpl.setAdditionalId(additionalId);
        proposalContestPhaseAttributeImpl.setNumericValue(numericValue);

        if (stringValue == null) {
            proposalContestPhaseAttributeImpl.setStringValue(StringPool.BLANK);
        } else {
            proposalContestPhaseAttributeImpl.setStringValue(stringValue);
        }

        proposalContestPhaseAttributeImpl.setRealValue(realValue);

        proposalContestPhaseAttributeImpl.resetOriginalValues();

        return proposalContestPhaseAttributeImpl;
    }
}
