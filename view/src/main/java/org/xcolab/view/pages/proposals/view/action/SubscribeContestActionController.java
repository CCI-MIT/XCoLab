package org.xcolab.view.pages.proposals.view.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//-- @RequestMapping("view")
public class SubscribeContestActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    //-- @RequestMapping(params = {"action=subscribeContest"})
    public void handleAction(HttpServletRequest request, Model model, HttpServletResponse response)
            throws ProposalsAuthorizationException, IOException {
        
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
