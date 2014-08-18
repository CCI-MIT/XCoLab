package org.xcolab.portlets.proposals.view.action;

import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 10/10/13
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("view")
public class RemoveUserFromProposalTeamActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=removeUserFromTeam"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response, @RequestParam("member") long memberUserId)
            throws PortalException, SystemException, ProposalsAuthorizationException {

        if (proposalsContext.getPermissions(request).getCanManageUsers()) {
        	long proposalId = proposalsContext.getProposal(request).getProposalId();

        	ProposalLocalServiceUtil.removeUserFromTeam(proposalId,memberUserId);
        }

    }

}
