package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.impact.ImpactIteration;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateFocusAreaList;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.impact.ImpactTemplateSeries;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class ImpactClientUtil {

    private static final RestService contestService = new RestService("contest-service");

    private static final ImpactClient client = ImpactClient.fromService(contestService);

    private ImpactClientUtil() {
    }

    public static ImpactClient getClient() {
        return client;
    }

    public static List<ImpactTemplateMaxFocusArea> getContestImpactFocusAreas(
            Contest contest) {
        return client.getContestImpactFocusAreas(contest);
    }

    public static ImpactTemplateFocusAreaList getContestImpactFocusAreaList(
            Contest contest) {
        return client.getContestImpactFocusAreaList(contest);
    }

    public static ImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(
            long focusAreaListId) {
        return client.getImpactTemplateFocusAreaList(focusAreaListId);
    }

    public static List<ImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusArea(
            Long focusAreaListId) {
        return client.getImpactTemplateMaxFocusArea(focusAreaListId);
    }

    public static List<ImpactIteration> getContestImpactIterations(
            Contest contest) {
        return client.getContestImpactIterations(contest);
    }

    public static ImpactTemplateSeries getContestImpactTemplateSeries(
            Contest contest) {
        return client.getContestImpactTemplateSeries(contest);
    }

    public static ImpactTemplateSeries getImpactTemplateSeries(long seriesId) {
        return client.getImpactTemplateSeries(seriesId);
    }

    public static List<ImpactIteration> getContestImpactIterations(
            Long iterationId) {
        return client.getContestImpactIterations(iterationId);
    }

}
