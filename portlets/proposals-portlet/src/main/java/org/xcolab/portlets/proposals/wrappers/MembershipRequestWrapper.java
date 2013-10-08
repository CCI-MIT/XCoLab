package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.Proposal;
import com.liferay.portal.model.MembershipRequest;
import com.ext.portlet.service.ProposalLocalServiceUtil;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 10/7/13
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class MembershipRequestWrapper {
    private List<MembershipRequest> membershipRequests;

    public MembershipRequestWrapper(Proposal proposal){
        try{
            membershipRequests = ProposalLocalServiceUtil.getMembershipRequests(proposal.getProposalId());
        } catch(Exception e){ e.printStackTrace(); }
    }

    public MembershipRequestWrapper()

}
