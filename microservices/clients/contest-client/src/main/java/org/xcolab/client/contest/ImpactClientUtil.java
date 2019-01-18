package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.IImpactIteration;
import org.xcolab.client.contest.pojo.IImpactTemplateFocusAreaList;
import org.xcolab.client.contest.pojo.IImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.IImpactTemplateSeries;

import java.util.List;

public final class ImpactClientUtil {

    private static final ImpactClient client = new ImpactClient();

    private ImpactClientUtil() {
    }

    public static ImpactClient getClient() {
        return client;
    }

    public static List<IImpactTemplateMaxFocusArea> getContestImpactFocusAreas(
            ContestWrapper contest) {
        return client.getContestImpactFocusAreas(contest);
    }

    public static IImpactTemplateFocusAreaList getContestImpactFocusAreaList(
            ContestWrapper contest) {
        return client.getContestImpactFocusAreaList(contest);
    }

    public static IImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(
            long focusAreaListId) {
        return client.getImpactTemplateFocusAreaList(focusAreaListId);
    }

    public static List<IImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusArea(
            Long focusAreaListId) {
        return client.getImpactTemplateMaxFocusArea(focusAreaListId);
    }

    public static List<IImpactIteration> getContestImpactIterations(
            ContestWrapper contest) {
        return client.getContestImpactIterations(contest);
    }

    public static IImpactTemplateSeries getContestImpactTemplateSeries(
            ContestWrapper contest) {
        return client.getContestImpactTemplateSeries(contest);
    }

    public static IImpactTemplateSeries getImpactTemplateSeries(long seriesId) {
        return client.getImpactTemplateSeries(seriesId);
    }

    public static List<IImpactIteration> getContestImpactIterations(
            Long iterationId) {
        return client.getContestImpactIterations(iterationId);
    }

}
