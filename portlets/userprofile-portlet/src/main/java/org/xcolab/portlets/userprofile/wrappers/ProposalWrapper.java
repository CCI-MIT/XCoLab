package org.xcolab.portlets.userprofile.wrappers;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;

/**
 * Created by Klemens Mang on 04.03.14.
 */
public class ProposalWrapper {

    Proposal proposal;

    public ProposalWrapper(Proposal proposal) {
        this.proposal = proposal;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public String getProposalName() {
        try {
            return ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Date getProposalCreationDate() {
        return proposal.getCreateDate();
    }

    public Long getContestId() throws PortalException, SystemException {
        return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId()).getContestPK();
    }

    public Long getPlanId() {
        return proposal.getProposalId();
    }

}
