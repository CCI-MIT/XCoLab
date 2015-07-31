package org.xcolab.portlets.userprofile.wrappers;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Date;

/**
 * Created by Klemens Mang on 04.03.14.
 */
public class ProposalWrapper {

    private final static Log _log = LogFactoryUtil.getLog(ProposalWrapper.class);
    private Proposal proposal;
    private Long contestId;
    private Boolean isProposalInActiveContest;
    private String proposalName;

    public ProposalWrapper(Proposal proposal) {
        this.proposal = proposal;
        initContestIdForProposal();
        initProposalName();
    }

    public Proposal getProposal() {
        return proposal;
    }

    private void initProposalName(){
        try {
            long proposalId = proposal.getProposalId();
            int latestActiveProposalVersion = Proposal2PhaseLocalServiceUtil.getLatestProposalVersionInActiveContest(proposalId);
            if(latestActiveProposalVersion == -1){
                proposalName = ProposalLocalServiceUtil.getAttribute(proposalId, ProposalAttributeKeys.NAME, 0).getStringValue();
            } else {
                proposalName = ProposalLocalServiceUtil.getAttribute(proposalId, latestActiveProposalVersion, ProposalAttributeKeys.NAME, 0).getStringValue();
            }
        } catch (Exception e) {
            _log.warn("Could not get proposal name for proposalId: " + proposal.getProposalId());
        }
    }

    public String getProposalName() {
        return proposalName;
    }

    public Date getProposalCreationDate() {
        return proposal.getCreateDate();
    }

    public Long getContestId(){
        return contestId;
    }

    public Long getPlanId() {
        return proposal.getProposalId();
    }

    public boolean isProposalInActiveContest() {
        return isProposalInActiveContest;
    }

    private void initContestIdForProposal(){
        isProposalInActiveContest = true;
        try {
            contestId = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId()).getContestPK();
        } catch(Exception e){
            _log.warn("Could not get contest Id for proposalId: " + proposal.getProposalId());
            isProposalInActiveContest = false;
        }
    }



}
