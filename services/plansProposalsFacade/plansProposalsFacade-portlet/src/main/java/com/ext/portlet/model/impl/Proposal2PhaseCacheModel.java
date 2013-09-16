package com.ext.portlet.model.impl;

import com.ext.portlet.model.Proposal2Phase;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing Proposal2Phase in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Proposal2Phase
 * @generated
 */
public class Proposal2PhaseCacheModel implements CacheModel<Proposal2Phase>,
    Serializable {
    public long proposalId;
    public long contestPhaseId;
    public int versionFrom;
    public int versionTo;
    public int sortWeight;
    public boolean autopromoteCandidate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{proposalId=");
        sb.append(proposalId);
        sb.append(", contestPhaseId=");
        sb.append(contestPhaseId);
        sb.append(", versionFrom=");
        sb.append(versionFrom);
        sb.append(", versionTo=");
        sb.append(versionTo);
        sb.append(", sortWeight=");
        sb.append(sortWeight);
        sb.append(", autopromoteCandidate=");
        sb.append(autopromoteCandidate);
        sb.append("}");

        return sb.toString();
    }

    public Proposal2Phase toEntityModel() {
        Proposal2PhaseImpl proposal2PhaseImpl = new Proposal2PhaseImpl();

        proposal2PhaseImpl.setProposalId(proposalId);
        proposal2PhaseImpl.setContestPhaseId(contestPhaseId);
        proposal2PhaseImpl.setVersionFrom(versionFrom);
        proposal2PhaseImpl.setVersionTo(versionTo);
        proposal2PhaseImpl.setSortWeight(sortWeight);
        proposal2PhaseImpl.setAutopromoteCandidate(autopromoteCandidate);

        proposal2PhaseImpl.resetOriginalValues();

        return proposal2PhaseImpl;
    }
}
