package org.xcolab.client.contest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.IImpactIteration;
import org.xcolab.client.contest.pojo.IImpactTemplateFocusAreaList;
import org.xcolab.client.contest.pojo.IImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.IImpactTemplateSeries;
import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

import java.util.List;

@FeignClient("xcolab-contest-service")
public interface IImpactClient {

    default List<IImpactTemplateMaxFocusArea> getContestImpactFocusAreas(ContestWrapper contest) {
        IImpactTemplateFocusAreaList focusAreaList = getContestImpactFocusAreaList(contest);
        return getImpactTemplateMaxFocusArea(focusAreaList.getId());
    }

    default IImpactTemplateFocusAreaList getContestImpactFocusAreaList(ContestWrapper contest) {
        IProposalTemplate proposalTemplate = StaticContestContext.getProposalTemplateClient()
                .getProposalTemplate(contest.getProposalTemplateId());
        return getImpactTemplateFocusAreaList(proposalTemplate.getFocusAreaListTemplateId());
    }

    @GetMapping("/impactTemplateFocusAreaLists/{impactTemplateFocusAreaListId}")
    IImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(
            @PathVariable("impactTemplateFocusAreaListId") Long impactTemplateFocusAreaListId);

    @GetMapping("/impactTemplateMaxFocusAreas")
    public List<IImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusArea(
            @RequestParam(value = "focusAreaListId", required = false) Long focusAreaListId);

    default List<IImpactIteration> getContestImpactIterations(ContestWrapper contest) {
        IImpactTemplateSeries impactSeries = getContestImpactTemplateSeries(contest);
        return getContestImpactIterations(impactSeries.getIterationId());
    }

    default IImpactTemplateSeries getContestImpactTemplateSeries(ContestWrapper contest) {
        IProposalTemplate proposalTemplate = StaticContestContext.getProposalTemplateClient()
                .getProposalTemplate(contest.getProposalTemplateId());
        return getImpactTemplateSeries(proposalTemplate.getImpactSeriesTemplateId());
    }

    @GetMapping("/impactTemplateSeries/{impactTemplateSeriesId}")
    IImpactTemplateSeries getImpactTemplateSeries(
            @PathVariable("impactTemplateSeriesId") Long impactTemplateSeriesId);

    @GetMapping("/impactIterations")
    List<IImpactIteration> getContestImpactIterations(
            @RequestParam(value = "iterationId", required = false) Long iterationId);
}
