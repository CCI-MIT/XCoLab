package org.xcolab.view.pages.proposals.view;

import edu.mit.cci.roma.client.Simulation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.util.IdListUtil;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalTab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
//-- @RequestMapping("view")
public class ProposalModelTabController extends BaseProposalTabController {

    private final ProposalsContext proposalsContext;

    @Autowired
    public ProposalModelTabController(ProposalsContext proposalsContext) {
        this.proposalsContext = proposalsContext;
    }

    //--  @RequestMapping(params = {"pageToDisplay=proposalDetails_ACTIONSIMPACTS"})
    public String show(Model model, @RequestParam(required = false) boolean edit, HttpServletRequest request) {

        setCommonModelAndPageAttributes(request, model, ProposalTab.ACTIONSIMPACTS);
        
        if (edit) {
        	Map<Long, String> modelIdsWithNames =
                    getModelIdsAndNames(proposalsContext.getContest(request).getContestPK());
        	if (modelIdsWithNames.size() > 1) {
        		model.addAttribute("availableModels", modelIdsWithNames);
        	}
        	
            return "proposals/proposalModel_edit";
        }
        return "proposals/proposalModel";
    }

    private Map<Long, String> getModelIdsAndNames(long contestPK) {
        List<Long> modelIds = getModelIds(contestPK);

        Map<Long, String> ret = new HashMap<>();
        for (Long modelId: modelIds) {
            try {
                Simulation s = RomaClientUtil.client().getSimulation(modelId);
                ret.put(s.getId(), s.getName());

            } catch (IOException e) {
                throw new InternalException(e);
            }
        }
        return ret;
    }

    private List<Long> getModelIds(long contestPK) {
        Contest contest = ContestClientUtil.getContest(contestPK);

        List<Long> modelIds = new ArrayList<>();

        if (StringUtils.isNotBlank(contest.getOtherModels())) {
            modelIds.addAll(IdListUtil.getIdsFromString(contest.getOtherModels()));
        }
        if (!modelIds.contains(contest.getDefaultModelId())) {
            modelIds.add(contest.getDefaultModelId());
        }

        return modelIds;
    }

}
