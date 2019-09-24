package org.xcolab.client.contest;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.IImpactIteration;
import org.xcolab.client.contest.pojo.IImpactTemplateFocusAreaList;
import org.xcolab.client.contest.pojo.IImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.IImpactTemplateSeries;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ImpactClientMockImpl implements IImpactClient {

    @Override
    public IImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(
            Long impactTemplateFocusAreaListId) {
        return null;
    }

    @Override
    public List<IImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusArea(Long focusAreaListId) {
        return Collections.emptyList();
    }

    @Override
    public IImpactTemplateSeries getImpactTemplateSeries(Long impactTemplateSeriesId) {
        return null;
    }

    @Override
    public List<IImpactIteration> getContestImpactIterations(Long iterationId) {
        return Collections.emptyList();
    }
}
