package org.xcolab.portlets.proposals.view.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("view")
public class SubscribeContestActionController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"action=subscribeContest"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {
        
        if (proposalsContext.getPermissions(request).getCanSubscribeContest()) {
            long contestId = proposalsContext.getContest(request).getContestPK();
            long memberId = proposalsContext.getMember(request).getUserId();
            if (ContestClientUtil.isMemberSubscribedToContest(contestId, memberId)) {
                ContestClientUtil.unsubscribeMemberFromContest(contestId, memberId);
            }
            else {
                ContestClientUtil.subscribeMemberToContest(contestId, memberId);

            }
            response.sendRedirect(proposalsContext.getContest(request).getContestLinkUrl());
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to subscribe contest");
        }
    }

}
