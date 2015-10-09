package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

/**
 * Created by johannes on 9/24/15.
 * Utility class to retrieve ProposalContestPhaseAttributes in various formats
 * TODO: use new ProposalContestPhaseAttributeLocalServiceUtil methods!
 */
public class ProposalContestPhaseAttributeHelper {
    private final static Log _log = LogFactoryUtil.getLog(ProposalContestPhaseAttributeHelper.class);

    private Long proposalId;
    private Long contestPhasePK;
    private List<ProposalContestPhaseAttribute> proposalContestPhaseAttributes;

    public ProposalContestPhaseAttributeHelper(Proposal proposal, ContestPhase contestPhase) {
        this.proposalId = proposal.getProposalId();
        if (contestPhase != null) {
            this.contestPhasePK = contestPhase.getContestPhasePK();
            try {
                proposalContestPhaseAttributes = ProposalContestPhaseAttributeLocalServiceUtil.getAllContestPhaseProposalAttributes(contestPhasePK, proposalId);
            } catch (NoSuchProposalContestPhaseAttributeException | SystemException e) {
                e.printStackTrace();
            }
        }
    }

    public long getAttributeLongValue(String attributeName, long additionalId, long defaultVal)
            throws PortalException, SystemException {
        ProposalContestPhaseAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getNumericValue();
    }

    public String getAttributeStringValue(String attributeName, long additionalId, String defaultVal)
            throws PortalException, SystemException {
        ProposalContestPhaseAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getStringValue();
    }

    public ProposalContestPhaseAttribute getAttributeOrNull(String attributeName, long additionalId) throws PortalException, SystemException {
        try {
        	if (proposalContestPhaseAttributes != null) {
                for (ProposalContestPhaseAttribute attr: proposalContestPhaseAttributes) {
                    if (attr.getName().equals(attributeName) && attr.getAdditionalId() == additionalId) {
                        return attr;
                    }
                }
            }
        } catch (Exception ignored) { }
        return null;
    }

    public ProposalContestPhaseAttribute getAttributeOrCreate(
            String attributeName, long additionalId) {
        ProposalContestPhaseAttribute attribute = null;
        try {
            attribute = getAttributeOrNull(attributeName, additionalId);
            if (attribute != null) {
                return attribute;
            }

            attribute = ProposalContestPhaseAttributeLocalServiceUtil.createProposalContestPhaseAttribute(
                    CounterLocalServiceUtil.increment(ProposalContestPhaseAttribute.class.getName()));
            attribute.setProposalId(proposalId);
            attribute.setContestPhaseId(contestPhasePK);
            attribute.setName(attributeName);
            ProposalContestPhaseAttributeLocalServiceUtil.addProposalContestPhaseAttribute(attribute);
        } catch (SystemException | PortalException e) {
            e.printStackTrace();
        }
        return attribute;
    }
}
