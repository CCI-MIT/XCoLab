package org.xcolab.portlets.proposals.view.action;

import com.ext.portlet.ProposalAttributeKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.requests.JudgeProposalBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 28/10/13
 * Time: 12:06
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("view")
public class SuggestContestActionController {


    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=suggestContest"})
    public void suggestContest(ActionRequest request, Model model,
                                ActionResponse response, @Valid JudgeProposalBean judgeProposalBean,
                                BindingResult result)
         throws PortalException, SystemException, ProposalsAuthorizationException {

    }


}
