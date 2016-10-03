package org.xcolab.service.contest.domain.impacttemplatemaxfocusarea;

import org.xcolab.model.tables.pojos.ImpactTemplateMaxFocusArea;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ImpactTemplateMaxFocusAreaDao {
    ImpactTemplateMaxFocusArea getByFocusAreaListId(Long id_) throws NotFoundException;
    List<ImpactTemplateMaxFocusArea> findByGiven(Long focusAreaListId);
}
