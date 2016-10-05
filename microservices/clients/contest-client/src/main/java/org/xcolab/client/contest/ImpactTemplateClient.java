package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ImpactIteration;
import org.xcolab.client.contest.pojo.ImpactTemplateFocusAreaList;
import org.xcolab.client.contest.pojo.ImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.ImpactTemplateSeries;
import org.xcolab.client.contest.pojo.PlanTemplate;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class ImpactTemplateClient {

    private static final RestService contestService = new RestService("contest-service");

    private static final RestResource1<ImpactTemplateSeries,Long> impactTemplateSeriesResource = new RestResource1<>(contestService,
            "impactTemplateSeries", ImpactTemplateSeries.TYPES);

    private static final RestResource1<ImpactIteration,Long> impactIterationResource = new RestResource1<>(contestService,
            "impactIterations", ImpactIteration.TYPES);

    private static final RestResource1<ImpactTemplateFocusAreaList,Long> impactTemplateFocusAreaListResource = new RestResource1<>(contestService,
            "impactTemplateFocusAreaLists", ImpactTemplateFocusAreaList.TYPES);

    private static final RestResource1<ImpactTemplateMaxFocusArea,Long> impactTemplateMaxFocusAreaResource = new RestResource1<>(contestService,
            "impactTemplateMaxFocusAreas", ImpactTemplateMaxFocusArea.TYPES);


    public static ImpactTemplateSeries getImpactTemplateSeries(long seriesId) {
        return impactTemplateSeriesResource.get(seriesId)
                .execute();
    }

    public static ImpactTemplateSeries getContestImpactTemplateSeries(Contest contest) {

        PlanTemplate planTemplate = PlanTemplateClient.getPlanTemplate(contest.getPlanTemplateId());
        return getImpactTemplateSeries(planTemplate.getImpactSeriesTemplateId());

    }

    public static List<ImpactIteration> getContestImpactIterations(Long iterationId) {
        return impactIterationResource.list()
                .optionalQueryParam("iterationId", iterationId)
                .execute();
    }

    public static ImpactTemplateFocusAreaList getContestImpactFocusAreaList(Contest contest) {
        PlanTemplate planTemplate = PlanTemplateClient.getPlanTemplate(contest.getPlanTemplateId());
        return getImpactTemplateFocusAreaList(planTemplate.getFocusAreaListTemplateId());
    }

    public static ImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(long focusAreaListId) {
        return impactTemplateFocusAreaListResource.get(focusAreaListId)
                .execute();
    }

    public static List<ImpactTemplateMaxFocusArea> getContestImpactFocusAreas(Contest contest) {
        ImpactTemplateFocusAreaList focusAreaList = getContestImpactFocusAreaList(contest);
        return getImpactTemplateMaxFocusArea(focusAreaList.getFocusAreaListId());
    }

    public static List<ImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusArea(Long focusAreaListId) {
        return impactTemplateMaxFocusAreaResource.list()
                .optionalQueryParam("focusAreaListId", focusAreaListId)
                .execute();
    }

    public static List<ImpactIteration> getContestImpactIterations(Contest contest) {
        ImpactTemplateSeries impactSeries = getContestImpactTemplateSeries(contest);
        return getContestImpactIterations(impactSeries.getIterationId());
    }
}
