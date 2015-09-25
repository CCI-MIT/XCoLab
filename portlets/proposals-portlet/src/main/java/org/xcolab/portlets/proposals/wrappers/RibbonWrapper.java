package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Created by johannes on 9/18/15.
 *
 * Wrapper around ContestPhaseRibbonType with utility methods for retrieving information related to the proposal
 */
public class RibbonWrapper {
    private final static Log _log = LogFactoryUtil.getLog(RibbonWrapper.class);

    private ContestPhaseRibbonType contestPhaseRibbonType;
    private final ProposalWrapper proposalWrapper;

    public RibbonWrapper(ProposalWrapper proposalWrapper) {
        this.proposalWrapper = proposalWrapper;
    }

    private ContestPhaseRibbonType getContestPhaseRibbonType() {
        if (contestPhaseRibbonType == null) {
            ProposalContestPhaseAttribute ribbonAttribute;
            try {
                ribbonAttribute = ProposalContestPhaseAttributeLocalServiceUtil
                        .getProposalContestPhaseAttribute(
                                proposalWrapper.getProposalId(),
                                proposalWrapper.getContestPhase().getContestPhasePK(),
                                ProposalContestPhaseAttributeKeys.RIBBON
                        );
                if (ribbonAttribute != null) {
                    long typeId = ribbonAttribute.getNumericValue();
                    if (typeId >= 0) {
                        contestPhaseRibbonType = ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonType(typeId);
                    }
                } else {
                    _log.warn(String.format("Could not retrieve ribbon type for proposal %d", proposalWrapper.getProposalId()));
                }
            } catch (NoSuchProposalContestPhaseAttributeException e) {
                //TODO: can (and should) we cache this failure to prevent repeated failed requests?
                _log.info(String.format("Could not find attribute RIBBON for proposal %d, contest phase %d",
                        proposalWrapper.getProposalId(),
                        proposalWrapper.getContestPhase().getContestPhasePK()));
            } catch (PortalException | SystemException e) {
                _log.error(String.format("Liferay exception occurred while getting ContestPhaseRibbonType for proposal %d",
                        proposalWrapper.getProposalId()), e);
            }
        }
        return contestPhaseRibbonType;
    }

    public int getRibbon() {
        if (getContestPhaseRibbonType() != null) {
            return getContestPhaseRibbonType().getRibbon();
        }
        return 0;
    }
    public long getRibbonId() {
        if (getContestPhaseRibbonType() != null) {
            return getContestPhaseRibbonType().getId();
        }
        return 0L;
    }

    public String getRibbonText() {
        if (getContestPhaseRibbonType() != null) {
            return getContestPhaseRibbonType().getHoverText();
        }
        return "";
    }

    public String getRibbonTitle() {
        if (getContestPhaseRibbonType() != null) {
            if (getRibbonText().equalsIgnoreCase("Finalist") || getRibbonText().equalsIgnoreCase("Judges' Special Commendation")){
                return "Finalist";
            } else if (getRibbonText().equalsIgnoreCase("Semi-Finalist")) {
                return "Semi-Finalist";
            } else {
                return "Winner";
            }
        }
        _log.error(String.format("Could not get ribbon title: ContestPhaseRibbonType was null for proposal %d", proposalWrapper.getProposalId()));
        return "";
    }

}
