package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.impact.ImpactIteration;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateFocusAreaList;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateSeries;
import org.xcolab.client.contest.pojo.templates.ProposalTemplate;
import org.xcolab.client.contest.resources.ImpactResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImpactClient {

    private static final Map<ServiceNamespace, ImpactClient> instances = new HashMap<>();

    private final ServiceNamespace serviceNamespace;

    private final RestResource1<ImpactTemplateSeries, Long> impactTemplateSeriesResource;
    private final RestResource1<ImpactIteration, Long> impactIterationResource;
    private final RestResource1<ImpactTemplateFocusAreaList, Long>
            impactTemplateFocusAreaListResource;
    private final RestResource1<ImpactTemplateMaxFocusArea, Long>
            impactTemplateMaxFocusAreaResource;

    private ImpactClient(ServiceNamespace serviceNamespace) {
        this.serviceNamespace = serviceNamespace;
        impactTemplateSeriesResource = new RestResource1<>(ImpactResource.IMPACT_TEMPLATE_SERIES,
                ImpactTemplateSeries.TYPES, serviceNamespace);
        impactIterationResource = new RestResource1<>(ImpactResource.IMPACT_ITERATION,
                ImpactIteration.TYPES, serviceNamespace);
        impactTemplateFocusAreaListResource = new RestResource1<>(
                ImpactResource.IMPACT_TEMPLATE_FOCUS_AREA_LIST,
                ImpactTemplateFocusAreaList.TYPES, serviceNamespace);
        impactTemplateMaxFocusAreaResource = new RestResource1<>(
                ImpactResource.IMPACT_TEMPLATE_MAX_FOCUS_AREA, ImpactTemplateMaxFocusArea.TYPES,
                serviceNamespace);
    }

    public static ImpactClient fromService(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, ImpactClient::new);
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
