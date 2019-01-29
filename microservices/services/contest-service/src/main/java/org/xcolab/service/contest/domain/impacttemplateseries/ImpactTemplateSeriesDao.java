package org.xcolab.service.contest.domain.impacttemplateseries;

import org.xcolab.client.contest.pojo.IImpactTemplateSeries;
import org.xcolab.service.contest.exceptions.NotFoundException;

public interface ImpactTemplateSeriesDao {
    IImpactTemplateSeries get(Long seriesId) throws NotFoundException;
}
