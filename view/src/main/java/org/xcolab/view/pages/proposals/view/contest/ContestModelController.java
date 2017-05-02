package org.xcolab.view.pages.proposals.view.contest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.view.proposal.BaseProposalsController;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContestModelController extends BaseProposalsController {
    private final static String COOKIE_PREFERRED_MODEL = "cc_contests_preferredModels";
    
    private final ProposalsContext proposalsContext;

    @Autowired
    public ContestModelController(ProposalsContext proposalsContext) {
        this.proposalsContext = proposalsContext;
    }

    @GetMapping("/contests/{contestYear}/{contestUrlName}/model")
    public String showContestModel(HttpServletRequest request, HttpServletResponse response,
            @PathVariable int contestYear, @PathVariable String contestUrlName,
            Model model, @RequestParam(required = false) boolean refreshModels)
            throws IOException {
    	
    	if (refreshModels) {
			RomaClientUtil.client().getManager().clearCache();
			RomaClientUtil.client().getManager().refreshSimulations();
    	}
        Contest contest = proposalsContext.getContest(request);
        Long modelId = contest.getDefaultModelId();

        Map<Long, String> modelIdsWithNames;
        final Long contestId = contest.getContestPK();
        if (modelId != null) {
            modelIdsWithNames = ContestClientUtil.getModelIdsAndNames(contestId);
            model.addAttribute("availableModels", modelIdsWithNames);
        } else {
            modelIdsWithNames = new HashMap<>();
        }

        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals(COOKIE_PREFERRED_MODEL)) {
                JSONObject json = new JSONObject(URLDecoder.decode(cookie.getValue(), "UTF-8"));
                if (json.has(String.valueOf(contestId))) {

                    long preferredModelId = json.getLong(String.valueOf(contestId));
                    if (modelIdsWithNames.containsKey(preferredModelId)) {
                        modelId = preferredModelId;
                    }
                }
            }
        }

    	model.addAttribute("modelId", modelId);
        return "proposals/contestModel";
        
    }
}
