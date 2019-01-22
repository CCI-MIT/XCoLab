package org.xcolab.view.pages.proposals.view.proposal.tabs;

import edu.mit.cci.roma.client.Simulation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.commons.IdListUtil;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalLegacyModelTabController extends BaseProposalTabController {

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=ACTIONS_IMPACTS")
    public String show(HttpServletRequest request, Model model, ProposalContext proposalContext,
            @RequestParam(required = false) boolean edit) {

        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.ACTIONS_IMPACTS);
        return "proposals/proposalModel";
    }

    private Map<Long, String> getModelIdsAndNames(long contestId) {
        List<Long> modelIds = getModelIds(contestId);

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

    private List<Long> getModelIds(long contestId) {
        ContestWrapper contest = contestClient.getContest(contestId);

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
