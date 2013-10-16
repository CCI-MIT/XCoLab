package org.xcolab.portlets.proposals.view.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.requests.JudgeProposalBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 10/16/13
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("view")
public class JudgeProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;


    @RequestMapping(params = {"action=saveJudgeRating"})
    public void saveJudgeRating(ActionRequest request, Model model,
                                 ActionResponse response, @Valid JudgeProposalBean judgeProposalBean,
                                 BindingResult result) {
        // SAVE TO DB
        System.out.println("TEST");
    }

    @RequestMapping(params = {"action=saveFellowRating"})
    public void saveFellowRating(ActionRequest request, Model model,
                                 ActionResponse response, @Valid JudgeProposalBean judgeProposalBean,
                                 BindingResult result) {
        // SAVE TO DB
        System.out.println("TEST");
    }
}
