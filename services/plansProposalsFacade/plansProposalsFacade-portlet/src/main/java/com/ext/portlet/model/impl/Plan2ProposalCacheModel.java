package com.ext.portlet.model.impl;

import com.ext.portlet.model.Plan2Proposal;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing Plan2Proposal in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Plan2Proposal
 * @generated
 */
public class Plan2ProposalCacheModel implements CacheModel<Plan2Proposal>,
    Serializable {
    public long planId;
    public long proposalId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{planId=");
        sb.append(planId);
        sb.append(", proposalId=");
        sb.append(proposalId);
        sb.append("}");

        return sb.toString();
    }

    public Plan2Proposal toEntityModel() {
        Plan2ProposalImpl plan2ProposalImpl = new Plan2ProposalImpl();

        plan2ProposalImpl.setPlanId(planId);
        plan2ProposalImpl.setProposalId(proposalId);

        plan2ProposalImpl.resetOriginalValues();

        return plan2ProposalImpl;
    }
}
