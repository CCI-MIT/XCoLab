package org.xcolab.view.pages.proposals.view.contest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
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

    private static final String COOKIE_PREFERRED_MODEL = "cc_contests_preferredModels";

    @GetMapping("/contests/{contestYear}/{contestUrlName}/model")
    public String showContestModel(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext,
            @PathVariable int contestYear, @PathVariable String contestUrlName,
            @RequestParam(required = false) boolean refreshModels)
            throws IOException {
    	
    	if (refreshModels) {
			RomaClientUtil.client().getManager().clearCache();
			RomaClientUtil.client().getManager().refreshSimulations();
    	}
        ContestWrapper contest = proposalContext.getContest();
        Long modelId = contest.getDefaultModelId();

        Map<Long, String> modelIdsWithNames;
        final Long contestId = contest.getId();
        if (modelId != null) {
            modelIdsWithNames = contestClient.getModelIdsAndNames(contestId);
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
