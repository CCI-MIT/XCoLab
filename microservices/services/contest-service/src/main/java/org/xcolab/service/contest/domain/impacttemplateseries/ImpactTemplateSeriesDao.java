package org.xcolab.service.contest.domain.impacttemplateseries;

import org.xcolab.model.tables.pojos.ImpactTemplateSeries;
import org.xcolab.service.contest.exceptions.NotFoundException;

public interface ImpactTemplateSeriesDao {
    ImpactTemplateSeries get(Long seriesId) throws NotFoundException;
}
