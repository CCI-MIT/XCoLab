package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.impact.ImpactIteration;
import org.xcolab.client.contest.pojo.impact.ImpactIterationDto;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateFocusAreaList;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateFocusAreaListDto;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateMaxFocusAreaDto;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateSeries;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateSeriesDto;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.client.contest.resources.ImpactResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.dto.DtoUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImpactClient {

    private static final Map<ServiceNamespace, ImpactClient> instances = new HashMap<>();

    private final ServiceNamespace serviceNamespace;

    private final RestResource1<ImpactTemplateSeriesDto, Long> impactTemplateSeriesResource;
    private final RestResource1<ImpactIterationDto, Long> impactIterationResource;
    private final RestResource1<ImpactTemplateFocusAreaListDto, Long>
            impactTemplateFocusAreaListResource;
    private final RestResource1<ImpactTemplateMaxFocusAreaDto, Long>
            impactTemplateMaxFocusAreaResource;

    private ImpactClient(ServiceNamespace serviceNamespace) {
        this.serviceNamespace = serviceNamespace;
        impactTemplateSeriesResource = new RestResource1<>(ImpactResource.IMPACT_TEMPLATE_SERIES,
                ImpactTemplateSeriesDto.TYPES, serviceNamespace);
        impactIterationResource = new RestResource1<>(ImpactResource.IMPACT_ITERATION,
                ImpactIterationDto.TYPES, serviceNamespace);
        impactTemplateFocusAreaListResource = new RestResource1<>(
                ImpactResource.IMPACT_TEMPLATE_FOCUS_AREA_LIST,
                ImpactTemplateFocusAreaListDto.TYPES, serviceNamespace);
        impactTemplateMaxFocusAreaResource = new RestResource1<>(
                ImpactResource.IMPACT_TEMPLATE_MAX_FOCUS_AREA, ImpactTemplateMaxFocusAreaDto.TYPES,
                serviceNamespace);
    }

    public static ImpactClient fromService(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, ImpactClient::new);
    }

    public List<ImpactTemplateMaxFocusArea> getContestImpactFocusAreas(Contest contest) {
        ImpactTemplateFocusAreaList focusAreaList = getContestImpactFocusAreaList(contest);
        return getImpactTemplateMaxFocusArea(focusAreaList.getFocusAreaListId());
    }

    public ImpactTemplateFocusAreaList getContestImpactFocusAreaList(Contest contest) {
        PlanTemplate planTemplate =
                PlanTemplateClientUtil.getPlanTemplate(contest.getPlanTemplateId());
        return getImpactTemplateFocusAreaList(planTemplate.getFocusAreaListTemplateId());
    }

    public ImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(long focusAreaListId) {
        return impactTemplateFocusAreaListResource.get(focusAreaListId)
                .execute().toPojo(serviceNamespace);
    }

    public List<ImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusArea(Long focusAreaListId) {
        return DtoUtil.toPojos(impactTemplateMaxFocusAreaResource.list()
                .optionalQueryParam("focusAreaListId", focusAreaListId)
                .execute(), serviceNamespace);
    }

    public List<ImpactIteration> getContestImpactIterations(Contest contest) {
        ImpactTemplateSeries impactSeries = getContestImpactTemplateSeries(contest);
        return getContestImpactIterations(impactSeries.getIterationId());
    }

    public ImpactTemplateSeries getContestImpactTemplateSeries(Contest contest) {

        PlanTemplate planTemplate =
                PlanTemplateClientUtil.getPlanTemplate(contest.getPlanTemplateId());
        return getImpactTemplateSeries(planTemplate.getImpactSeriesTemplateId());

    }

    public ImpactTemplateSeries getImpactTemplateSeries(long seriesId) {
        return impactTemplateSeriesResource.get(seriesId)
                .execute().toPojo(serviceNamespace);
    }

    public List<ImpactIteration> getContestImpactIterations(Long iterationId) {
        return DtoUtil.toPojos(impactIterationResource.list()
                .optionalQueryParam("iterationId", iterationId)
                .execute(), serviceNamespace);
    }
}
