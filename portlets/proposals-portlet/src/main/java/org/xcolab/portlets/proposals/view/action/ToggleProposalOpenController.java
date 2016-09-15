package org.xcolab.portlets.proposals.view.action;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;

@Controller
@RequestMapping("view")
public class ToggleProposalOpenController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"action=toggleProposalOpen"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response, @RequestParam boolean planOpen)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {

        final ProposalsPermissions permissions = proposalsContext.getPermissions(request);

        if (permissions.getCanDelete()) {
            final long proposalId = proposalsContext.getProposal(request).getProposalId();
            final long userId = proposalsContext.getUser(request).getUserId();
            ProposalAttributeLocalServiceUtil.setAttribute(userId, proposalId,
                    ProposalAttributeKeys.OPEN, planOpen ? 1 : 0);
            response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request)));
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to change proposal open attribute");
        }
    }

}
