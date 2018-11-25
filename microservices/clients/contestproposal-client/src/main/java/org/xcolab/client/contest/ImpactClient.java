package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.impact.ImpactIteration;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateFocusAreaList;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateSeries;
import org.xcolab.client.contest.pojo.templates.ProposalTemplate;
import org.xcolab.client.contest.resources.ImpactResource;
import org.xcolab.util.http.client.RestResource1;

import java.util.List;

public class ImpactClient {

    private final RestResource1<ImpactTemplateSeries, Long> impactTemplateSeriesResource;
    private final RestResource1<ImpactIteration, Long> impactIterationResource;
    private final RestResource1<ImpactTemplateFocusAreaList, Long>
            impactTemplateFocusAreaListResource;
    private final RestResource1<ImpactTemplateMaxFocusArea, Long>
            impactTemplateMaxFocusAreaResource;

    public ImpactClient() {
        impactTemplateSeriesResource = new RestResource1<>(ImpactResource.IMPACT_TEMPLATE_SERIES,
                ImpactTemplateSeries.TYPES);
        impactIterationResource = new RestResource1<>(ImpactResource.IMPACT_ITERATION,
                ImpactIteration.TYPES);
        impactTemplateFocusAreaListResource = new RestResource1<>(
                ImpactResource.IMPACT_TEMPLATE_FOCUS_AREA_LIST,
                ImpactTemplateFocusAreaList.TYPES);
        impactTemplateMaxFocusAreaResource = new RestResource1<>(
                ImpactResource.IMPACT_TEMPLATE_MAX_FOCUS_AREA, ImpactTemplateMaxFocusArea.TYPES);
    }

    public List<ImpactTemplateMaxFocusArea> getContestImpactFocusAreas(Contest contest) {
        ImpactTemplateFocusAreaList focusAreaList = getContestImpactFocusAreaList(contest);
        return getImpactTemplateMaxFocusArea(focusAreaList.getId());
    }

    public ImpactTemplateFocusAreaList getContestImpactFocusAreaList(Contest contest) {
        ProposalTemplate proposalTemplate =
                ProposalTemplateClientUtil.getProposalTemplate(contest.getProposalTemplateId());
        return getImpactTemplateFocusAreaList(proposalTemplate.getFocusAreaListTemplateId());
    }

    public ImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(long focusAreaListId) {
        return impactTemplateFocusAreaListResource.get(focusAreaListId)
                .execute();
    }

    public List<ImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusArea(Long focusAreaListId) {
        return impactTemplateMaxFocusAreaResource.list()
                .optionalQueryParam("focusAreaListId", focusAreaListId)
                .execute();
    }

    public List<ImpactIteration> getContestImpactIterations(Contest contest) {
        ImpactTemplateSeries impactSeries = getContestImpactTemplateSeries(contest);
        return getContestImpactIterations(impactSeries.getIterationId());
    }

    public ImpactTemplateSeries getContestImpactTemplateSeries(Contest contest) {

        ProposalTemplate proposalTemplate =
                ProposalTemplateClientUtil.getProposalTemplate(contest.getProposalTemplateId());
        return getImpactTemplateSeries(proposalTemplate.getImpactSeriesTemplateId());

    }

    public ImpactTemplateSeries getImpactTemplateSeries(long seriesId) {
        return impactTemplateSeriesResource.get(seriesId)
                .execute();
    }

    public List<ImpactIteration> getContestImpactIterations(Long iterationId) {
        return impactIterationResource.list()
                .optionalQueryParam("iterationId", iterationId)
                .execute();
    }
}
