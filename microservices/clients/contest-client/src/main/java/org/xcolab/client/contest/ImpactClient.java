package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.ContestWrapper;
import org.xcolab.client.contest.pojo.IImpactIteration;
import org.xcolab.client.contest.pojo.IImpactTemplateFocusAreaList;
import org.xcolab.client.contest.pojo.IImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.IImpactTemplateSeries;
import org.xcolab.client.contest.pojo.ProposalTemplate;
import org.xcolab.util.http.client.RestResource1;

import java.util.List;

public class ImpactClient {

    private final RestResource1<IImpactTemplateSeries, Long> impactTemplateSeriesResource = null; // impactTemplateSeries
    private final RestResource1<IImpactIteration, Long> impactIterationResource = null; // impactIterations
    private final RestResource1<IImpactTemplateFocusAreaList, Long>
            impactTemplateFocusAreaListResource = null; // impactTemplateFocusAreaLists
    private final RestResource1<IImpactTemplateMaxFocusArea, Long>
            impactTemplateMaxFocusAreaResource = null; // impactTemplateMaxFocusAreas

    public List<IImpactTemplateMaxFocusArea> getContestImpactFocusAreas(ContestWrapper contest) {
        IImpactTemplateFocusAreaList focusAreaList = getContestImpactFocusAreaList(contest);
        return getImpactTemplateMaxFocusArea(focusAreaList.getId());
    }

    public IImpactTemplateFocusAreaList getContestImpactFocusAreaList(ContestWrapper contest) {
        ProposalTemplate proposalTemplate =
                ProposalTemplateClientUtil.getProposalTemplate(contest.getProposalTemplateId());
        return getImpactTemplateFocusAreaList(proposalTemplate.getFocusAreaListTemplateId());
    }

    public IImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(long focusAreaListId) {
        return impactTemplateFocusAreaListResource.get(focusAreaListId)
                .execute();
    }

    public List<IImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusArea(Long focusAreaListId) {
        return impactTemplateMaxFocusAreaResource.list()
                .optionalQueryParam("focusAreaListId", focusAreaListId)
                .execute();
    }

    public List<IImpactIteration> getContestImpactIterations(ContestWrapper contest) {
        IImpactTemplateSeries impactSeries = getContestImpactTemplateSeries(contest);
        return getContestImpactIterations(impactSeries.getIterationId());
    }

    public IImpactTemplateSeries getContestImpactTemplateSeries(ContestWrapper contest) {

        ProposalTemplate proposalTemplate =
                ProposalTemplateClientUtil.getProposalTemplate(contest.getProposalTemplateId());
        return getImpactTemplateSeries(proposalTemplate.getImpactSeriesTemplateId());

    }

    public IImpactTemplateSeries getImpactTemplateSeries(long seriesId) {
        return impactTemplateSeriesResource.get(seriesId)
                .execute();
    }

    public List<IImpactIteration> getContestImpactIterations(Long iterationId) {
        return impactIterationResource.list()
                .optionalQueryParam("iterationId", iterationId)
                .execute();
    }
}
