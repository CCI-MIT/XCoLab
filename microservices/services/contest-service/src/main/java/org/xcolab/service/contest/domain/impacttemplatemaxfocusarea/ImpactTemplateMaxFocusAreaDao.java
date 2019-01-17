package org.xcolab.service.contest.domain.impacttemplatemaxfocusarea;

import org.xcolab.client.contest.pojo.IImpactTemplateMaxFocusArea;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ImpactTemplateMaxFocusAreaDao {
    IImpactTemplateMaxFocusArea getByFocusAreaListId(Long id) throws NotFoundException;
    List<IImpactTemplateMaxFocusArea> findByGiven(Long focusAreaListId);
}
