package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.impact.ImpactIteration;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateFocusAreaList;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateSeries;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.client.contest.pojo.impact.ImpactIterationDto;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateFocusAreaListDto;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateMaxFocusAreaDto;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateSeriesDto;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImpactClient {

    private static final Map<RestService, ImpactClient> instances = new HashMap<>();

    private final RestService contestService;

    private final RestResource1<ImpactTemplateSeriesDto, Long> impactTemplateSeriesResource;
    private final RestResource1<ImpactIterationDto, Long> impactIterationResource;
    private final RestResource1<ImpactTemplateFocusAreaListDto, Long>
            impactTemplateFocusAreaListResource;
    private final RestResource1<ImpactTemplateMaxFocusAreaDto, Long>
            impactTemplateMaxFocusAreaResource;

    private ImpactClient(RestService contestService) {
        this.contestService = contestService;
        impactTemplateSeriesResource = new RestResource1<>(this.contestService,
                "impactTemplateSeries", ImpactTemplateSeriesDto.TYPES);
        impactIterationResource = new RestResource1<>(this.contestService,
                "impactIterations", ImpactIterationDto.TYPES);
        impactTemplateFocusAreaListResource = new RestResource1<>(this.contestService,
                "impactTemplateFocusAreaLists", ImpactTemplateFocusAreaListDto.TYPES);
        impactTemplateMaxFocusAreaResource = new RestResource1<>(this.contestService,
                "impactTemplateMaxFocusAreas", ImpactTemplateMaxFocusAreaDto.TYPES);
    }

    public static ImpactClient fromService(RestService contestService) {
        ImpactClient client = instances.get(contestService);
        if (client == null) {
            client = new ImpactClient(contestService);
            instances.put(contestService, client);
        }
        return client;
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
                .execute().toPojo(contestService);
    }

    public List<ImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusArea(Long focusAreaListId) {
        return DtoUtil.toPojos(impactTemplateMaxFocusAreaResource.list()
                .optionalQueryParam("focusAreaListId", focusAreaListId)
                .execute(), contestService);
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
                .execute().toPojo(contestService);
    }

    public List<ImpactIteration> getContestImpactIterations(Long iterationId) {
        return DtoUtil.toPojos(impactIterationResource.list()
                .optionalQueryParam("iterationId", iterationId)
                .execute(), contestService);
    }
}
