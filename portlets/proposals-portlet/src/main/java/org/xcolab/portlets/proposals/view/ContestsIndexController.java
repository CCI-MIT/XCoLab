package org.xcolab.portlets.proposals.view;

import javax.portlet.PortletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class ContestsIndexController {
    
    @RequestMapping
    public String showContestsIndex(PortletRequest request) throws PortalException, SystemException {
        
        Proposal p = ProposalLocalServiceUtil.create(10144L);
        //ProposalLocalServiceUtil.createProposal(1234);

        ProposalLocalServiceUtil.setAttribute(10144L, p.getProposalId(), "DESCRIPTION", 0, "1 DESCRIPTION " + p.getProposalId(), 0, 0);
        ProposalLocalServiceUtil.setAttribute(10144L, p.getProposalId(), "NAME", 0, "1This is a proposal " + p.getProposalId(), 0, 0);
        ProposalLocalServiceUtil.setAttribute(10144L, p.getProposalId(), "NAME", 0, "2This is a proposal " + p.getProposalId(), 0, 0);
        ProposalLocalServiceUtil.setAttribute(10144L, p.getProposalId(), "NAME", 0, "3This is a proposal " + p.getProposalId(), 0, 0);
        ProposalLocalServiceUtil.setAttribute(10144L, p.getProposalId(), "NAME", 0, "4This is a proposal " + p.getProposalId(), 0, 0);
        ProposalLocalServiceUtil.setAttribute(10144L, p.getProposalId(), "NAME", 0, "5This is a proposal " + p.getProposalId(), 0, 0);
        ProposalLocalServiceUtil.setAttribute(10144L, p.getProposalId(), "NAME", 0, "6This is a proposal " + p.getProposalId(), 0, 0);
        ProposalLocalServiceUtil.setAttribute(10144L, p.getProposalId(), "NAME", 0, "7This is a proposal " + p.getProposalId(), 0, 0);
        ProposalLocalServiceUtil.setAttribute(10144L, p.getProposalId(), "NAME", 0, "8This is a proposal " + p.getProposalId(), 0, 0);
        
        return "contestIndex";
    }

}
