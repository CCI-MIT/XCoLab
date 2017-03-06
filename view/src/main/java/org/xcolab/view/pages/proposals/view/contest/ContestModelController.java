package org.xcolab.view.pages.proposals.view.contest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
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
//-- @RequestMapping("view")
public class ContestModelController extends BaseProposalsController {
    private final static String COOKIE_PREFERRED_MODEL = "cc_contests_preferredModels";
    
    @Autowired
    private ProposalsContext proposalsContext;

	//-- @RequestMapping(params = "pageToDisplay=contestModel")
    public String showContestProposals(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(required = false) boolean refreshModels)
            throws IOException {
    	
    	if (refreshModels) {
			RomaClientUtil.client().getManager().clearCache();
			RomaClientUtil.client().getManager().refreshSimulations();
    	}
		Long modelId = 0L;
    	Long contestPK = proposalsContext.getContest(request).getContestPK();
		try{
			Contest contest = ContestClientUtil.getContest(contestPK);
			modelId = contest.getDefaultModelId();
			Map<Long, String> modelIdsWithNames;
			if (modelId != null) {
				modelIdsWithNames = ContestClientUtil.getModelIdsAndNames(proposalsContext.getContest(request).getContestPK());
				model.addAttribute("availableModels", modelIdsWithNames);
			}
			else {
				modelIdsWithNames = new HashMap<>();
			}

			for (Cookie cookie: request.getCookies()) {
				if (cookie.getName().equals(COOKIE_PREFERRED_MODEL)) {
					try {
						JSONObject object = new JSONObject(URLDecoder.decode(cookie.getValue()));
						if (object.has(String.valueOf(proposalsContext.getContest(request).getContestPK()))) {

							long preferredModelId = object.getLong(String.valueOf(contestPK));
							if (modelIdsWithNames.containsKey(preferredModelId)) {
								modelId = preferredModelId;
							}

						}

					}
					catch (JSONException e) {
						//ignored
					}
				}
			}
		}catch (ContestNotFoundException ignored){

		}

    	model.addAttribute("modelId", modelId);
        return "contestModel";
        
    }
}
